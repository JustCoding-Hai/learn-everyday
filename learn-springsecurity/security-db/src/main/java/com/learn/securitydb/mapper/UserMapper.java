package com.learn.securitydb.mapper;

import com.learn.securitydb.bean.Role;
import com.learn.securitydb.bean.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author Hai
 * @date 2020/4/10 - 13:22
 */
@Mapper
public interface UserMapper {
  User loadUserByUsername(String username);//根据用户名查询用户
  List<Role> getUserRoleById(Integer id);//获取用户角色
}
