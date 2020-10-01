package com.learn.securitydy.service;

import com.learn.securitydy.bean.Menu;
import com.learn.securitydy.mapper.MenuMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Hai
 * @date 2020/4/11 - 21:50
 */
@Service
public class MenuService {
  @Autowired
  MenuMapper menuMapper;

  public List<Menu> getAllMenus(){
    return menuMapper.getAllMenus();
  }
}
