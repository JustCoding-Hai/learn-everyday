package com.example.learnredis.scan;

import com.example.learnredis.jedis.Redis;

/**
 * @author Hai
 * @program: learn-everyday
 * @description:
 * @create 2021/6/7 - 0:04
 **/
public class ScanTest {
    public static void main(String[] args) {
        Redis redis = new Redis();
        redis.execute(jedis -> {
            for (int i = 0; i < 1000; i++) {
                jedis.set("k"+i,"v"+i);
            }
        });
    }
}
