package com.learn.thymeleaf;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Hai
 * @date 2020/2/5 - 12:50
 */
@Controller
public class TestController {
  @GetMapping("/show")
  public String show(Model model){
    List<User> userList=new ArrayList<>();
    for (int i = 0; i <10 ; i++) {
      User user=new User();
      user.setId(i);
      user.setUsername("小"+i);
      user.setPhone("1786453987"+i);
      userList.add(user);
    }
     model.addAttribute("userList",userList);
    return "show";


  }
}
