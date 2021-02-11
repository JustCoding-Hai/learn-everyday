package top.javahai.multithread.lock;


import java.util.concurrent.locks.ReentrantLock;

/**
 * @author Hai
 * @program: learn-everyday
 * @description: ReentrantLock的使用
 * @create 2021/2/11 - 12:04
 **/
public class ReentrantLockDemo {

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
    /**
     * lock锁
     */
    private final ReentrantLock lock=new ReentrantLock();


    private  void sell() throws InterruptedException {
        try {
            lock.lock();
            if (ticketNum<=0){
                flag=false;
                return;
            }
            //模拟方法的延时
            Thread.sleep(1000);
            //成功出售了票
            System.out.println(Thread.currentThread().getName()+"买到了票："+ticketNum--);
        }finally {
            lock.unlock();
        }
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
