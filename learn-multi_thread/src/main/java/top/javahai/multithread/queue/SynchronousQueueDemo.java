package top.javahai.multithread.queue;

import java.sql.Time;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.TimeUnit;

/**
 * @author Hai
 * @program: learn-everyday
 * @description: SynchronousQueue Demo
 * @create 2021/2/20 - 18:03
 **/
public class SynchronousQueueDemo {
    public static void main(String[] args) {
        SynchronousQueue<String> synchronousQueue = new SynchronousQueue<>();
        new Thread(()->{
            try {
                System.out.println(Thread.currentThread().getName()+" put 1");
                synchronousQueue.put("1");
                System.out.println(Thread.currentThread().getName()+" put 2");
                synchronousQueue.put("2");
                System.out.println(Thread.currentThread().getName()+" put 3");
                synchronousQueue.put("3");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

        new Thread(()->{
            try {
                System.out.println(Thread.currentThread().getName()+"=>"+synchronousQueue.take());
                System.out.println(Thread.currentThread().getName()+"=>"+synchronousQueue.take());
                System.out.println(Thread.currentThread().getName()+"=>"+synchronousQueue.take());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

    }
}
