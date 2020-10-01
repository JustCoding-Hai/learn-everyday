package top.javahai;

import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @author Hai
 * @date 2020/9/14 - 23:19
 */
@Component
public class FanoutConsumer {
    @RabbitListener(bindings = @QueueBinding(
            value = @Queue,//创建临时队列
            exchange = @Exchange(name="logs",type = "fanout")//绑定交换机
    ))
    public void consumer01(String message){
        System.out.println("consumer01 handle message:"+message);
    }

    @RabbitListener(bindings = @QueueBinding(
            value = @Queue,
            exchange = @Exchange(name="logs",type = "fanout")
    ))
    public void consumer02(String message){
        System.out.println("consumer02 handle message:"+message);
    }
}
