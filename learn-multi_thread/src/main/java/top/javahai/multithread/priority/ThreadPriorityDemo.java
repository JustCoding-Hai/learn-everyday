package top.javahai.multithread.priority;

import org.omg.PortableServer.THREAD_POLICY_ID;

/**
 * @author Hai
 * @program: learn-everyday
 * @description: 线程优先级Demo
 * @create 2021/2/10 - 18:58
 **/
public class ThreadPriorityDemo {
    public static void main(String[] args) {
        Runnable runnable=()-> System.out.println(Thread.currentThread().getName()+":"+Thread.currentThread().getPriority());
        //主线程
        System.out.println("main:"+Thread.currentThread().getPriority());
        Thread thread01 = new Thread(runnable);
        thread01.setPriority(10);
        Thread thread02 = new Thread(runnable);
        thread02.setPriority(8);
        Thread thread03 = new Thread(runnable);
        thread03.setPriority(6);
        Thread thread04 = new Thread(runnable);
        thread04.setPriority(4);
        thread04.start();
        thread03.start();
        thread02.start();
        thread01.start();
    }
}
