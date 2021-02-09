package com.adstate.todoliste.utils;

import com.adstate.todoliste.datatype.Role;
import com.adstate.todoliste.datatype.User;
import com.adstate.todoliste.repository.RoleRepository;
import com.adstate.todoliste.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
public class InitializeUsers {

  @Autowired
  private UserRepository userRepository;

  @Autowired
  private RoleRepository roleRepository;

  protected final Logger log = LoggerFactory.getLogger(getClass());

  @PostConstruct
  public void initUsers() {
    User u1 =  new User(101, "GENIEIT", "genieit", "aziz.halaoui@genie.no", true,  "ROLE_ADMIN, ROLE_USER",new ArrayList<>());
    User u2 = new User(102, "CELINA1509", "celina1509", "celina.halaoui@genie.no", true,"ROLE_ADMIN", new ArrayList<>());
    User u3 = new User(103, "JONAS2912", "jonas2912", "jonas.halaoui@genie.no", true,"ROLE_ANONYME", new ArrayList<>());
    User u4 = new User(104, "OSLO4722", "oslo4722", "oslo.norway@genie.no", true,"ROLE_USER", new ArrayList<>());

    List<User> users = Arrays.asList(u1, u2, u3, u4);

    Role role1 = new Role(101, "ROLE_ADMIN", "Administrator roles", new ArrayList<>());
    Role role2 = new Role(102, "ROLE_USER", "User roles", new ArrayList<>());
    Role role3 = new Role(103, "ROLE_ANONYME", "Visitor roles", new ArrayList<>());

    List<Role> roles = Arrays.asList(role1, role2, role3);
    roleRepository.saveAll(roles);

    u1.addRole(role1);
    u1.addRole(role2);

    u2.addRole(role1);
    u3.addRole(role3);
    u4.addRole(role2);

    // Save in the system dbms the list of users.
    userRepository.saveAll(users);

    for(User u:users) {
      log.info("User: {} <---> Role: {}", u.getUserName(), u.getRoles());
    }
  }
}
