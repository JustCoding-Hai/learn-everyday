package com.learn.freemarker.controller;

import com.learn.freemarker.Model.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Hai
 * @date 2020/1/28 - 22:19
 */
@Controller
public class UserController {
  @GetMapping("/show")
  public String show(Model model){
    List<User> users=new ArrayList<User>();
    for (int i = 0; i < 10; i++) {
      User user=new User();
      user.setId(i);
      user.setUsername("用户"+i);
      users.add(user);
    }
    model.addAttribute("users",users);
    return "show";
  }
}
