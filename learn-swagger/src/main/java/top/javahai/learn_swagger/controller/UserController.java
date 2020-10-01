package top.javahai.learn_swagger.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import top.javahai.learn_swagger.model.User;

/**
 * @author Hai
 * @date 2020/7/26 - 19:07
 */
@RestController
@RequestMapping("/user")
@Api(tags="用户实体相关请求控制器")
public class UserController {
  @GetMapping("/")
  @ApiOperation("返回所有的用户")
  public String getAllUsers(){
    return "This is All";
  }
  @DeleteMapping("/{id}")
  @ApiOperation("/根据用户id删除用户")
  public String deleteUserById(@PathVariable String id){
    return "ok";
  }
  @PostMapping("/")
  public User insertUser(@RequestBody User user){
    return user;
  }
}
