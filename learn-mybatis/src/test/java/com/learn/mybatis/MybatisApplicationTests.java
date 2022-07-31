package com.learn.mybatis;

import com.learn.mybatis.bean.Student;
import com.learn.mybatis.bean.Teacher;
import com.learn.mybatis.mapper.StudentMapper;
import com.learn.mybatis.mapper2.TeacherMapper;
import com.mysql.jdbc.Driver;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class MybatisApplicationTests {
  @Autowired
  StudentMapper studentMapper;
  @Autowired
  TeacherMapper teacherMapper;

  @Test
  void contextLoads() {
    List<Student> list=studentMapper.queryAllStu();
    for (Student student:list){
      System.out.println("编号："+student.getId()+ " 姓名："+student.getName());
    }
    List<Teacher> teacherList=teacherMapper.queryAllTea();
    for (Teacher teacher:teacherList){
      System.out.println("编号："+teacher.getId()+ " 姓名："+teacher.getName());
    }
  }

}
