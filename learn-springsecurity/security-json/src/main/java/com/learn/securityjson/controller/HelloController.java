package com.learn.securityjson.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Hai
 * @date 2020/4/12 - 13:52
 */
@RestController
public class HelloController {
  @GetMapping("/hello")
  public String hello(){
    return "Hello Security";
  }
}
