package com.learn.mybatis.mapper2;

import com.learn.mybatis.bean.Student;
import com.learn.mybatis.bean.Teacher;

import java.util.List;

/**
 * @author Hai
 * @date 2020/2/12 - 16:12
 */
public interface TeacherMapper {
  public List<Teacher> queryAllTea();
}
