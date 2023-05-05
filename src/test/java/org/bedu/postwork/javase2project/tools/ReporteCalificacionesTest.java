package org.bedu.postwork.javase2project.tools;

import org.bedu.postwork.javase2project.model.Curso;
import org.bedu.postwork.javase2project.model.Estudiante;
import org.bedu.postwork.javase2project.model.Materia;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import org.assertj.core.api.Assertions;
import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class ReporteCalificacionesTest {
    private static final Materia MATERIA = new Materia();
    private static final Estudiante ESTUDIANTE_1 = new Estudiante();
    private static final Estudiante ESTUDIANTE_2 = new Estudiante();
    private static final Estudiante ESTUDIANTE_3 = new Estudiante();
    private static final Curso CURSO = new Curso();

    private static ReporteCalificaciones.Reporte reporte1;
    private static ReporteCalificaciones.Reporte reporte2;
    private static ReporteCalificaciones.Reporte reporte3;

    static{
        MATERIA.setNombre("Matemáticas");

        ESTUDIANTE_1.setNombreCompleto("Brandon Adata");
        ESTUDIANTE_2.setNombreCompleto("Leonardo Barrera");
        ESTUDIANTE_3.setNombreCompleto("Juanito Alcachofa");

        CURSO.setCiclo("2024");
        CURSO.setMateria(MATERIA);

        Map<Estudiante,Integer> calificaciones = new HashMap<>();
        calificaciones.put(ESTUDIANTE_1, 9);
        calificaciones.put(ESTUDIANTE_2, 7);
        calificaciones.put(ESTUDIANTE_3, 8);

        CURSO.setCalificaciones(calificaciones);

        reporte1 = new ReporteCalificaciones.Reporte("Brandon Adata", 9);
        reporte2 = new ReporteCalificaciones.Reporte("Leonardo Barrera", 7);
        reporte3 = new ReporteCalificaciones.Reporte("Juanito Alcachofa", 8);


    }

    @Test
    @DisplayName("Ordena alfabéticamente")
    void alfabetico(){
        ReporteCalificaciones reporteCalificaciones = new ReporteCalificaciones();

        assertThat(reporteCalificaciones.ordenarAlfabeticamente(CURSO)).containsExactly(reporte1, reporte3, reporte2);
    }


    @Test
    @DisplayName("Ordena por calificaciones")
    void calificaciones(){
        ReporteCalificaciones reporteCalificaciones = new ReporteCalificaciones();

        assertThat(reporteCalificaciones.ordenarCalificaciones(CURSO)).containsExactly(reporte1, reporte3, reporte2);
    }

}