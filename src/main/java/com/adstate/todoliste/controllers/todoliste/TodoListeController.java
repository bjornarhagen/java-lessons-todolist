package com.adstate.todoliste.controllers.todoliste;

import com.adstate.todoliste.datatype.TodoListe;
import com.adstate.todoliste.services.tasks.TodoListServices;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/todoliste")
public class TodoListeController {
  @Autowired
  private TodoListServices todoListServices;
  protected final Logger log = LoggerFactory.getLogger(getClass());

  @PostMapping("/create")
  public TodoListe createTodoListe(@RequestBody TodoListe todoListe) {
    log.info(" Add an todoListe element with id {} to the system database ", todoListe.getId());
    return todoListServices.createTodoListe(todoListe);
  }

  @RequestMapping("/tasklist")
  public List<TodoListe> getAll2DoListe() {
    log.info(" Get teh list of todo task list ....");
    return (todoListServices.getAllTodoTasks());
  }

  @RequestMapping("/tasklist/{todoListId}")
  public TodoListe getTodoListById(@PathVariable("todoListId") long todoListId) {
    log.info("Fitching todoList with id: {}", todoListId);
    return(todoListServices.findById(todoListId));
  }
}
