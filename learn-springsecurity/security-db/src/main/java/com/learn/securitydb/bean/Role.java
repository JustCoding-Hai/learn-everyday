package com.learn.securitydb.bean;

/**
 * @author Hai
 * @date 2020/4/10 - 0:02
 */
public class Role {
  private Integer id;
  private String name;//角色名
  private String nameZh;//角色中文名

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
