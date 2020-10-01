package com.learn.securitydy.bean;

import java.util.List;

/**
 * @author Hai
 * @date 2020/4/11 - 12:14
 * 权限类，控制不同角色的访问权限
 */
public class Menu {
  private Integer id;
  private String pattern;//访问路径
  private List<Role> roles;//访问指定路径所需要的角色

  @Override
  public String toString() {
    return "Menu{" +
            "id=" + id +
            ", pattern='" + pattern + '\'' +
            ", roles=" + roles +
            '}';
  }

  public List<Role> getRoles() {
    return roles;
  }

  public void setRoles(List<Role> roles) {
    this.roles = roles;
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getPattern() {
    return pattern;
  }

  public void setPattern(String pattern) {
    this.pattern = pattern;
  }
}
