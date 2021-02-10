package top.javahai.multithread.create;

/**
 * @author Hai
 * @program: learn-everyday
 * @description: 实现Runnable接口创建线程
 * @create 2021/2/10 - 12:18
 **/
public class ThreadTest02 implements Runnable {
    @Override
    public void run() {
        for (int i = 0; i < 20; i++) {
            System.out.println("我在写代码"+i);
        }
    }

    public static void main(String[] args) {
        System.out.println("线程："+Thread.currentThread().getName()+"启动");
        //创建实现类
        ThreadTest02 threadTest02 = new ThreadTest02();
        //创建代理类
        Thread thread = new Thread(threadTest02);
        thread.start();
        System.out.println("线程："+thread.getName()+"启动");

        for (int i = 0; i < 1000; i++) {
            System.out.println("我在学习多线程"+i);
        }
    }
}
