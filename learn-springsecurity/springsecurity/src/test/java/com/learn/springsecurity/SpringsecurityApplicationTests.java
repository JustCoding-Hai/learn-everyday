package com.learn.springsecurity;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootTest
class SpringsecurityApplicationTests {

  @Test
  void contextLoads() {
    for (int i = 0; i < 10; i++) {
      BCryptPasswordEncoder encoder=new BCryptPasswordEncoder();
      System.out.println(encoder.encode("123"));;//加密密码123

    }
  }

}
