package com.adstate.todoliste;

import com.adstate.todoliste.datatype.User;
import com.adstate.todoliste.repository.UserRepository;
import com.adstate.todoliste.utils.InitializeUsers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@SpringBootApplication
@EnableSwagger2
public class TodolisteApplication {

  @Autowired
  private static InitializeUsers initializeUsers = new InitializeUsers();

  public static void main(String[] args) {
    SpringApplication.run(TodolisteApplication.class, args);

    initializeUsers.initUsers();
  }

}
