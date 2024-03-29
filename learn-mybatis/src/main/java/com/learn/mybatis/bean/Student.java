package com.learn.mybatis.bean;

import lombok.Builder;

/**
 * @author Hai
 * @date 2020/2/12 - 13:07
 */
@Builder
public class Student {
  private int id;
  private String name;


  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }
}
