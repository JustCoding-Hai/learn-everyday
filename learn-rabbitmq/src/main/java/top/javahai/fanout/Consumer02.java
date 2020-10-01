package top.javahai.fanout;

import com.rabbitmq.client.*;
import top.javahai.utils.RabbitMQUtils;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @author Hai
 * @date 2020/9/13 - 10:40
 */
public class Consumer02 {
    public static void main(String[] args) throws IOException, TimeoutException {
        Connection connection = RabbitMQUtils.getConnection();
        Channel channel = connection.createChannel();
        //绑定交换机
        channel.exchangeDeclare("logs","fanout");
        //创建临时队列
        String queue = channel.queueDeclare().getQueue();
        //将临时队列绑定到exchange交换机
        channel.queueBind(queue,"logs","");
        //处理消息
        channel.basicConsume(queue,true, new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                System.out.println("No.2 Consumer Handler the message:"+new String(body));
            }
        });

    }
}
