package com.example.demo;

import org.junit.jupiter.api.Test;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

/**
 * @author Ethan Huang
 * @create 2021-01-05 14:33
 */
public class Test03 {
    static SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @Test
    public static void main(String[] args) {
        ArrayList<Integer> list = new ArrayList<>();
        for (int i = 0; i < 900000; i++) {
            list.add(i);
        }
        String s="s";
        long before01 = System.currentTimeMillis();
        for (int i = 0; i < list.size(); i++) {
            s+="v";
        }
        long after01 = System.currentTimeMillis();
        System.out.println(after01-before01);

        String a="a";
        long before02=System.currentTimeMillis();
        for (int i = 0,size= list.size(); i < size; i++) {
            a+="v";
        }
        long after02=System.currentTimeMillis();
        System.out.println(after02-before02);

        System.exit(0);
    }
}
