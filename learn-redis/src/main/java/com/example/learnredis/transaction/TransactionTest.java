package com.example.learnredis.transaction;

import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.springframework.data.projection.TargetAware;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.Transaction;

import java.util.List;

/**
 * @author Hai
 * @program: learn-everyday
 * @description: 测试事务
 * @create 2021/6/8 - 23:39
 **/
public class TransactionTest {
    public static void main(String[] args) {
        GenericObjectPoolConfig config = new GenericObjectPoolConfig();
        //连接池最大空闲数
        config.setMaxIdle(300);
        //最大连接数
        config.setMaxTotal(1000);
        //连接最大等待时间
        config.setMaxWaitMillis(30000);
        //在空闲时检查有效性
        config.setTestOnBorrow(true);
        JedisPool jedisPool=new JedisPool(config,"39.108.169.57",6379,30000,"hjh123");
        Jedis jedis = jedisPool.getResource();
        Integer money = new TransactionTest().addMoney(jedis, "zhangsan", 300);
        System.out.println(money);
    }

    private Integer addMoney(Jedis jedis,String userId, Integer money){
        jedis.watch(userId);
        int v=Integer.parseInt(jedis.get(userId))+money;
        Transaction transaction = jedis.multi();
        transaction.set(userId,String.valueOf(v));
        List<Object> exec = transaction.exec();
        if (exec==null){
            System.out.println("userId事务操作被打断");
        }
        return Integer.parseInt(jedis.get(userId))+money;
    }
}
