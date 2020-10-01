package com.example.demo;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.junit.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.test.context.junit4.SpringRunner;
import java.io.IOException;

@RunWith(SpringRunner.class)
@org.springframework.boot.test.autoconfigure.json.JsonTest
//@SpringBootTest
class JsonTest {
  @Autowired
  JacksonTester<User> jacksonTester;
  @Test
  void contextLoads() throws IOException {
    User user = new User();
    user.setId(1);
    user.setName("hjh");
    Assertions.assertThat(jacksonTester.write(user)).isEqualToJson("user.json");
  }

}
