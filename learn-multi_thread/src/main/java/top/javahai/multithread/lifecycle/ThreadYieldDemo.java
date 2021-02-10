package top.javahai.multithread.lifecycle;

/**
 * @author Hai
 * @program: learn-everyday
 * @description: 线程礼让Demo
 * @create 2021/2/10 - 17:57
 **/
public class ThreadYieldDemo {
    public static void main(String[] args) {
        Runnable runnable=()->{
            System.out.println(Thread.currentThread().getName()+"线程开始执行");
            Thread.yield();
            System.out.println(Thread.currentThread().getName()+"线程执行结束");
        };
        Thread thread01 = new Thread(runnable, "线程1");
        Thread thread02 = new Thread(runnable, "线程2");
        thread01.start();
        thread02.start();
    }
}
