package top.javahai.improve;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.ConfirmCallback;
import com.rabbitmq.client.Connection;
import top.javahai.utils.RabbitMQUtils;

import java.io.IOException;
import java.io.OutputStream;
import java.util.UUID;
import java.util.concurrent.ConcurrentNavigableMap;
import java.util.concurrent.ConcurrentSkipListMap;
import java.util.concurrent.TimeoutException;

/**
 * @author Hai
 * @program: learn-everyday
 * @description:
 * @create 2021/12/28 - 23:18
 **/
public class PublishConfirmTest {

    private static final int MESSAGE_COUNT = 1000;


    /**
     * 单个发布确认,耗时41451ms
     */
    public static void publishMessageIndividually() throws IOException, TimeoutException, InterruptedException {
        Connection connection = RabbitMQUtils.getConnection();
        Channel channel = connection.createChannel();
        //创建队列
        String queueName = UUID.randomUUID().toString();
        channel.queueDeclare(queueName, false, false, false, null);
        //开启发布确认
        channel.confirmSelect();
        long begin = System.currentTimeMillis();
        for (int i = 0; i < MESSAGE_COUNT; i++) {
            String message = i + " ";
            channel.basicPublish("", queueName, null, message.getBytes());
            //等待确认
            boolean flag = channel.waitForConfirms();
            if (flag) {
                System.out.println("消息发送成功");
            }
        }
        long end = System.currentTimeMillis();
        System.out.println("发布" + MESSAGE_COUNT + "个单独确认的消息，耗时" + (end - begin) + "ms");
        RabbitMQUtils.closeConnnectionAndChannel(connection, channel);
    }

    /**
     * 批量消息发布确认，自行代码实现。 耗时584ms
     */
    public static void publishMessageBatch() throws IOException, TimeoutException, InterruptedException {
        Connection connection = RabbitMQUtils.getConnection();
        Channel channel = connection.createChannel();
        //创建队列
        String queueName = UUID.randomUUID().toString();
        channel.queueDeclare(queueName, false, false, false, null);
        //开启发布确认
        channel.confirmSelect();
        //批量确认消息大小阈值
        int batchSize = 100;
        int unConfirmMessageCount = 0;
        long begin = System.currentTimeMillis();
        for (int i = 0; i < MESSAGE_COUNT; i++) {
            String message = i + " ";
            channel.basicPublish("", queueName, null, message.getBytes());
            unConfirmMessageCount++;
            if (unConfirmMessageCount == batchSize) {
                //等待所有的消息都确认发布成功，再继续发布
                channel.waitForConfirms();
                unConfirmMessageCount=0;
            }
        }
        long end = System.currentTimeMillis();
        System.out.println("发布" + MESSAGE_COUNT + "个批量确认的消息，耗时" + (end - begin) + "ms");
        RabbitMQUtils.closeConnnectionAndChannel(connection, channel);
    }

    /**
     * 异步发布确认. 耗时584ms
     */
    public static void publishMessageAsync() throws IOException, TimeoutException, InterruptedException {
        Connection connection = RabbitMQUtils.getConnection();
        Channel channel = connection.createChannel();
        //创建队列
        String queueName = UUID.randomUUID().toString();
        channel.queueDeclare(queueName, false, false, false, null);
        //开启发布确认
        channel.confirmSelect();

        /*
         * 线程安全有序的一个哈希表，适用于高并发的情况
         * 1. 轻松得将序号与消息进行关联
         * 2. 可以通过序列号轻松批量删除条目
         * 3. 支持高并发访问
         */
        ConcurrentSkipListMap<Long, String> outStandingConfirms = new ConcurrentSkipListMap<>();
        /*
         *  确认收到消息的回调
         *  参数说明：
         *  sequenceNumber - 消息序列号
         *  multiple -true可以确认小于等于当前序列号的消息，false确认当前序列号消息
         */
        ConfirmCallback ackCallback = (sequenceNumber, multiple) -> {
            if (multiple) {
                ConcurrentNavigableMap<Long, String> confirmed = outStandingConfirms.headMap(sequenceNumber, true);
                //清除部分未确认消息
                confirmed.clear();
            } else {
                //只清除当前序列号的消息
                outStandingConfirms.remove(sequenceNumber);
            }

        };
        /*
          未收到消息的回调
         */
        ConfirmCallback nackCallback = (sequenceNumber, multiple) -> {
            String message = outStandingConfirms.get(sequenceNumber);
            System.out.println("发布的消息" + message + "未被确认,序列号" + sequenceNumber);
        };
        /*
          添加异步确认的监视器
          ackCallback - 确认消息的回调
          nackCallback - 未收到消息的回调
         */
        channel.addConfirmListener(ackCallback, nackCallback);
        long begin = System.currentTimeMillis();
        for (int i = 0; i < MESSAGE_COUNT; i++) {
            String message = i + " ";
            /*
              channel.getNextPublishSeqNo()获取下一个消息的序列号
              通过序列号与消息体进行关联
             */
            outStandingConfirms.put(channel.getNextPublishSeqNo(), message);
            channel.basicPublish("", queueName, null, message.getBytes());
        }
        long end = System.currentTimeMillis();
        System.out.println("发布" + MESSAGE_COUNT + "个异步确认的消息，耗时" + (end - begin) + "ms");
        RabbitMQUtils.closeConnnectionAndChannel(connection, channel);
    }

    public static void main(String[] args) throws InterruptedException, TimeoutException, IOException {
//        publishMessageIndividually();
//        publishMessageBatch();
        publishMessageAsync();
    }

}
