package com.adstate.todoliste.services.tasks;

import com.adstate.todoliste.datatype.TodoListe;
import com.adstate.todoliste.exceptions.TodoListeNotFoundException;
import com.adstate.todoliste.repository.TodoListeRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;

@Component
public class TodoListServicesImpl implements TodoListServices {
    @Resource
     private TodoListeRepository todoListeRepository;

    @Override
    public TodoListe createTodoListe(TodoListe todoListe) {
        return todoListeRepository.save(todoListe);
    }

    @Override
    @Transactional(rollbackFor = TodoListeNotFoundException.class)
    public TodoListe deleteTodoListe(Long id) throws TodoListeNotFoundException {
        TodoListe todoTask = todoListeRepository.getOne(id);
        if(null == todoTask)
            throw new TodoListeNotFoundException(todoTask.getTaskName(), todoTask.getCreatedDate());
        todoListeRepository.deleteById(id);
        return todoTask;
    }

    @Override
    public List<TodoListe> getAllTodoTasks() {
        return todoListeRepository.findAll();
    }

    @Override
    public TodoListe findById(Long id) {
        return todoListeRepository.findById(id).get();
    }

    @Override
    public List<TodoListe> findTodoListeByCreatedDateBefore(LocalDateTime localDateTime) {
        return todoListeRepository.findTodoListeByCreatedDateBefore(localDateTime);
    }

    @Override
    public List<TodoListe> findTodoListeByCreatedDateAfter(LocalDateTime localDateTime) {
        return todoListeRepository.findTodoListeByCreatedDateAfter(localDateTime);
    }

    @Override
    public List<TodoListe> findTodoListeByDueDate(LocalDateTime localDateTime) {
        return todoListeRepository.findTodoListeByDueDate(localDateTime);
    }
}
