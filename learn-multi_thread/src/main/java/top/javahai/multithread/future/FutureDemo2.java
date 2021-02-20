package top.javahai.multithread.future;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * @author Hai
 * @program: learn-everyday
 * @description: 有返回值的回调
 * @create 2021/2/21 - 0:05
 **/
public class FutureDemo2 {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        CompletableFuture<Integer> completableFuture=CompletableFuture.supplyAsync(()->{
            System.out.println(Thread.currentThread().getName()+"=> Integer");
            int i=1/0;
            return 1024;
        });
        Integer result = completableFuture.whenComplete((t, u) -> {
            //正常返回结果
            System.out.println("返回结果:" + t);
            //错误信息
            System.out.println("错误信息:" + u);
        }).exceptionally((e) -> {
            //发生异常的处理
            System.out.println(e.getMessage());
            return 404;
        }).get();
        System.out.println(result);
    }
}
