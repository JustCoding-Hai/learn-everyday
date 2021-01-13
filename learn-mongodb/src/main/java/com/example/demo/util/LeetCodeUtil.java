package com.example.demo.util;

import java.lang.reflect.Method;

/**
 * @author Ethan Huang
 * @create 2021-01-05 15:11
 */
public class LeetCodeUtil {

    public static void countExecuteMilliSeconds(Class clazz, String methodName, Class[] argsType, Object... args) {
        String className = clazz.getSimpleName();
        System.out.println("类名：" + className);
        System.out.println("执行的方法:" + methodName);
        //处理方法执行，获取方法所需要的参数，一个个进行类型转换
        try {
            Method method = clazz.getMethod(methodName, argsType);
            Object o = clazz.newInstance();
            long startTime = System.currentTimeMillis();
            System.out.println("执行结果：");
            method.invoke(o, args);
            System.out.println(System.currentTimeMillis() - startTime + "ms");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args) {
        Class[] classes = {String.class, Integer.class};
        countExecuteMilliSeconds(LeetCodeUtil.class, "test2", classes, "123", 12);
    }

    public void test(String name) {
        System.out.println(name);
    }

    public void test2(String name, Integer num) {
        StringBuilder s = new StringBuilder("abc");
        int i1 = 100000;
        for (int i = 0; i < i1; i++) {
            s.append("a");
        }
        System.out.println(name);
        System.out.println(num);
    }
}
