package com.learn.mybatis.mapper;

import com.learn.mybatis.bean.Student;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author Hai
 * @date 2020/2/12 - 13:07
 */
public interface StudentMapper {

  public List<Student> queryAllStu();
}
