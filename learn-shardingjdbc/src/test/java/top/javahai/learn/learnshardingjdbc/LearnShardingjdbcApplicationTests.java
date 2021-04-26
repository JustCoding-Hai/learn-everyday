package top.javahai.learn.learnshardingjdbc;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import top.javahai.learn.learnshardingjdbc.Entity.Course;
import top.javahai.learn.learnshardingjdbc.dao.CourseMapper;

import javax.xml.ws.Action;

@RunWith(SpringRunner.class)
@SpringBootTest
class LearnShardingjdbcApplicationTests {

    @Autowired
    CourseMapper courseMapper;

    @Test
    void contextLoads() {
    }

    @Test
    public void testInsert(){
        for (int i = 0; i < 10; i++) {
            Course course = new Course();
            course.setName("zhang"+i);
            course.setUserId(100L);
            course.setStatus("normal");
            courseMapper.insert(course);
        }
    }


    @Test
    public void testSelect(){
        //查询表1
        Course course = courseMapper.selectById(1386703799795679234L);
        //查询表2
        Course course1 = courseMapper.selectOne(new QueryWrapper<Course>().eq("id", 1386703805315383297L));
        System.out.println(course);
        System.out.println(course1);
    }

}
