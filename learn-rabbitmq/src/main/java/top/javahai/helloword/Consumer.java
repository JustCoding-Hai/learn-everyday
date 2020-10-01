package top.javahai.helloword;

import com.rabbitmq.client.*;
import top.javahai.utils.RabbitMQUtils;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @author Hai
 * @date 2020/9/12 - 20:39
 */
public class Consumer {
    public static void main(String[] args) throws IOException, TimeoutException {
        //创建连接工厂
        Connection connection = RabbitMQUtils.getConnection();
        Channel channel = connection.createChannel();
        //连接到的队列
        channel.queueDeclare("hello",true,false,false,null);
        //使用DefaultConsumer来获取消息内容，autoAck：开启消息的自动确认机制
        channel.basicConsume("hello",true,new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                System.out.println("String Body:"+new String(body));
            }
        });
        //channel.close();
        //connection.close();

    }
}
