package top.javahai.multithread.lock;

import org.springframework.aop.ThrowsAdvice;

import java.util.concurrent.atomic.AtomicReference;

/**
 * @author Hai
 * @program: learn-everyday
 * @description: 自旋锁Demo
 * @create 2021/2/22 - 12:05
 **/
public class SpinLockDemo {

    static AtomicReference<Thread> atomicReference = new AtomicReference<>();

    public void mylock(){
        Thread thread = Thread.currentThread();
        //自旋锁,如果Thread不为null（即已经有线程加锁了），则一直循环等待，直至前个线程解锁
        while (!atomicReference.compareAndSet(null,thread)){

        }
    }

    public void myUnlock(){
        Thread thread = Thread.currentThread();
        System.out.println(thread.getName()+"=>my unlock");
        atomicReference.compareAndSet(thread,null);
    }

    public static void main(String[] args) {

    }
}
