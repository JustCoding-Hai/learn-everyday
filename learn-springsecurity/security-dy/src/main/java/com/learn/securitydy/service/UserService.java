package com.learn.securitydy.service;

import com.learn.securitydy.bean.User;
import com.learn.securitydy.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * @author Hai
 * @date 2020/4/11 - 12:30
 * 处理用户登录请求
 */
@Service
public class UserService implements UserDetailsService {
  @Autowired
  UserMapper userMapper;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    User user=userMapper.loadUserByUserName(username);
    if (user==null){
      throw  new UsernameNotFoundException("该用户不存在！");
    }
    user.setRoles(userMapper.getRolesById(user.getId()));
    return user;
  }
}
