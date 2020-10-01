package com.example.demo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;



@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
class DemoApplicationTests2 {
  @Autowired
  TestRestTemplate testRestTemplate;

  @Test
  void contextLoads() {
    String result = testRestTemplate.getForObject("/hello?name=java", String.class);
    System.out.println(result);
  }

}
