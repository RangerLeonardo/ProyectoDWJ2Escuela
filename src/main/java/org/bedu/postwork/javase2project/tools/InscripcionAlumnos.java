package org.bedu.postwork.javase2project.tools;

import org.bedu.postwork.javase2project.async.SolicitudEstudiante;
import org.bedu.postwork.javase2project.model.Curso;
import org.bedu.postwork.javase2project.model.Estudiante;
import org.bedu.postwork.javase2project.model.Materia;
import org.bedu.postwork.javase2project.controller.SolicitudesController;

import java.util.Random;
import java.util.concurrent.TimeUnit;

public class InscripcionAlumnos {
    public static void main(String[] args) {
        Random random = new Random();

        SolicitudesController eventLoop = new SolicitudesController(InscripcionAlumnos::procesarSolicitud);

        eventLoop.iniciar();
        SolicitudEstudiante[] solicitudes = crearSolicitudes();

        for (SolicitudEstudiante s : solicitudes) {
            eventLoop.registrarEvento(s);
            try {
                TimeUnit.MILLISECONDS.sleep(random.nextInt(200));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        eventLoop.detener();
    }


    private static void procesarSolicitud(SolicitudEstudiante solicitud) {
        if (solicitud.getEstudiante() == null || solicitud.getCurso() == null) {
            System.out.println("Solicitud rechazada por datos incompletos");
            return;
        }
        System.out.println("El estudiante: " + solicitud.getEstudiante().getNombreCompleto()
                + " se ha inscrito en la materia: " + solicitud.getCurso().getMateria().getNombre());
    }

    private static SolicitudEstudiante[] crearSolicitudes() {
        Random random = new Random();
        Curso[] cursos = new Curso[]{
                crearCurso(random, "Programación estructurada", 1),
                crearCurso(random, "Programación orientada a objetos", 2),
                crearCurso(random, "Estructura de datos", 3),
                crearCurso(random, "Bases de datos", 4)
        };

        SolicitudEstudiante[] solicitudes = new SolicitudEstudiante[20];
        for (int i = 0; i < 20; i++) {
            Estudiante e = new Estudiante();
            e.setNombreCompleto("Estudiante " + i);
            e.setId((long)i);

            solicitudes[i] = new SolicitudEstudiante(e, cursos[random.nextInt(cursos.length)]);
        }
        return solicitudes;
    }

    private static Curso crearCurso(Random random, String nombreMateria, long idCurso) {
        Curso curso1 = new Curso();
        curso1.setId(idCurso);
        Materia materia1 = new Materia();
        materia1.setNombre(nombreMateria);
        curso1.setMateria(materia1);
        return curso1;
    }
}