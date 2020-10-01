package top.javahai.work;

import com.rabbitmq.client.*;
import top.javahai.utils.RabbitMQUtils;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @author Hai
 * @date 2020/9/12 - 22:48
 */
public class Consumer01 {
    public static void main(String[] args) throws IOException, TimeoutException {
        Connection connection = RabbitMQUtils.getConnection();
        Channel channel = connection.createChannel();
        channel.queueDeclare("work",false,false,false,null);
        //设置一次只消费一个消息
        channel.basicQos(1);
        /*
         *实现能者多劳：
         * 取消自动确定机制
         * 设置一次只消费一个消息
         * 手动确认消息
         */
        channel.basicConsume("work",false,new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                System.out.println("No.1 Consumer01 has handled message:"+new String(body));
                //手动确认消息
                channel.basicAck(envelope.getDeliveryTag(),false);
            }
        });
    }
}
