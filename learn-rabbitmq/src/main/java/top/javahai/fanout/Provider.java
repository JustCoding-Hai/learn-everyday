package top.javahai.fanout;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import top.javahai.utils.RabbitMQUtils;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @author Hai
 * @date 2020/9/13 - 10:36
 */
public class Provider {
    public static void main(String[] args) throws IOException, TimeoutException {
        Connection connection = RabbitMQUtils.getConnection();
        Channel channel = connection.createChannel();
        //声明交换价
        /*
        参数：exchange-交换机的名字，fanout-交换机的类型
         */
        channel.exchangeDeclare("logs","fanout");
        channel.basicPublish("logs","",null,"Hello EveryOne!".getBytes());
        RabbitMQUtils.closeConnnectionAndChannel(connection,channel);

    }
}
