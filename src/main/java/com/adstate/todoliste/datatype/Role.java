package com.adstate.todoliste.datatype;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table
public class Role {

  @Id
  @GeneratedValue
  @Column
  private int id;

  @Column
  private String name;

  @Column
  private String description;

  @ManyToMany(targetEntity = User.class, mappedBy = "rolles", cascade = CascadeType.ALL)
  private List<User> users = new ArrayList<>();

  public void addUser(User user) {
    users.add(user);
  }
}

