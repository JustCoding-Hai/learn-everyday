package top.javahai.learn.learnshardingjdbc;

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
            Course course = Course.builder().id(i).name("zhang" + i).userId(i + "").status("normal").build();
            courseMapper.insert(course);
        }
    }

}
