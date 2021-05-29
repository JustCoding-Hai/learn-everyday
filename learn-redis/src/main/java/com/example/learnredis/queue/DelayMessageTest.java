package com.example.learnredis.queue;

import com.example.learnredis.jedis.Redis;
import com.fasterxml.jackson.core.JsonProcessingException;

/**
 * @author Hai
 * @program: learn-everyday
 * @description: 延迟消息队列测试
 * @create 2021/5/29 - 13:46
 **/
public class DelayMessageTest {
    public static void main(String[] args) {
        Redis redis = new Redis();
        redis.execute(jedis -> {
            //构造一个消息队列
            DelayMessageQueue queue = new DelayMessageQueue(jedis, "test-delay-queue");
            //构造消息生产者
            Thread thread1 = new Thread(()->{
                for (int i=0;i<5;i++){
                    queue.enqueue("你好"+i);
                }
            });

            //构造消息消费者
            Thread thread2 = new Thread(()->{
                try {
                    queue.loop();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (JsonProcessingException e) {
                    e.printStackTrace();
                }
            });
            thread1.start();
            thread2.start();
            //休息七秒后停止程序
            Thread.sleep(7000);
            thread2.interrupt();
        });


    }
}
