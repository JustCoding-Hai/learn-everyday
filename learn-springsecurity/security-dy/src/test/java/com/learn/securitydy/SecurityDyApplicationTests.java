package com.learn.securitydy;

import com.learn.securitydy.service.MenuService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SecurityDyApplicationTests {

  @Autowired
  MenuService menuService;

  @Test
  void contextLoads() {
    System.out.println(menuService.getAllMenus());
  }

}
