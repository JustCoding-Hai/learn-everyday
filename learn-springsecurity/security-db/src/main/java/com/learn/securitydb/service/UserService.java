package com.learn.securitydb.service;

import com.learn.securitydb.bean.User;
import com.learn.securitydb.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * @author Hai
 * @date 2020/4/10 - 13:25
 */
@Service
public class UserService implements UserDetailsService {
  @Autowired
  UserMapper userMapper;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    User user=userMapper.loadUserByUsername(username);
    if (user==null){
      throw  new UsernameNotFoundException("用户不存在！");
    }
    user.setRoles(userMapper.getUserRoleById(user.getId()));
    return user;
  }
}
