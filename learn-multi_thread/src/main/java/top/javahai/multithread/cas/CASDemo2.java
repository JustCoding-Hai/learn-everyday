package top.javahai.multithread.cas;

import org.springframework.boot.autoconfigure.aop.AopAutoConfiguration;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicStampedReference;

/**
 * @author Hai
 * @program: learn-everyday
 * @description: ABAwenti 使用原子引用
 * @create 2021/2/21 - 17:44
 **/
public class CASDemo2 {
    public static void main(String[] args) {
        //stamp可理解为版本号
        AtomicStampedReference<Integer> atomicStampedReference = new AtomicStampedReference<>(1, 1);
        //使用包装类型Integer，注意引用问题，-128~127用的缓冲池的引用，其他范围每次初始为新的对象


        new Thread(()->{
            int stamp = atomicStampedReference.getStamp();
            System.out.println(Thread.currentThread().getName()+"-v1=>"+stamp);

            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            atomicStampedReference.compareAndSet(1,2,atomicStampedReference.getStamp(),atomicStampedReference.getStamp()+1);
            System.out.println(Thread.currentThread().getName()+"-reference=>"+atomicStampedReference.getReference()+"-v2=>"+ atomicStampedReference.getStamp());

            atomicStampedReference.compareAndSet(2,1,atomicStampedReference.getStamp(),atomicStampedReference.getStamp()+1);
            System.out.println(Thread.currentThread().getName()+"-reference=>"+atomicStampedReference.getReference()+"-v3=>"+ atomicStampedReference.getStamp());

        }).start();



        new Thread(()-> {
            int stamp = atomicStampedReference.getStamp();
            System.out.println(Thread.currentThread().getName()+"-v=>" + stamp);

            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            //另一个线程操作stamp改变，stamp不等于1了，该操作不进行
            boolean b = atomicStampedReference.compareAndSet(1, 66, stamp, stamp + 1);
            System.out.println(b);
            System.out.println(Thread.currentThread().getName()+"-reference=>"+atomicStampedReference.getReference()+"-v=>"+ atomicStampedReference.getStamp());
        }).start();


    }
}
