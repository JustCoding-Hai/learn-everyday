package com.example.learnredis.jedis;

import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.awt.*;

/**
 * @author Hai
 * @program: learn-everyday
 * @description:
 * @create 2021/4/8 - 22:53
 **/
public class Redis {
    private JedisPool jedisPool;

    /**
     * 返回Redis对象
     */
    public Redis(){
        GenericObjectPoolConfig config = new GenericObjectPoolConfig();
        //连接池最大空闲数
        config.setMaxIdle(300);
        //最大连接数
        config.setMaxTotal(1000);
        //连接最大等待时间
        config.setMaxWaitMillis(30000);
        //在空闲时检查有效性
        config.setTestOnBorrow(true);
        jedisPool=new JedisPool(config,"39.108.169.57",6379,30000,"hjh123");
    }

    public void execute(CallWithJedis callWithJedis){
        Jedis jedis = null;
        try {
             jedis = jedisPool.getResource();
            callWithJedis.call(jedis);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            jedis.close();
        }
    }
}
