package com.example.learnredis.jedis;

import io.lettuce.core.ConnectionEvents;
import redis.clients.jedis.Jedis;

/**
 * @author Hai
 * @program: learn-everyday
 * @description: 测试Jedis
 * @create 2021/4/8 - 20:40
 **/
public class JedisTest {
    public static void main(String[] args) {
        //1.构造Jedis对象
        Jedis jedis = new Jedis("39.108.169.57");
        //2.密码认证
        jedis.auth("hjh123");
        //3.测试是否连接成功
        String pong=jedis.ping();
        //4.返回pong表示连接成功
        System.out.println(pong);
    }
}
