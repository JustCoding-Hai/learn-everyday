package com.example.learnredis.jedis;

import redis.clients.jedis.params.SetParams;

import java.util.concurrent.Callable;

/**
 * @author Hai
 * @program: learn-everyday
 * @description:
 * @create 2021/4/9 - 8:30
 **/
public class LockTest03 {
    public static void main(String[] args) {
        Redis redis=new Redis();
        redis.execute(jedis -> {
            String set = jedis.set("k1", "v1",new SetParams().nx().ex(20));
            //没有人占位
            if("OK".equals(set)){
                jedis.set("say","hi");
                String name=jedis.get("say");
                System.out.println(name);
                //释放锁
//                jedis.del("k1");
            }else{
                //有人占位，放弃或者稍后重试
                System.out.println("锁已被占用！");
            }
        });
    }
}
