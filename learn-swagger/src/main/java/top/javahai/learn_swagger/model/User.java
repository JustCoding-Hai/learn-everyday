package top.javahai.learn_swagger.model;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author Hai
 * @date 2020/7/26 - 19:20
 */
@ApiModel("用户实体类")
public class User {
  private Integer id;
  @ApiModelProperty("用户名")
  private String username;
  @ApiModelProperty("密码")
  private String password;

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }
}
