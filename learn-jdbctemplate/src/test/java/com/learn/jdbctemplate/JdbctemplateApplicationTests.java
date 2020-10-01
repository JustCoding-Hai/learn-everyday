package com.learn.jdbctemplate;

import com.learn.jdbctemplate.model.Student;
import com.learn.jdbctemplate.model.Teacher;
import com.learn.jdbctemplate.service.StudentService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.annotation.Resource;
import java.util.List;

@SpringBootTest
class JdbctemplateApplicationTests {
  @Autowired
  StudentService studentService;
  @Test
  void contextLoads() {

  }

  //测试插入
  @Test
  public void addTest() {
    Student student=new Student();
    student.setId(14);
    student.setName("赵14");
    if (studentService.addStudent(student)>=1){
      System.out.println("数据插入成功！");
    }
  }

  //测试更新
  @Test
  public void updTest(){
    if (studentService.updStudent(5, "赵101")>=1) {
      System.out.println("数据更新成功！");
    }
    }

  //测试删除
  @Test
  public void delTest(){
    if (studentService.delStudent(201)>=1) {
      System.out.println("数据删除成功！");
    }
  }

  //测试查询
  @Test
  public void queryTest(){
    List<Student> list=studentService.queryAllStudent();
    for (Student student:list){
      System.out.println("编号:"+student.getId()+"  "+"姓名:"+student.getName());
    }
  }
  //测试查询
  @Test
  public void queryTest2(){
    List<Student> list=studentService.queryAllStudent2();
    for (Student student:list){
      System.out.println("编号:"+student.getId()+"  "+"姓名:"+student.getName());
    }
  }

  //连接另一个数据库
  @Resource(name = "jdbcTemplateTwo")
  JdbcTemplate jdbcTemplate;

  @Test
  public void queryTeacherTest(){
    List<Teacher> list=jdbcTemplate.query("select * from teacher", new BeanPropertyRowMapper<>(Teacher.class));
    for (Teacher teacher:list){
      System.out.println("编号:"+teacher.getId()+"  "+"姓名:"+teacher.getName());
    }
  }


}
