package top.javahai;

import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @author Hai
 * @date 2020/9/14 - 23:59
 */
@Component
public class TopicConsumer {
    @RabbitListener(bindings= @QueueBinding(
            value = @Queue,
            key = {"user.*"},
            exchange = @Exchange(type = "topic",name = "topics")
    ))
    public void consume01(String message){
        System.out.println("consumer 01 handle message :"+message);
    }
    @RabbitListener(bindings = @QueueBinding(
            value = @Queue,
            key = {"user.#"},
            exchange = @Exchange(type = "topic",name="topics")
    ))
    public void consume02(String message){
        System.out.println("consumer 02 handle message :"+message);
    }
}
