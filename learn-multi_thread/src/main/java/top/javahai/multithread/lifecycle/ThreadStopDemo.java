package top.javahai.multithread.lifecycle;

import jdk.nashorn.internal.ir.Flags;

/**
 * @author Hai
 * @program: learn-everyday
 * @description: 停止线程
 * @create 2021/2/10 - 17:01
 **/
public class ThreadStopDemo implements Runnable {
    /**
     * 定义线程使用的标志
     */
    private boolean flag=true;

    @Override
    public void run() {
        while (flag){
            System.out.println(Thread.currentThread().getName()+": I am running");
        }
    }

    /**
     * 对外提供方法改变标识
     */
    public void stop(){
        flag=false;
    }

    public static void main(String[] args) throws InterruptedException {
        ThreadStopDemo threadStopDemo = new ThreadStopDemo();
        new Thread(threadStopDemo).start();
        Thread.sleep(1000);
        for (int i = 0; i < 2000; i++) {
            System.out.println("main Thread: I am counting-"+i);
            if (i==1980){
                threadStopDemo.stop();
            }
        }
    }
}
