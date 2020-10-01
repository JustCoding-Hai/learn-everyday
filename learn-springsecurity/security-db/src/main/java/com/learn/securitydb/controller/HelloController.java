package com.learn.securitydb.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Hai
 * @date 2020/4/10 - 16:39
 */
@RestController
public class HelloController {

  @GetMapping("/hello")
  public String hello(){
    return "hello security";
  }
  @GetMapping("admin/hello")
  public String admin(){
    return "hello admin";
  }
  @GetMapping("dba/hello")
  public String dba(){
    return "hello dba";
  }
  @GetMapping("user/hello")
  public String user(){
    return "hello user";
  }
}
