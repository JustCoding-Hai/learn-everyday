package com.example.learnredis.jedis;

/**
 * @author Hai
 * @program: learn-everyday
 * @description:
 * @create 2021/4/8 - 23:01
 **/
public class JedisPoolTest02 {
    public static void main(String[] args) {
        Redis redis = new Redis();
        redis.execute(jedis -> {
            System.out.println(jedis.ping());
        });
    }
}
