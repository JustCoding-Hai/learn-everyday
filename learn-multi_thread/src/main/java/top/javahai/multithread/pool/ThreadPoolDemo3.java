package top.javahai.multithread.pool;

import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author Hai
 * @program: learn-everyday
 * @description: 手动创建线程池
 * @create 2021/2/20 - 18:54
 **/
public class ThreadPoolDemo3 {
    public static void main(String[] args) {
        ThreadPoolExecutor threadPool = null;
        try {
            threadPool=new ThreadPoolExecutor(2,5,3, TimeUnit.SECONDS,
                    new LinkedBlockingDeque<>(3),
                    Executors.defaultThreadFactory(),
                    new ThreadPoolExecutor.AbortPolicy());

            for (int i = 0; i < 9; i++) {
                threadPool.execute(()->{
                    System.out.println(Thread.currentThread().getName()+"create");
                });
            }
        }finally {
            threadPool.shutdown();
        }


    }

}
