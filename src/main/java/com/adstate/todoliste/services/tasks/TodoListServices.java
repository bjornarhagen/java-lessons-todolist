package com.adstate.todoliste.services.tasks;

import com.adstate.todoliste.datatype.TodoListe;
import org.springframework.boot.context.config.ConfigDataResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public interface TodoListServices {

    /**
     *
     * @param todoListe
     * @return 0 inserted.
     */
    TodoListe createTodoListe(TodoListe todoListe);

    /**
     *
     * @param listOfTodoList
     */
    void savAllTodos(List<TodoListe> listOfTodoList);

    /**
     *
     * @param todoListeId
     * @return 0 if deleted
     * @throws ConfigDataResourceNotFoundException
     */
    TodoListe deleteTodoListe(Long todoListeId) throws ConfigDataResourceNotFoundException;

    TodoListe findById(Long id);

    /**
     *
     * @return the current todoo list from the system databas.
     */
    List<TodoListe> getAllTodoTasks();

    /**
     *
     * @param localDateTime
     * @return
     */
    List<TodoListe> findTodoListeByCreatedDateBefore(LocalDateTime localDateTime);

    /**
     *
     * @param localDateTime
     * @return
     */
    List<TodoListe> findTodoListeByCreatedDateAfter(LocalDateTime localDateTime);

    /**
     *
     * @param localDateTime
     * @return
     */
    List<TodoListe> findTodoListeByDueDate(LocalDateTime localDateTime);
}
