package com.learn.securitydy.mapper;

import com.learn.securitydy.bean.Role;
import com.learn.securitydy.bean.User;

import java.util.List;

/**
 * @author Hai
 * @date 2020/4/11 - 12:21
 */
public interface UserMapper {
  List<Role> getRolesById(Integer id);
  User loadUserByUserName(String username);
}
