package top.javahai.dead;

import com.rabbitmq.client.*;
import top.javahai.utils.RabbitMQUtils;

import java.io.IOException;
import java.util.HashMap;
import java.util.concurrent.TimeoutException;

/**
 * @author Hai
 * @program: learn-everyday
 * @description:
 * @create 2022/1/2 - 12:51
 **/
public class Consumer02 {
    private static final String DEAD_EXCHANGE = "dead_exchange";
    private static final String NORMAL_DEAD_LETTER_ROUTING_KEY = "normal_dead_letter_routing_key";


    public static void main(String[] args) throws IOException, TimeoutException {
        Connection connection = RabbitMQUtils.getConnection();
        Channel channel = connection.createChannel();
        channel.exchangeDeclare(DEAD_EXCHANGE, BuiltinExchangeType.DIRECT);
        //声明死信队列,绑定到死信交换机
        String deadQueue= "dead-queue";
        channel.queueDeclare(deadQueue,false,false,false, null );
        channel.queueBind(deadQueue, DEAD_EXCHANGE, NORMAL_DEAD_LETTER_ROUTING_KEY);
        channel.basicConsume(deadQueue, true, new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                System.out.println("Consumer02接收死信队列的消息:"+new String(body));
            }
        });
    }
}
