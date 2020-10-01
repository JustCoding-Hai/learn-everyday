package top.javahai;

import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.support.BindStatus;

/**
 * @author Hai
 * @date 2020/9/14 - 23:50
 */
@Component
public class RountingConsumer {
    @RabbitListener(bindings = @QueueBinding(
            value = @Queue,//创建临时队列
            key = {"info","error"},//Routing Key
            exchange = @Exchange(name = "directs",type = "direct")
    ))
    public void consumer01(String message){
        System.out.println("consumer 01 handle message:"+message);
    }
    @RabbitListener(bindings = @QueueBinding(
            value = @Queue,
            key = {"error"},
            exchange = @Exchange(name="directs",type = "direct")
    ))
    public void consumer02(String message){
        System.out.println("Consumer 02 handle message:"+message);
    }
}
