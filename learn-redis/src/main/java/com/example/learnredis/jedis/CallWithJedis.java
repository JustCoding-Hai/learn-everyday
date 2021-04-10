package com.example.learnredis.jedis;

import redis.clients.jedis.Jedis;

/**
 * @author Hai
 * @program: learn-everyday
 * @description:
 * @create 2021/4/8 - 22:52
 **/
@FunctionalInterface
public interface CallWithJedis {
    void call(Jedis jedis) throws InterruptedException;
}
