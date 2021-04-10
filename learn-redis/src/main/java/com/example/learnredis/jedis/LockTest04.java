package com.example.learnredis.jedis;

import redis.clients.jedis.params.SetParams;

import java.util.Arrays;
import java.util.Collections;
import java.util.UUID;

/**
 * @author Hai
 * @program: learn-everyday
 * @description:
 * @create 2021/4/9 - 22:31
 **/
public class LockTest04 {
    public static void main(String[] args) {
        Redis redis = new Redis();
        Thread thread1 = new Thread(() -> {
            redis.execute(jedis -> {
                //1.先获取一个随机字符串
                String value = UUID.randomUUID().toString();
                String k1;
                synchronized (LockTest04.class){
                  k1= jedis.set("k1", value, new SetParams().nx().ex(1));
                    //模拟耗时操作
                    Thread.sleep(3000);
                }
                //2.获取锁
                //3.判断是否成功获取到锁
                if ("OK".equals(k1)) {
                    //4.具体的业务操作
                    jedis.set("say", "hi");
                    System.out.println(jedis.get("say"));
                    //5.释放锁
                    jedis.evalsha("b8059ba43af6ffe8bed3db65bac35d452f8115d8", Arrays.asList("k1"), Arrays.asList(value));
                } else {
                    System.out.println("该锁已经被占用！");

            }
        });
        });
        thread1.setPriority(1);
        thread1.start();
        Thread thread2=new Thread(() -> {
            redis.execute(jedis -> {
                //1.先获取一个随机字符串
                String value = UUID.randomUUID().toString();
                //2.获取锁
                String k1;
                synchronized (LockTest04.class){
                    k1 = jedis.set("k1", value, new SetParams().nx().ex(30));
                }
                //3.判断是否成功获取到锁
                if ("OK".equals(k1)) {
                    //模拟耗时操作
                    Thread.sleep(3000);
                    //4.具体的业务操作
                    jedis.set("say", "hello");
                    System.out.println(jedis.get("say"));
                    //5.释放锁
                    jedis.evalsha("b8059ba43af6ffe8bed3db65bac35d452f8115d8", Arrays.asList("k1"), Arrays.asList(value));
                } else {
                    System.out.println("该锁已经被占用！");
                }
            });
        });
        thread2.setPriority(2);
        thread2.start();
    }
}
