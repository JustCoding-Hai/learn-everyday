package com.example.learnredis.jedis;

import com.sun.scenario.effect.impl.sw.java.JSWEffectPeer;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/**
 * @author Hai
 * @program: learn-everyday
 * @description:
 * @create 2021/4/8 - 20:50
 **/
public class JedisPoolTest {
    public static void main(String[] args) {
        //1.构建Jedis连接池
        JedisPool pool =new JedisPool("39.108.169.57",6379);
        Jedis jedis = null;
        try {
            //2.从连接池中获取Jedis连接
            jedis = pool.getResource();
            jedis.auth("hjh123");
            //3.Jedis操作
            String pong = jedis.ping();
            System.out.println(pong);
            //4.归还连接
            jedis.close();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if (jedis!=null){
                jedis.close();
            }
        }


    }
}
