package com.example.demo;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Hai
 * @date 2020/2/14 - 13:06
 */
@RestController
public class HelloController {
  @GetMapping("/hello")
  public String get(String name){
    return "hello"+name;
  }

  @PostMapping("/addUser")
  public User addUser( @RequestBody User user){
    return user;
  }
}
