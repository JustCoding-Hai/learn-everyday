package top.javahai.multithread.collection;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author Hai
 * @program: learn-everyday
 * @description: 线程安全的Map
 * @create 2021/2/19 - 23:08
 **/
public class MapDemo {
    public static void main(String[] args) {
        HashMap<String, String> hashMap = new HashMap<>();
        Map<String, String> synchronizedMap = Collections.synchronizedMap(hashMap);
        for (int i = 0; i < 30; i++) {
            new Thread(()->{
                synchronizedMap.put(Thread.currentThread().getName(), UUID.randomUUID().toString().substring(0,5));
                System.out.println(synchronizedMap);
            }).start();
        }
        ConcurrentHashMap<String, String> concurrentHashMap = new ConcurrentHashMap<>();
        for (int i = 0; i < 30; i++) {
            new Thread(()->{
                concurrentHashMap.put(Thread.currentThread().getName(), UUID.randomUUID().toString().substring(0,5));
                System.out.println(concurrentHashMap);
            }).start();
        }
    }
}
