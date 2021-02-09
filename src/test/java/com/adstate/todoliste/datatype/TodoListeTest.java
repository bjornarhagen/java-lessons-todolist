package com.adstate.todoliste.datatype;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.Month;

import static org.junit.jupiter.api.Assertions.*;

class TodoListeTest {

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void testTodoListe() {
        System.out.println("Create a new task of todos");
        TodoListe todoListe= TodoListe.builder()
                .taskName("Logging")
                .createdDate(LocalDateTime.now())
                .dueDate(LocalDateTime.of(2021, Month.JULY, 31, 23, 59, 59))
                .description("Add logging to the product ...")
                .build();

        // Print out this todo.
        System.out.println(todoListe.formatedOutput());
        assertTrue(todoListe.getTaskName().equals("Logging"));

    }
}