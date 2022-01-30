package com.example.learnredis.ratelimiter;

import io.lettuce.core.RedisClient;
import io.lettuce.core.api.StatefulRedisConnection;
import io.lettuce.core.dynamic.Commands;
import io.lettuce.core.dynamic.RedisCommandFactory;

import java.util.List;

/**
 * @author Hai
 * @program: learn-everyday
 * @description: CL.THROTTLE 命令测试
 * @create 2021/5/30 - 23:57
 **/
public class ThrottleTest {
    public static void main(String[] args) {
        RedisClient redisClient = RedisClient.create("redis://hjh123@39.108.169.57");
        StatefulRedisConnection<String, String> connect = redisClient.connect();
        RedisCommandFactory redisCommandFactory = new RedisCommandFactory(connect);
        RedisCommandInterface commands = redisCommandFactory.getCommands(RedisCommandInterface.class);
        List<Object> list = commands.throttle("ethan-edit", 10L, 10L, 60L, 1L);
        System.out.println(list);

    }
}
