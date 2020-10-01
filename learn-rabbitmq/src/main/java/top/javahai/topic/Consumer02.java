package top.javahai.topic;

import com.rabbitmq.client.*;
import top.javahai.utils.RabbitMQUtils;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @author Hai
 * @date 2020/9/14 - 22:10
 */
public class Consumer02 {
    public static void main(String[] args) throws IOException, TimeoutException {
        Connection connection = RabbitMQUtils.getConnection();
        Channel channel = connection.createChannel();
        //声明交换机
        channel.exchangeDeclare("topics","topic");
        //创建临时队列
        String queue = channel.queueDeclare().getQueue();
        //绑定队列与交换机并设置交换机中动态路由
        channel.queueBind(queue,"topics","user.#");
        //消费消息
        channel.basicConsume(queue,true,new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                System.out.println("No.2 Consumer have consume message:"+new String(body));
            }
        });
    }
}
