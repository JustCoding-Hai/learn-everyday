package top.javahai.multithread.assist;

import java.util.concurrent.CountDownLatch;

/**
 * @author Hai
 * @program: learn-everyday
 * @description: CountDownLatch的相关Demo
 * @create 2021/2/20 - 11:52
 **/
public class CountDownLatchDemo {
    public static void main(String[] args) throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(6);

        for (int i = 0; i < 6; i++) {
            new Thread(()->{
                System.out.println(Thread.currentThread().getName()+"-Go Out");
                countDownLatch.countDown();
            }).start();
        }
        //等待计数器归零
        countDownLatch.await();
        System.out.println("close Door");

    }
}
