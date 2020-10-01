package com.example.demo;

/**
 * @author Hai
 * @date 2020/2/14 - 14:07
 */
public class User {
  private int id;
  private String name;

  @Override
  public String toString() {
    return "User{" +
            "id=" + id +
            ", name='" + name + '\'' +
            '}';
  }

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
