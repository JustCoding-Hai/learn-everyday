package com.learn.securitydy.bean;

/**
 * @author Hai
 * @date 2020/4/11 - 12:04
 * 角色类
 */
public class Role {
  private Integer id;
  private String name;
  private String nameZh;

  @Override
  public String toString() {
    return "Role{" +
            "id=" + id +
            ", name='" + name + '\'' +
            ", nameZh='" + nameZh + '\'' +
            '}';
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getNameZh() {
    return nameZh;
  }

  public void setNameZh(String nameZh) {
    this.nameZh = nameZh;
  }
}
