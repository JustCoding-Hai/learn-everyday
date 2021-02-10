package top.javahai.multithread.create;

/**
 * @author Hai
 * @program: learn-everyday
 * @description: 继承Thread类创建线程
 * @create 2021/2/10 - 11:47
 **/
public class ThreadTest01 extends Thread {
    @Override
    public void run() {
        for (int i = 0; i < 20; i++) {
            System.out.println("我在写代码"+i);
        }
    }

    public static void main(String[] args) {
        System.out.println("线程："+Thread.currentThread().getName()+"启动");
        ThreadTest01 threadTest01 = new ThreadTest01();
        System.out.println("线程："+threadTest01.getName()+"启动");
        threadTest01.start();

        for (int i = 0; i < 1000; i++) {
            System.out.println("我在学习多线程"+i);
        }

    }
}
