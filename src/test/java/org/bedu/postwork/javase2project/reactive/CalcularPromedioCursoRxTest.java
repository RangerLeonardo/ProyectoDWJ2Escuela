package org.bedu.postwork.javase2project.reactive;

import org.bedu.postwork.javase2project.model.Curso;
import org.bedu.postwork.javase2project.model.Estudiante;
import org.bedu.postwork.javase2project.model.Materia;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.*;


class CalcularPromedioCursoRxTest {

    private static final Curso CURSO = new Curso();

    static {
        Estudiante estudiante1 = new Estudiante();
        Estudiante estudiante2 = new Estudiante();
        Estudiante estudiante3 = new Estudiante();

        estudiante1.setNombreCompleto("Brandon Adata");
        estudiante2.setNombreCompleto("Leonardo Barrera");
        estudiante3.setNombreCompleto("Juanito Alcachofa");

        Materia materia = new Materia();
        materia.setNombre("Historia");

        CURSO.setCiclo("2024");
        CURSO.setMateria(materia);

        Map<Estudiante, Integer> calificaciones = new HashMap<>();
        calificaciones.put(estudiante1, 9);
        calificaciones.put(estudiante2, 8);
        calificaciones.put(estudiante3, 0);

        CURSO.setCalificaciones(calificaciones);
    }

    @Test
    @DisplayName("Soy el test para Mono y ReactiveRx")
    void calculaPromedio(){
        CalcularPromedioCursoRx promedio = new CalcularPromedioCursoRx();
        promedio.calcularPromedio(CURSO)
                .subscribe(v -> assertThat(v).isEqualTo(5.6, within(0.1)));

    }

}