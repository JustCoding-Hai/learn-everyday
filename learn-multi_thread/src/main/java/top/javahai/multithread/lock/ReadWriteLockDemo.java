package top.javahai.multithread.lock;

import com.sun.org.apache.bcel.internal.generic.NEW;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author Hai
 * @program: learn-everyday
 * @description: 读写锁Demo
 * @create 2021/2/20 - 13:26
 **/
public class ReadWriteLockDemo {
    public static void main(String[] args) {
        LockCache lockCache = new LockCache();
        for (int i = 0; i < 5; i++) {
            final String temp=i+"";
            new Thread(()->{
                lockCache.put(temp,temp);
            }).start();
        }
        for (int i = 0; i < 5; i++) {
            final String temp=i+"";
            new Thread(()->{
                lockCache.get(temp);
            }).start();
        }
    }

}
class LockCache{
    private volatile Map<String,Object> map=new HashMap<>();
    /**
     * 读写锁
     */
    private ReadWriteLock readWriteLock=new ReentrantReadWriteLock();

    public void put(String key,Object value){
        readWriteLock.writeLock().lock();
        try {
            System.out.println(Thread.currentThread().getName()+"写入"+key);
            map.put(key,value);
            System.out.println(Thread.currentThread().getName()+"写入OK");
        }finally {
            readWriteLock.writeLock().unlock();
        }
    }

    public void get(String key){
        readWriteLock.readLock().lock();
        try {
            System.out.println(Thread.currentThread().getName()+"读取"+key);
            System.out.println(map.get(key));
            System.out.println(Thread.currentThread().getName()+"读取OK");
        }finally {
            readWriteLock.readLock().unlock();
        }
    }
}
