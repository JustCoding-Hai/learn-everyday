package top.javahai;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author Hai
 * @date 2020/9/14 - 22:48
 */
@SpringBootTest(classes = RabbitmqSpringbootApplication.class)
@RunWith(SpringRunner.class)
public class RabbitMQTest {

    @Autowired
    RabbitTemplate rabbitTemplate;
    //默认生产者发送消息，不会自动创建队列

    /**
     * 测试简单的Hello World
     */
    @Test
    public void testHello(){
        rabbitTemplate.convertAndSend("hello","hello world!");
    }

    /**
     * Test Task Model
     */
    @Test
    public void testTask(){
        for (int i = 0; i < 10; i++) {

            rabbitTemplate.convertAndSend("task","I am task model"+i);
        }
    }
    /**
     * Test Fanout Model
     */
    @Test
    public void testFanout(){
        rabbitTemplate.convertAndSend("logs","","I am Fanout message");
    }
    /**
     * Test Routing Model
     */
    @Test
    public void testRouting(){
        rabbitTemplate.convertAndSend("directs","error","I am a error message");
        rabbitTemplate.convertAndSend("directs","info","I am a info message");
    }

    /**
     * Test Topic Model
     */
    @Test
    public void testTopic(){
        rabbitTemplate.convertAndSend("topics","user.name","this is user.name");
        rabbitTemplate.convertAndSend("topics","user.name.pwd","this is user.name.pwd");
        rabbitTemplate.convertAndSend("topics","user","this is user");
        rabbitTemplate.convertAndSend("topics","user.","this is user.");
    }
}
