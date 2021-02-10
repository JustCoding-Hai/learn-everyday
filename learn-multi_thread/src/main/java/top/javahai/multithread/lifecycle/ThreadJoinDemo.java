package top.javahai.multithread.lifecycle;

/**
 * @author Hai
 * @program: learn-everyday
 * @description: 线程强制执行DEMO
 * @create 2021/2/10 - 18:14
 **/
public class ThreadJoinDemo {
    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(() -> {
            for (int i = 0; i < 200; i++) {
                System.out.println("来了" + i + "个VIP插队");
            }
        },"vip线程");
        thread.start();

        for (int i = 0; i < 1000; i++) {
            if (i==200){
                //vip线程强制执行，当前main线程进入阻塞,需要等待vip线程执行完
                thread.join();
            }
            System.out.println("main 线程："+i);
        }


    }
}
