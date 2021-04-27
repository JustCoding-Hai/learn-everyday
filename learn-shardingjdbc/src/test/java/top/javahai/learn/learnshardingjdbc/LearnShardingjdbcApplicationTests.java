package top.javahai.learn.learnshardingjdbc;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import top.javahai.learn.learnshardingjdbc.Entity.Course;
import top.javahai.learn.learnshardingjdbc.Entity.User;
import top.javahai.learn.learnshardingjdbc.dao.CourseMapper;
import top.javahai.learn.learnshardingjdbc.dao.UserMapper;

import javax.xml.ws.Action;

@RunWith(SpringRunner.class)
@SpringBootTest
class LearnShardingjdbcApplicationTests {

    @Autowired
    CourseMapper courseMapper;

    @Autowired
    UserMapper userMapper;

    @Test
    void contextLoads() {
    }

    /**
     * 测试水平分表-插入操作
     */
    @Test
    public void testInsert(){
        for (int i = 0; i < 10; i++) {
//            Course course =new Course();
//            course.setUserId(100L);
//            course.setName("hai");
//            course.setStatus("normal");
          Course course = Course.builder().userId(24L).status("normal").name("Kobe").build();

            courseMapper.insert(course);
        }
    }


    /**
     * 测试水平分表-查询操作
     */
    @Test
    public void testSelect(){
        //查询表1
        Course course = courseMapper.selectById(1386703799795679234L);
        //查询表2
        Course course1 = courseMapper.selectOne(new QueryWrapper<Course>().eq("id", 1386703805315383297L));
        System.out.println(course);
        System.out.println(course1);
    }

    /**
     * 测试水平分库-插入操作
     */
    @Test
    public void testInsert1(){
        //偶数的userId插入数据库m1,edu_db_1
        for (int i = 0; i < 5; i++) {
            Course course = Course.builder().userId(24L).status("normal").name("Kobe").build();
            System.out.println(course);
            courseMapper.insert(course);
        }
        //奇数的userId插入数据库m2,edu_db_2
        for (int i = 0; i < 5; i++) {
            Course course = Course.builder().userId(23L).status("normal").name("Jordan").build();
            System.out.println(course);
            courseMapper.insert(course);
        }
    }

    /**
     * 测试水平分库-查询操作
     */
    @Test
    public void testSelect2(){
        Course course1 = courseMapper.selectOne(new QueryWrapper<Course>().eq("id", 1386867089354190850L));
        System.out.println(course1);
        Course course2 = courseMapper.selectOne(new QueryWrapper<Course>().eq("id", 1386867089379356673L).eq("user_id",23));
        System.out.println(course2);
        Course course3 = courseMapper.selectOne(new QueryWrapper<Course>().eq("id", 1386867088699879426L));
        System.out.println(course3);
    }

    /**
     * 测试垂直分库-插入操作
     */
    @Test
    public void testInsert2(){
        //偶数的userId插入数据库m1,edu_db_1
        for (int i = 0; i < 5; i++) {
            Course course = Course.builder().userId(244L).status("normal").name("Kobe2").build();
            System.out.println(course);
            courseMapper.insert(course);
        }
        //插入数据库m3的t_user表
        for (int i = 0; i < 5; i++) {
            User user = User.builder().username("zeng").status("prohibit").build();
            userMapper.insert(user);
        }
        //奇数的userId插入数据库m2,edu_db_2
        for (int i = 0; i < 5; i++) {
            Course course = Course.builder().userId(233L).status("normal").name("Jordan2").build();
            System.out.println(course);
            courseMapper.insert(course);
        }
    }




}
