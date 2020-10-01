package top.javahai.helloword;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.MessageProperties;
import top.javahai.utils.RabbitMQUtils;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * “Hello World”模型-直连
 * 生产者
 * @author Hai
 * @date 2020/9/12 - 20:18
 */
public class Provider {
    public static void main(String[] args) {
        Connection connection = null;
        Channel channel=null;
        try {
            connection = RabbitMQUtils.getConnection();
            //创建通道
            channel= connection.createChannel();
            //定义通道连接的队列
            /**
             * queueDeclare方法：绑定一个队列，如果队列不存在就新建一个
             * 参数：
             *   1.queue 队列名称
             *   2.durable：是否持久化该队列，true则重启RabbitMQ服务后队列仍然存在，队列中的消息是否持久化需要另外设置
             *   3.exclusive：队列是否只允许一个通道Channel建立连接
             *   4.autoDelete：队列中的消息被消费后是否自动删除队列
             *   5.arguments：其他参数
             */
            channel.queueDeclare("hello",true,false,false,null);
            /**
             * basicPublish方法：发布消息
             * 参数：
             * 1。exchange：定义的交换机
             * 2.routingKey：队列名称
             * 3.props：设置消息的属性，如MessageProperties.PERSISTENT_TEXT_PLAIN，定义持久化消息
             * 4.body：消息内容
             */
            //发布消息
            channel.basicPublish("","hello", MessageProperties.PERSISTENT_TEXT_PLAIN,"hello rabbitmq".getBytes());

        } catch (IOException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }finally {
            //关闭通道和连接
            RabbitMQUtils.closeConnnectionAndChannel(connection,channel);
        }
    }
}
