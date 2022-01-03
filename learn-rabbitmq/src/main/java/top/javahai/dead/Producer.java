package top.javahai.dead;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import top.javahai.utils.RabbitMQUtils;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @author Hai
 * @program: learn-everyday
 * @description: 死信队列DEMO
 * @create 2022/1/2 - 12:40
 **/
public class Producer {

    private static final String NORMAL_EXCHANGE = "normal_exchange";
    private static final String NORMAL_ROUTING_KEY = "normal_routing_key";

    public static void main(String[] args) throws IOException, TimeoutException {
        Connection connection = RabbitMQUtils.getConnection();
        Channel channel = connection.createChannel();
        channel.exchangeDeclare(NORMAL_EXCHANGE, BuiltinExchangeType.DIRECT);
        //设置消息的TTL
//        AMQP.BasicProperties properties = new AMQP.BasicProperties().builder().expiration("10000").build();
        for (int i = 0; i < 10; i++) {
            String message = "info" + i;
            channel.basicPublish(NORMAL_EXCHANGE, NORMAL_ROUTING_KEY, null, message.getBytes());
            System.out.println("生产者发送消息：" + message);
        }
    }
}
