package com.learn.securitydb.bean;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @author Hai
 * @date 2020/4/9 - 23:59
 */
public class User implements UserDetails {
  private Integer id;
  private String username;
  private String password;
  private Boolean enabled;//是否可用
  private Boolean locked;//是否锁定
  private List<Role> roles;//拥有的角色

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public void setUsername(String username) {
    this.username = username;
  }


  public void setEnabled(Boolean enabled) {
    this.enabled = enabled;
  }


  public void setLocked(Boolean locked) {
    this.locked = locked;
  }

  public List<Role> getRoles() {
    return roles;
  }

  public void setRoles(List<Role> roles) {
    this.roles = roles;
  }
  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {//返回用户所有的角色，不同角色有不同的权限
    //先去数据进行处理
    List<SimpleGrantedAuthority> authorities=new ArrayList();
    for (Role role : roles) {
      authorities.add(new SimpleGrantedAuthority("ROLE_"+role.getName()));
    }
    return authorities;
  }

  public String getPassword() {
    return password;
  }

  @Override
  public String getUsername() {
    return username;
  }

  @Override
  public boolean isAccountNonExpired() {//账号是否未过期
    return true;
  }

  @Override
  public boolean isAccountNonLocked() {//账号是否未锁定
    return !locked;
  }//账号是否没有锁定

  @Override
  public boolean isCredentialsNonExpired() {//凭证是否未过期
    return true;
  }

  @Override
  public boolean isEnabled() {//是否能使用
    return enabled;
  }

  public void setPassword(String password) {
    this.password = password;
  }
}
