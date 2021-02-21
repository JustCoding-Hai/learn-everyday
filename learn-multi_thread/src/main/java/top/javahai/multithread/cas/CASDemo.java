package top.javahai.multithread.cas;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author Hai
 * @program: learn-everyday
 * @description: cas的demo
 * @create 2021/2/21 - 17:31
 **/
public class CASDemo {
    public static void main(String[] args) {
        AtomicInteger atomicInteger = new AtomicInteger(2020);
        System.out.println(atomicInteger.compareAndSet(2020,2021));
        System.out.println(atomicInteger.get());

        System.out.println(atomicInteger.compareAndSet(2021,2020));
        System.out.println(atomicInteger.get());

        //存在ABA问题，不知道值在操作前有修改过
        System.out.println(atomicInteger.compareAndSet(2020,666));
        System.out.println(atomicInteger.get());
    }
}
