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
public class User {

  @Id
  @GeneratedValue
  @Column
  private int id;

  @Column
  private String userName;

  @Column
  private String password;

  @Column
  private String emailAddress;

  private boolean active;

  private String roles;

  @ManyToMany(targetEntity = Role.class,cascade = CascadeType.ALL )
  private List<Role> rolles = new ArrayList<>();

  public void addRole(Role role) {
    rolles.add(role);
  }
}
