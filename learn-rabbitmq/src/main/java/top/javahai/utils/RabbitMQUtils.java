package top.javahai.utils;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @author Hai
 * @date 2020/9/12 - 21:45
 */
public class RabbitMQUtils {
    //创建工厂,连接工厂是重量型的，重复创建，消耗性能
    private static ConnectionFactory connectionFactory;
    static {
        connectionFactory = new ConnectionFactory();
        connectionFactory.setHost("");
        connectionFactory.setPort(5672);
        connectionFactory.setUsername("");
        connectionFactory.setPassword("");
        connectionFactory.setVirtualHost("/");
    }

    //获取连接
    public static Connection getConnection() throws IOException, TimeoutException {
        return connectionFactory.newConnection();
    }
    //关闭连接和通道
    public static void closeConnnectionAndChannel(Connection connection, Channel channel){
        try {
            channel.close();
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}

