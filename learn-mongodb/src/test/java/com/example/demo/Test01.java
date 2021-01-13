package com.example.demo;

import sun.applet.Main;

/**
 * @author Ethan Huang
 * @create 2021-01-04 17:53
 */
public class Test01 {
    public static void main(String[] args) {
        System.out.println("--------Integer--------");
        //-128~127
        Integer num1 = new Integer(127);
        Integer num2 = new Integer(127);
        Integer num3 = new Integer(128);
        Integer num4 = new Integer(128);
        System.out.println(num1==num2);
        System.out.println(num3==num4);
        /**
         * 1.Integer、Long、Short、Byte包装类型直接赋值，调用的是valueOf方法，如果-128=<数值<=127的包，会直接取缓存数组中的包装类型对象
         * 2.包装类型直接赋值，会调用valueOf方法自动装箱。指向基本数据类型会调用(基本数据类型+"Value")自动拆箱
         *
         */
        Integer num5=127;
        Integer num6=127;
        Integer num7=128;
        Integer num8=128;
        System.out.println(num5==num6);
        System.out.println(num7==num8);

        /**
         * 基本数据类型和包装类型比较的时候，包装类型会自动拆箱
         */
        int num9=10;
        Integer num10=10;
        System.out.println(num9==num10);

        System.out.println("--------Byte--------");
        //byte的范围-128~127
        Byte n1=127;
        Byte n2=127;
        System.out.println(n1==n2);
        Byte aByte = new Byte(((byte) 127));
        Byte bByte = new Byte(((byte) 127));
        System.out.println(aByte==bByte);

        System.out.println("--------Long--------");
        Long n3=127L;
        Long n4=127L;
        Long n5=128L;
        Long n6=128L;
        System.out.println(n3==n4);
        System.out.println(n5==n6);
        System.out.println("--------Short--------");
        Short s1=127;
        Short s2=127;
        Short s3=128;
        Short s4=128;
        System.out.println(s1==s2);
        System.out.println(s3==s4);




    }
}
