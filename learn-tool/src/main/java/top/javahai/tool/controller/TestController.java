package top.javahai.tool.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.javahai.tool.bean.Student;

/**
 * @author Hai
 * @program: learn-everyday
 * @description:
 * @create 2022/7/19 - 0:10
 **/
@RestController
@RequestMapping("/test")
public class TestController {

    @GetMapping("/student")
    public Student getStudent(){
        Student student = new Student();
        student.setName("张三三");
        student.setIdCard("44082199612054343");
        return student;
    }
}
