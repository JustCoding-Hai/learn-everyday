package top.javahai.multithread.lock;

import org.springframework.aop.ThrowsAdvice;

/**
 * @author Hai
 * @program: learn-everyday
 * @description: 可重入锁DEMO
 * @create 2021/2/22 - 11:48
 **/
public class Demo01 {
    public static void main(String[] args) {
        Phone phone = new Phone();
        new Thread(()->{
            phone.sms();
        }).start();

        new Thread(()->{
            phone.sms();
        }).start();
    }
}


class Phone{
    public synchronized void sms(){
        System.out.println(Thread.currentThread().getName()+":sms");
        call();
    }

    private synchronized void call() {
        System.out.println(Thread.currentThread().getName()+":call");
    }
}
