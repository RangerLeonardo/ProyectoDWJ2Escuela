package org.bedu.postwork.javase2project.repositories;

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

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ComponentScan(basePackages = "org.bedu.postwork.javase2project")
@ExtendWith(SpringExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class MateriaRepositoryTet {

    @Autowired
    private MateriaRepository repository;

    @BeforeAll
    void cleanDatabase(){
        repository.deleteAll();
    }
    @Test
    @DisplayName("Materia Guardada")
    void canSave(){
        Materia materia1 = new Materia();
        materia1.setNombre("Matematicas");
        materia1 = repository.save(materia1);
        System.out.println(materia1.getNombre());
        assertNotNull(materia1.getId());

    }
}