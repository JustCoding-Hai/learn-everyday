package top.javahai.work;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import top.javahai.utils.RabbitMQUtils;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @author Hai
 * @date 2020/9/12 - 22:48
 */
public class Provider {
    public static void main(String[] args) throws IOException, TimeoutException {
        Connection connection = RabbitMQUtils.getConnection();
        Channel channel = connection.createChannel();
        channel.queueDeclare("work", false, false, false, null);
        for (int i = 0; i < 100; i++) {
            channel.basicPublish("","work",null,(i+"====>Hello！！！I am coming").getBytes());
        }
        RabbitMQUtils.closeConnnectionAndChannel(connection,channel);
    }
}
