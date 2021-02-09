package com.adstate.todoliste.exceptions;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Data
@Getter
@Setter

public class TodoListeNotFoundException extends RuntimeException {
    private String taskName;
    private LocalDateTime createdDate;

    public TodoListeNotFoundException(String taskName, LocalDateTime createdDate) {
        super(String.format(" Todo task with: %s and %l not found", taskName, createdDate));
        this.taskName = taskName;
        this.createdDate = createdDate;
    }
}
