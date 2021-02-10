package top.javahai.multithread.lifecycle;

import javax.lang.model.element.VariableElement;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author Hai
 * @program: learn-everyday
 * @description: 线程睡眠实现倒计时
 * @create 2021/2/10 - 17:42
 **/
public class ThreadSleepDemo implements Runnable{
    SimpleDateFormat dateFormat=new SimpleDateFormat("HH:mm:ss");

    @Override
    public void run() {
        try {
            int i=0;
            while (true){
                if (i>10){
                    break;
                }
                Thread.sleep(1000);
                System.out.println(dateFormat.format(new Date()));
                i++;
            }
            System.out.println("神州100号发射~");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new Thread(new ThreadSleepDemo()).start();
    }
}
