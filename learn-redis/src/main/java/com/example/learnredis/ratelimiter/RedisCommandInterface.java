package com.example.learnredis.ratelimiter;

import io.lettuce.core.dynamic.Commands;
import io.lettuce.core.dynamic.annotation.Command;

import java.util.List;

/**
 * @author Hai
 * @program: learn-everyday
 * @description: 自定义执行的redis命令
 * @create 2021/5/30 - 23:58
 **/
public interface RedisCommandInterface extends Commands {
    /**
     *自定义执行CL.THROTTLE命令
     * @param key
     * @param init 漏斗的容量
     * @param count 时间窗口内可以操作的次数
     * @param period 时间窗口
     * @param quota 每次允许通过的数量
     * @return
     */
    @Command("CL.THROTTLE ?0 ?1 ?2 ?3 ?4")
    List<Object> throttle(String key, Long init, Long count, Long period, Long quota);
}
