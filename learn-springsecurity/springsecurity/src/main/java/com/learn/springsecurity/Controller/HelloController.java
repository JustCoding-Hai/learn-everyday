package com.learn.springsecurity.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Hai
 * @date 2020/4/7 - 15:53
 */
@RestController
public class HelloController {
  @GetMapping("hello")
  public String Hello(){
    return "Hello Spring Security";
  }
  @GetMapping("admin/hello")
  public String admin(){
    return "Hello admin";
  }
  @GetMapping("user/hello")
  public String user(){
    return "Hello user";
  }
  @GetMapping("login")
  public String login(){
    return "please login";
  }
}
