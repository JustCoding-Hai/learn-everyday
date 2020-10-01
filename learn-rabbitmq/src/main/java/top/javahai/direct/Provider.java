package top.javahai.direct;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import top.javahai.utils.RabbitMQUtils;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @author Hai
 * @date 2020/9/13 - 11:21
 */
public class Provider {
    private static final String exchange_name="logs_channel";
    public static void main(String[] args) throws IOException, TimeoutException {
        Connection connection = RabbitMQUtils.getConnection();
        Channel channel = connection.createChannel();
        //声明交换机
        channel.exchangeDeclare(exchange_name,"direct");
        //发布消息，指定Routing Key
        channel.basicPublish(exchange_name,"error",null,("This is a [error] message").getBytes());
        channel.basicPublish(exchange_name,"info",null,("This is a [info] message").getBytes());
        channel.basicPublish(exchange_name,"debug",null,("This is a [debug] message").getBytes());
        channel.basicPublish(exchange_name,"warn",null,("This is a [warn] message").getBytes());
        //释放资源
        RabbitMQUtils.closeConnnectionAndChannel(connection,channel);
    }
}
