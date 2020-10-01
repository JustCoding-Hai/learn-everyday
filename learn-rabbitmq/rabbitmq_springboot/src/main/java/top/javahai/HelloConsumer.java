package top.javahai;

import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @author Hai
 * @date 2020/9/14 - 22:50
 */
@Component
//声明队列，没有则创建
@RabbitListener(queuesToDeclare = @Queue("hello"))
public class HelloConsumer {
    @RabbitHandler
    public void receive(String message){
        System.out.println("message:"+message);
    }

}
