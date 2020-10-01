package com.learn.jdbctemplate.service;

import com.learn.jdbctemplate.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * @author Hai
 * @date 2020/2/11 - 16:38
 */
@Service
public class StudentService {
  @Autowired
  @Qualifier("jdbcTemplateOne")
  JdbcTemplate jdbcTemplate;

  //插入学生数据
  public int addStudent(Student student){
    return jdbcTemplate.update("insert into student(id,name) values(?,?)",student.getId(),student.getName());
  }

  //更新学生数据
  public int updStudent(int id,String name){
    return jdbcTemplate.update("update student set name=? where id=?",name,id);
  }

  //删除学生数据
  public int delStudent(int id){
    return jdbcTemplate.update("delete from student where id=?",id);
  }

  //查询学生数据
  public List<Student> queryAllStudent(){
    return jdbcTemplate.query("select * from student", new RowMapper<Student>() {
      @Override
      public Student mapRow(ResultSet resultSet, int i) throws SQLException {
        int id=resultSet.getInt("id");
        String name=resultSet.getString("name");
        Student student=new Student();
        student.setId(id);
        student.setName(name);
        return student;
      }
    });

  }
  //查询学生数据的第二种方式
  public List<Student> queryAllStudent2(){
    return jdbcTemplate.query("select * from student",new BeanPropertyRowMapper<>(Student.class));
  }
}
