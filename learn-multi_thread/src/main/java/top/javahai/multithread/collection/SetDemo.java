package top.javahai.multithread.collection;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * @author Hai
 * @program: learn-everyday
 * @description: 线程安全的Set集合
 * @create 2021/2/19 - 22:46
 **/
public class SetDemo {
    public static void main(String[] args) {
        HashSet<String> set = new HashSet<>();
        Set<String> synchronizedSet = Collections.synchronizedSet(set);
        CopyOnWriteArraySet<String> copyOnWriteArraySet = new CopyOnWriteArraySet<>();
        for (int i = 0; i < 50; i++) {
            new Thread(()->{
                synchronizedSet.add(UUID.randomUUID().toString().substring(0,5));
                System.out.println(synchronizedSet);
            }).start();
        }
        for (int i = 0; i < 50; i++) {
            new Thread(()->{
                copyOnWriteArraySet.add(UUID.randomUUID().toString().substring(0,5));
                System.out.println(copyOnWriteArraySet);
            }).start();
        }
    }
}
