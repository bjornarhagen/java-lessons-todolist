package com.adstate.todoliste.repository;

import com.adstate.todoliste.datatype.TodoListe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface TodoListeRepository extends JpaRepository<TodoListe, Long> {

    @Query("SELECT t FROM TodoListe t WHERE t.createdDate < : createdDate")
    List<TodoListe> findTodoListeByCreatedDateBefore(LocalDateTime createdDate);

    @Query("SELECT t FROM TodoListe t WHERE t.dueDate > : dueDate")
    List<TodoListe> findTodoListeByCreatedDateAfter(LocalDateTime localDateTime);

    @Query("SELECT t FROM TodoListe t WHERE t.dueDate = : dueDate")
    List<TodoListe> findTodoListeByDueDate(LocalDateTime localDateTime);
}
