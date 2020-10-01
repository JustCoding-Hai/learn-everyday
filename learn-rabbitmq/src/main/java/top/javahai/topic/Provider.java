package top.javahai.topic;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import top.javahai.utils.RabbitMQUtils;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @author Hai
 * @date 2020/9/14 - 22:06
 */
public class Provider {
    public static void main(String[] args) throws IOException, TimeoutException {
        Connection connection = RabbitMQUtils.getConnection();
        Channel channel = connection.createChannel();
        //声明交换机类型为topic，使用动态路由
        channel.exchangeDeclare("topics","topic");
        //发布消息
        channel.basicPublish("topics","user.save",null,"I am [user.save] routingKey".getBytes());
        channel.basicPublish("topics","user.save.id",null,"I am [user.save.id] routingKey".getBytes());
        channel.basicPublish("topics","user",null,"I am [user] routingKey".getBytes());
        channel.basicPublish("topics","user.",null,"I am [user.] routingKey".getBytes());
        channel.basicPublish("topics","username",null,"I am [username] routingKey".getBytes());
        channel.basicPublish("topics","user logout",null,"I am [user logout] routingKey".getBytes());
        RabbitMQUtils.closeConnnectionAndChannel(connection,channel);
    }
}
