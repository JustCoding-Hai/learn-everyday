package com.example.learnredis.ratelimiter;

import com.example.learnredis.jedis.Redis;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.Pipeline;
import redis.clients.jedis.Response;

/**
 * @author Hai
 * @program: learn-everyday
 * @description: 简单限流实现
 * @create 2021/5/30 - 16:31
 **/
public class RateLimiter {
    private Jedis jedis;

    public RateLimiter(Jedis jedis) {
        this.jedis = jedis;
    }

    /**
     * 限流方法
     * @param user 操作的用户
     * @param action 用户的操作
     * @param period 时间窗口，限流的周期,单位为秒
     * @param maxCount 限流的次数
     * @return
     */
    public boolean isAllowed(String user,String action,int period,int maxCount){
        //1. 数据用zset保存，首先生成一个key
        String key=user+"-"+action;
        //2. 获取当前时间戳
        long currentTimeMillis = System.currentTimeMillis();
        //3. 建立管道
        Pipeline pipeline = jedis.pipelined();
        pipeline.multi();
        //4. 将当前操作先存储
        pipeline.zadd(key,currentTimeMillis,String.valueOf(currentTimeMillis));
        //5. 移除时间窗之外的数据
        pipeline.zremrangeByScore(key,0,currentTimeMillis-period*1000);
        //6. 统计剩下的key
        Response<Long> response = pipeline.zcard(key);
        //7. 将当前key设置一个过期时间，过期时间就是时间窗
        pipeline.expire(key,period+1);
        //8. 关闭管道
        pipeline.exec();
        pipeline.close();
        //9. 比较时间窗内的操作数
        return response.get()<=maxCount;
    }

    public static void main(String[] args) {
        Redis redis = new Redis();
        redis.execute(j->{
            RateLimiter rateLimiter = new RateLimiter(j);
            for (int i = 0; i < 20; i++) {
                System.out.println(rateLimiter.isAllowed("Ethan","publish",5,3));
            }
        });

    }
}
