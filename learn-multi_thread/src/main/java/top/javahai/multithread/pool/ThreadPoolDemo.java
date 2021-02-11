package top.javahai.multithread.pool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @author Hai
 * @program: learn-everyday
 * @description: 线程池Demo
 * @create 2021/2/11 - 17:02
 **/
public class ThreadPoolDemo {

    public static void main(String[] args) {
        Runnable runnable=()-> System.out.println(Thread.currentThread());
        //创建线程池
        ExecutorService executorService = Executors.newFixedThreadPool(3);
        //启动线程
        executorService.execute(runnable);
        executorService.execute(runnable);
        executorService.execute(runnable);
        executorService.execute(runnable);
        executorService.execute(runnable);
        executorService.execute(runnable);
        //关闭线程池服务，结束未执行的任务
        executorService.shutdown();
    }
}
