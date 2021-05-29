package com.example.learnredis.queue;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import redis.clients.jedis.Jedis;

import java.util.Date;
import java.util.Set;
import java.util.UUID;

/**
 * @author Hai
 * @program: learn-everyday
 * @description: 延时消息队列
 * @create 2021/5/29 - 13:33
 **/
public class DelayMessageQueue {
    private Jedis jedis;
    /**
     * 队列名称
     */
    private String queue;

    public DelayMessageQueue(Jedis jedis, String queue) {
        this.jedis = jedis;
        this.queue = queue;
    }


    /**
     * 消息入队
     * @param data 要发送的消息
     */
    public void enqueue(Object data){
        QueueMessage queueMessage = new QueueMessage();
        queueMessage.setId(UUID.randomUUID().toString());
        queueMessage.setData(data);
        try{
            String s = new ObjectMapper().writeValueAsString(queueMessage);
            System.out.println(new Date()+"push:"+s);
            //消息发送，score延迟5s
            jedis.zadd(queue,System.currentTimeMillis()+5000,s);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    /**
     * 消息消费
     */
    public void loop() throws InterruptedException, JsonProcessingException {
        while (!Thread.interrupted()){
            //读取score在0到当前时间戳之间的消息
            Set<String> zrange = jedis.zrangeByScore(queue, 0, System.currentTimeMillis(), 0, 1);
            if (zrange.isEmpty()){
                //如果消息空的，就休息500ms再继续执行
                Thread.sleep(500);
                continue;
            }
            //如果读取到了消息，则直接读取消息出来
            String next = zrange.iterator().next();
            if (jedis.zrem(queue,next)>0){
                //抢到了就处理业务
                QueueMessage queueMessage = new ObjectMapper().readValue(next, QueueMessage.class);
                System.out.println("receice message:"+queueMessage);
            }
        }

    }
}
