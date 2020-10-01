package com.learn.securitydy.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Hai
 * @date 2020/4/11 - 13:19
 */
@RestController
public class HelloController  {
  @GetMapping("/hello")
  public String hello(){
    return "hello";
  }
  @GetMapping("db/hello")
  public String db(){
    return "hello db";
  }
  @GetMapping("admin/hello")
  public String admin(){
    return "hello admin";
  }
  @GetMapping("user/hello")
  public String user(){
    return "hello user";
  }
}
