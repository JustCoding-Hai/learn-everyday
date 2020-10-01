package top.javahai;

import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @author Hai
 * @date 2020/9/14 - 23:08
 */
@Component
public class TaskConsumer {
    @RabbitListener(queuesToDeclare = @Queue(name="task"))
    public void consumer01(String message){
        System.out.println("consumer01 handle message:"+message);
    }
    @RabbitListener(queuesToDeclare = @Queue(name="task"))
    public void consumer02(String message){
        System.out.println("consumer02 handle message:"+message);
    }
}
