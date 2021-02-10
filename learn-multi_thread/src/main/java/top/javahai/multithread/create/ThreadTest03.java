package top.javahai.multithread.create;

import java.util.concurrent.*;

/**
 * @author Hai
 * @program: learn-everyday
 * @description: 实现Callable接口创建线程
 * @create 2021/2/10 - 16:18
 **/
public class ThreadTest03 implements Callable {
    @Override
    public Boolean call() throws Exception {
        for (int i = 0; i < 20; i++) {
            System.out.println("我在写代码"+i);
        }
        return true;
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ThreadTest03 threadTest03 = new ThreadTest03();
        //创建执行服务
        ExecutorService executorService = Executors.newFixedThreadPool(1);
        //提交执行
        Future<Boolean> future = executorService.submit(threadTest03);
        //获取结果
        Boolean result = future.get();
        System.out.println(result);
        for (int i = 0; i < 1000; i++) {
            System.out.println("我在学习多线程"+i);
        }
        executorService.shutdown();
    }
}
