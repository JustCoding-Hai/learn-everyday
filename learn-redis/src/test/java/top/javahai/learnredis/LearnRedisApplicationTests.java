package top.javahai.learnredis;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

@SpringBootTest
class LearnRedisApplicationTests {

    @Test
    void contextLoads() {
    }

    @Autowired
    RedisTemplate redisTemplate;

    @Test
    public void testConnection(){
        ValueOperations ops = redisTemplate.opsForValue();
        ops.set("k1", "v1");
        Object k1 = ops.get("k1");
        ops.set("k3","v3");
        Object k3 = ops.get("k3");
        System.out.println(k1);
        System.out.println(k3);
    }
}
