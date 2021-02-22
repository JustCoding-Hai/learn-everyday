package top.javahai.multithread.lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author Hai
 * @program: learn-everyday
 * @description: 自旋锁测试
 * @create 2021/2/22 - 12:21
 **/
public class SpinLockTest {
    public static void main(String[] args) {

        Phone3 phone = new Phone3();
        for (int i = 0; i < 3; i++) {
            new Thread(()->{
                phone.sms();
            }).start();
        }

        for (int i = 0; i < 3; i++) {
            new Thread(()->{
                phone.call();
            }).start();
        }
    }

}

class Phone3{
    SpinLockDemo lock=new SpinLockDemo();

    public void sms(){
        lock.mylock();
        System.out.println(Thread.currentThread().getName()+":lock");
        try {
            System.out.println(Thread.currentThread().getName()+":sms");
        }finally {
            lock.myUnlock();
        }

    }

    public void call() {
        lock.mylock();
        System.out.println(Thread.currentThread().getName()+":lock");
        try {
            System.out.println(Thread.currentThread().getName()+":call");

        } finally {
            lock.myUnlock();
        }
    }
}
