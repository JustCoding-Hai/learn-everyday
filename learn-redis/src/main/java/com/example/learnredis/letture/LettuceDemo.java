package com.example.learnredis.letture;

import io.lettuce.core.RedisClient;
import io.lettuce.core.api.StatefulRedisConnection;
import io.lettuce.core.api.sync.RedisCommands;

import java.time.temporal.ValueRange;

/**
 * @author Hai
 * @program: learn-everyday
 * @description:
 * @create 2021/4/8 - 23:42
 **/
public class LettuceDemo {
    public static void main(String[] args) {
        RedisClient redisClient = RedisClient.create("redis://hjh123@39.108.169.57");
        StatefulRedisConnection<String, String> connect = redisClient.connect();
        RedisCommands<String, String> sync = connect.sync();
        sync.set("say","hai");
        String name = sync.get("say");
        System.out.println(name);

    }
}
