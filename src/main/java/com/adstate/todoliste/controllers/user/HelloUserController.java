package com.adstate.todoliste.controllers.user;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user/")
public class HelloUserController {

  @RequestMapping("hello")
  public String helloUser() {
    return (" Hello user updated....?");
  }
}
