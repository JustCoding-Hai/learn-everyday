package com.example.demo;

/**
 * @author Ethan Huang
 * @create 2021-01-05 10:40
 */
public class Test02 {
    public static void main(String[] args) {
        String s1 = new String("aaa");
        String s2 = new String("aaa");
        System.out.println(s1 == s2);           // false
        String s3 = s1.intern();
        String s4 = s1.intern();
        System.out.println(s3 == s4);           // true
        String s5 = s2.intern();
        System.out.println(s4==s5);             //true

    }
}
