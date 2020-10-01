package com.learn.freemarker.Model;

import org.springframework.stereotype.Component;

/**
 * @author Hai
 * @date 2020/1/28 - 22:18
 */
@Component
public class User {
  private long id;
  private String username;

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }
}
