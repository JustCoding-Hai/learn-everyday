package top.javahai.multithread.future;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * @author Hai
 * @program: learn-everyday
 * @description: 无返回值的回调
 * @create 2021/2/20 - 23:52
 **/
public class FutureDemo {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        CompletableFuture<Void> voidCompletableFuture = CompletableFuture.runAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + "runAsync=>Void");
        });
        //阻塞获取结果，直到线程执行完毕
        voidCompletableFuture.get();
        System.out.println("线程执行结束");
    }



}
