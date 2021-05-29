package com.example.learnredis.hyperloglog;

import com.example.learnredis.jedis.Redis;

/**
 * @author Hai
 * @program: learn-everyday
 * @description: HyperLogLog测试使用
 * @create 2021/5/29 - 17:53
 **/
public class HyperloglogTest {
    public static void main(String[] args) {
        Redis redis = new Redis();
        redis.execute(jedis -> {
            for (int i = 0; i < 1000; i++) {
                jedis.pfadd("uv","u"+i,"u"+(i+1));
            }
            long uv = jedis.pfcount("uv");
            System.out.println(uv);
        });
    }
}
