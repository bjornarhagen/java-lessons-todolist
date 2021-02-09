package com.adstate.todoliste.services.tasks;

import com.adstate.todoliste.TodolisteApplicationTests;
import com.adstate.todoliste.datatype.TodoListe;
import org.junit.Assert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.time.Month;

@RunWith(SpringRunner.class)
class TodoListServicesImplTest extends TodolisteApplicationTests {

    @Autowired
    private TodoListServices todoListServices;
    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    @DisplayName("testCreateTodoListe: Given an object of Todo List created, use jpa repository to save the object to the system database.")
    void testCreateTodoListe() {

        TodoListe todoListe= TodoListe.builder()
                .taskName("Add Security to application")
                .createdDate(LocalDateTime.now())
                .dueDate(LocalDateTime.of(2021, Month.JANUARY, 31, 23, 59, 59))
                .description("Add Security to the product ...")
                .build();
        Assert.assertNotNull(todoListServices);
        todoListServices.createTodoListe(todoListe);
        Assert.assertTrue(todoListe.getTaskName().equals("Add Security to application"));
    }
}