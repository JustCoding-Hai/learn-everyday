package top.javahai.multithread.lock;

import com.sun.scenario.animation.shared.FiniteClipEnvelope;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author Hai
 * @program: learn-everyday
 * @description: 可重入锁-lock实现
 * @create 2021/2/22 - 11:59
 **/
public class Demo02 {
    public static void main(String[] args) {
        Phone2 phone = new Phone2();
        new Thread(()->{
            phone.sms();
        }).start();

        new Thread(()->{
            phone.sms();
        }).start();

    }

}


class Phone2{

    Lock lock=new ReentrantLock();

    public void sms(){
        lock.lock();
        try {
            System.out.println(Thread.currentThread().getName()+":sms");
            call();
        }finally {
            lock.unlock();
        }

    }

    private void call() {
        lock.lock();
        try {
            System.out.println(Thread.currentThread().getName()+":call");
        } finally {
            lock.unlock();
        }
    }
}
