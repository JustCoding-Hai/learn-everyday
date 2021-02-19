package top.javahai.multithread.collection;

import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @author Hai
 * @program: learn-everyday
 * @description: 线程安全的List集合
 * @create 2021/2/19 - 22:09
 **/
public class ListDemo {
    public static void main(String[] args) {
        ArrayList<String> list1 = new ArrayList<>();
        //1. 线程安全解决方式1
        List<String> list2 = Collections.synchronizedList(list1);
        //2. 线程安全解决方式2
        Vector<String> list3 = new Vector<>();
        //3. 线程安全解决方式3
        List<String> list = new CopyOnWriteArrayList<>();
        for (int i = 0; i < 500; i++) {
            new Thread(()->{
                list.add(UUID.randomUUID().toString().substring(0,5));
                System.out.println(list);
            },String.valueOf(i)).start();
        }
    }
}
