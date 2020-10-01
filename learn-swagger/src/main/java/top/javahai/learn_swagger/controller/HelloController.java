package top.javahai.learn_swagger.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Hai
 * @date 2020/7/26 - 11:18
 */
@RestController
public class HelloController {
  @GetMapping
  public String hello(){
    return "hello";
  }
}
