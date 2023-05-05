package org.bedu.postwork.javase2project.persistence;


import org.bedu.postwork.javase2project.model.Curso;
import org.bedu.postwork.javase2project.model.Estudiante;
import org.bedu.postwork.javase2project.model.Materia;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ComponentScan(basePackages = "org.bedu.postwork.javase2project")
@ExtendWith(SpringExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class CursoRepositoryTest {
    @Autowired
    private MateriaRepository materiaRepository;
    @Autowired
    private EstudianteRepository estudianteRepository;
    @Autowired
    private CursoRepository cursoRepository;

    @BeforeAll
    void cleanDatabases(){
        cursoRepository.deleteAll();
        materiaRepository.deleteAll();
        estudianteRepository.deleteAll();
    }


    @Test
    @DisplayName("Guarda estudianteFK, materiaFK y calificacion ")
    @Transactional
    void canSave() {
        Materia materia = new Materia();
        materia.setNombre("Matematicas");
        materiaRepository.save(materia);

        Estudiante estudiante = new Estudiante();
        estudiante.setNombreCompleto("Brandon Leonardo Adata Barrera");
        estudianteRepository.save(estudiante);

        Curso curso = new Curso();
        curso.setMateria(materia);
        curso.setCiclo("2023");
        cursoRepository.save(curso);

        Map<Estudiante, Integer> calificaciones = new HashMap<>();
        calificaciones.put(estudiante, Curso.NO_CALIFICADO);
        curso.setCalificaciones(calificaciones);

        assertNotNull(estudiante.getId());
        assertNotNull(materia.getId());
        assertNotNull(curso.getId());
        System.out.println(curso.getMateria().getId()+" "+estudiante.getId()+" "+materia.getId()+" AQUÍ ESTOY -------------------+++");
    }



}