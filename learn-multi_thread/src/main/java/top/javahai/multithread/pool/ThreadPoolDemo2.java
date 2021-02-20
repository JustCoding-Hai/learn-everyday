package top.javahai.multithread.pool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author Hai
 * @program: learn-everyday
 * @description:
 * @create 2021/2/20 - 18:14
 **/
public class ThreadPoolDemo2 {
    public static void main(String[] args) {
        ExecutorService threadPool1 = Executors.newSingleThreadExecutor();
        ExecutorService threadPool2 = Executors.newFixedThreadPool(3);
        ExecutorService threadPool3 = Executors.newCachedThreadPool();
        try {
            for (int i = 0; i < 10; i++) {
                threadPool1.execute(()->{
                    System.out.println(Thread.currentThread().getName()+" run");
                });
            }
        }finally {
            threadPool1.shutdown();
        }

        try {
            for (int i = 0; i < 10; i++) {
                threadPool2.execute(()->{
                    System.out.println(Thread.currentThread().getName()+" run");
                });
            }
        }finally {
            threadPool2.shutdown();
        }

        try {
            for (int i = 0; i < 10; i++) {
                threadPool3.execute(()->{
                    System.out.println(Thread.currentThread().getName()+" run");
                });
            }
        }finally {
            threadPool3.shutdown();
        }

    }
}
