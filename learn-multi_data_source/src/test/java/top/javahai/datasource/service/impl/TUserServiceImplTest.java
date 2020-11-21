package top.javahai.datasource.service.impl;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import top.javahai.datasource.entity.TUser;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class TUserServiceImplTest {

    @Autowired
    TUserServiceImpl userService;

    @Test
    void insertUser() {
        TUser user = new TUser();
        user.setcId(6);
        user.setcUsername("张三");
        user.setcPassword("111");
        user.setcGender(0);
        userService.insertUser(user);

    }
}
