package com.example.learnredis.jedis;

/**
 * @author Hai
 * @program: learn-everyday
 * @description:
 * @create 2021/4/9 - 8:30
 **/
public class LockTest02 {
    public static void main(String[] args) {
        Redis redis=new Redis();
        redis.execute(jedis -> {
            Long setnx = jedis.setnx("k1", "v1");
            //没有人占位
            if(setnx==1){
                jedis.expire("k1",5);
                jedis.set("say","hi");
                String name=jedis.get("say");
                System.out.println(name);
                //释放锁
                jedis.del("k1");
            }else{
                //有人占位，放弃或者稍后重试
            }
        });
    }
}
