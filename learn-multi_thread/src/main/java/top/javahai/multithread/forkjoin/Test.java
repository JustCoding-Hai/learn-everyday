package top.javahai.multithread.forkjoin;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.stream.LongStream;

/**
 * @author Hai
 * @program: learn-everyday
 * @description:
 * @create 2021/2/20 - 23:15
 **/
public class Test {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        test1();//466
        test2();//147
        test3();
    }

    private static void test1(){
        long sum=0;
        long start=System.currentTimeMillis();
        for (long i = 0; i <=10_0000_0000; i++) {
            sum+=i;
        }
        long end=System.currentTimeMillis();
        System.out.println("sum="+sum+",时间："+(end-start));
    }

    private static void test2() throws ExecutionException, InterruptedException {
        long start=System.currentTimeMillis();
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        ForkJoinDemo task = new ForkJoinDemo(0, 10_0000_0000);
        ForkJoinTask<Long> submit = forkJoinPool.submit(task);
        long end=System.currentTimeMillis();
        Long sum = submit.get();
        System.out.println("sum="+sum+",时间："+(end-start));
    }


    private static void test3(){
        long start=System.currentTimeMillis();
        long sum=LongStream.rangeClosed(0,10_0000_0000).parallel().reduce(0,Long::sum);
        long end=System.currentTimeMillis();
        System.out.println("sum="+sum+",时间："+(end-start));
    }
}
