package top.javahai.direct;

import com.rabbitmq.client.*;
import top.javahai.utils.RabbitMQUtils;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @author Hai
 * @date 2020/9/13 - 11:35
 */
public class Consumer02 {
    private static final String exchange_name="logs_channel";

    public static void main(String[] args) throws IOException, TimeoutException {
        Connection connection = RabbitMQUtils.getConnection();
        Channel channel = connection.createChannel();
        //声明交换机
        channel.exchangeDeclare(exchange_name,"direct");
        //创建临时队列
        String queue = channel.queueDeclare().getQueue();
        //绑定队列和交换机
        channel.queueBind(queue,exchange_name,"error");
        channel.queueBind(queue,exchange_name,"info");
        channel.queueBind(queue,exchange_name,"debug");
        channel.queueBind(queue,exchange_name,"warn");
        //消费消息
        channel.basicConsume(queue,true,new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                System.out.println("No.2 Consumer handle :"+new String(body));
            }
        });
    }
}
