package com.example.learnredis.bloomfilter;

import io.rebloom.client.Client;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import redis.clients.jedis.JedisPool;

/**
 * @author Hai
 * @program: learn-everyday
 * @description:
 * @create 2021/5/30 - 15:57
 **/
public class BloomFilter {
    public static void main(String[] args) {
        GenericObjectPoolConfig config = new GenericObjectPoolConfig();
        config.setMaxIdle(300);
        config.setMaxIdle(1000);
        config.setMaxWaitMillis(30000);
        config.setTestOnBorrow(true);
        JedisPool pool = new JedisPool(config, "localhost", 6379, 30000, "hjh123");
        Client client = new Client(pool);
        for (int i = 0; i < 100000; i++) {
            client.add("name","xiao"+i);
        }
        //检测数据是否存在
        boolean exists = client.exists("name", "123");
        System.out.println(exists);
    }
}
