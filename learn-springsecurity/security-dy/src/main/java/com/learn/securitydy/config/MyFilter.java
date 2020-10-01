package com.learn.securitydy.config;

import com.learn.securitydy.bean.Menu;
import com.learn.securitydy.bean.Role;
import com.learn.securitydy.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;

import java.util.Collection;
import java.util.List;

/**
 * @author Hai
 * @date 2020/4/11 - 13:39
 * 找到要访问路径所需要的角色
 */
@Component
public class MyFilter implements FilterInvocationSecurityMetadataSource {

  AntPathMatcher antPathMatcher=new AntPathMatcher();//用于匹配路径

  @Autowired
  MenuService menuService;

  @Override
  public Collection<ConfigAttribute> getAttributes(Object o) throws IllegalArgumentException {
    //获取请求路径
    String requestUrl = ((FilterInvocation) o).getRequestUrl();
    //获取所有的角色对应权限列表
    List<Menu> allMenus = menuService.getAllMenus();
    for (Menu menu : allMenus) {
      if (antPathMatcher.match(menu.getPattern(),requestUrl)){//匹配请求路径
        List<Role> roles = menu.getRoles();//路径所需要的角色
        String[] rolesStr=new String[roles.size()];
        for (int i = 0; i < roles.size(); i++) {
          rolesStr[i]=roles.get(i).getName();
        }
        return SecurityConfig.createList(rolesStr);
      }
    }

    return SecurityConfig.createList("ROLE_login"); //默认可以访问的
  }

  @Override
  public Collection<ConfigAttribute> getAllConfigAttributes() {
    return null;
  }

  @Override
  public boolean supports(Class<?> aClass) {
    return true;
  }
}
