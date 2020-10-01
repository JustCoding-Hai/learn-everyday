package com.example.demo;

import org.springframework.stereotype.Service;

/**
 * @author Hai
 * @date 2020/2/14 - 12:04
 */
@Service
public class HelloService {

  public String helloService(String name){
    return "hello"+name;

  }
}
