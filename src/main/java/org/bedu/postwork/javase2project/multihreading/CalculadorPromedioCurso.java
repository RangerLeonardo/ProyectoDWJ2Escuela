package org.bedu.postwork.javase2project.multihreading;

import org.bedu.postwork.javase2project.model.Curso;

public class CalculadorPromedioCurso implements Runnable {
    private Curso curso;
    private double promedio;

    public CalculadorPromedioCurso(Curso curso) {
        this.curso = curso;
    }

    @Override
    public void run() {
        int numAlumnos = 0;
        for(Integer calificaciones : curso.getCalificaciones().values()){
            promedio += calificaciones;
            numAlumnos++;
        }
        promedio /= numAlumnos;
        System.out.println("Promedio del curso: " + curso.getId() + " " + curso.getMateria().getNombre() + " = " + promedio);
    }
}
