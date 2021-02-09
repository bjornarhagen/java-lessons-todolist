package com.adstate.todoliste.datatype;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Data
@Getter
@Setter
@AllArgsConstructor
@Builder
@NoArgsConstructor
@ToString
@Entity
@Table
public class TodoListe implements Serializable {
    @Id
    @GeneratedValue
    @Column
    private long id;

    @Column
    private String taskName;

    @Column
    private LocalDateTime createdDate;

    @Column
    private LocalDateTime dueDate;

    @Column
    private String description;

    public String formatedOutput() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        String formatedCreatedDate =  createdDate.format(formatter);
        String formatedDueDate =  dueDate.format(formatter);

        String formatedOutput = String.format("Task Name: %s\n Created Date: %s\n Due Date: %s\n Short Description: %s\n",
                taskName, formatedCreatedDate, formatedDueDate,description);
        return (formatedOutput);
    }


}
