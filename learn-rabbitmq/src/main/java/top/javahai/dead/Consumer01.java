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
public class Consumer01 {
    private static final String NORMAL_EXCHANGE = "normal_exchange";
    private static final String DEAD_EXCHANGE = "dead_exchange";
    private static final String NORMAL_ROUTING_KEY = "normal_routing_key";
    private static final String NORMAL_DEAD_LETTER_ROUTING_KEY = "normal_dead_letter_routing_key";

    public static void main(String[] args) throws IOException, TimeoutException {
        Connection connection = RabbitMQUtils.getConnection();
        Channel channel = connection.createChannel();
        //声明死信交换机和普通交换机，类型为direct
        channel.exchangeDeclare(NORMAL_EXCHANGE, BuiltinExchangeType.DIRECT);
        channel.exchangeDeclare(DEAD_EXCHANGE, BuiltinExchangeType.DIRECT);
        //声明死信队列,绑定到死信交换机
        String deadQueue= "dead-queue";
        channel.queueDeclare(deadQueue,false,false,false, null );
        channel.queueBind(deadQueue, DEAD_EXCHANGE, NORMAL_DEAD_LETTER_ROUTING_KEY);

        //声明普通队列，并绑定死信队列信息
        HashMap<String, Object> params = new HashMap<>();
        params.put("x-dead-letter-exchange", DEAD_EXCHANGE);
        params.put("x-dead-letter-routing-key", NORMAL_DEAD_LETTER_ROUTING_KEY);
        //设置队列的最大消息数
//        params.put("x-max-length", 6);
        String normalQueue = "normal-queue";
        channel.queueDeclare(normalQueue, false,false,false,params);
        channel.queueBind(normalQueue,NORMAL_EXCHANGE,NORMAL_ROUTING_KEY);
//        channel.basicConsume(normalQueue, true, new DefaultConsumer(channel){
//            @Override
//            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
//                System.out.println("Consumer01接收普通队列的消息:"+new String(body));
//            }
//        });
        /*
        模拟消息被拒绝
         */
        boolean autoAck = false;
        channel.basicConsume(normalQueue, autoAck, new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                String message = new String(body);
                if ("info5".equals(message)){
                    System.out.println("Consumer01接收普通队列的消息:"+ message);
                    channel.basicReject(envelope.getDeliveryTag(), false);
                }else{
                    System.out.println("Consumer01接收普通队列的消息:"+ message);
                    channel.basicAck(envelope.getDeliveryTag(), false);
                }
            }
        });
    }
}
