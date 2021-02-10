package top.javahai.multithread.synchronic;

import sun.nio.ch.FileKey;

import java.util.concurrent.BlockingDeque;

/**
 * @author Hai
 * @program: learn-everyday
 * @description: 线程同步-买票问题案例
 * @create 2021/2/10 - 22:19
 **/
public class TicketBuy {
    public static void main(String[] args) {
        TicketOffice ticketOffice = new TicketOffice();
        new Thread(ticketOffice,"小明").start();
        new Thread(ticketOffice,"小黄").start();
        new Thread(ticketOffice,"小林").start();

    }
}

/**
 * 售票处
 */
class TicketOffice implements Runnable{

    private int ticketNum=10;
    private boolean flag=true;


    private synchronized void sell() throws InterruptedException {
        if (ticketNum<=0){
            flag=false;
            return;
        }
        //模拟方法的延时
        Thread.sleep(1000);
        //成功出售了票
        System.out.println(Thread.currentThread().getName()+"买到了票："+ticketNum--);
    }

    @Override
    public void run() {
        while (flag){
            try {
                sell();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
