package org.bedu.postwork.javase2project.multihreading;

import org.bedu.postwork.javase2project.model.Curso;
import org.bedu.postwork.javase2project.model.Estudiante;
import org.bedu.postwork.javase2project.model.Materia;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CrearCurso {

        private static Curso crearCurso(Random random, String nombreMateria, long idCurso) {
                Curso curso1 = new Curso();
                curso1.setId(idCurso);
                Materia materia1 = new Materia();
                materia1.setNombre(nombreMateria);
                curso1.setMateria(materia1);

                Map<Estudiante, Integer> calificaciones = new HashMap<>();
                for (int i = 0; i < 20; i++) {
                        Estudiante estudiante = new Estudiante();
                        estudiante.setId((long)i);
                        estudiante.setNombreCompleto("Estudiante " + i);
                        calificaciones.put(estudiante, random.nextInt(10));
                }

                curso1.setCalificaciones(calificaciones);
                return curso1;
        }


        public static void main(String[] args) {
                Random random = new Random();
                ExecutorService pool = Executors.newCachedThreadPool();
                Curso[] cursos = new Curso[]{
                        crearCurso(random, "Ecuaciones diferenciales", 1),
                        crearCurso(random, "Programación orientada a objetos", 2),
                        crearCurso(random, "Estadistica", 3),
                        crearCurso(random, "Seguridad informatica", 4)
                };
                for(Curso curso : cursos){
                        pool.execute(new CalculadorPromedioCurso(curso));
                }
                pool.shutdown();
        }


}
