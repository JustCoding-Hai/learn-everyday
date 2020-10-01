package com.learn.springsecurity.service;

import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

/**
 * @author Hai
 * @date 2020/4/9 - 18:53
 */
@Service
public class HelloService {
  @PreAuthorize("hasRole('admin')")
  public String admin(){
    return "Hello admin";
  }
  @Secured("ROLE_user")
  public String user(){
    return "Hello user";
  }
  @PreAuthorize("hasAnyRole('admin','user'")
  public String hello(){
    return "Helo";
  }
}
