package com.example.learnredis;

import java.io.IOException;
import java.net.Socket;

/**
 * @author Hai
 * @program: learn-everyday
 * @description: 自定义Redis客户端实现set和get命令
 * @create 2021/6/7 - 23:32
 **/
public class MyRedisClient {
    private Socket socket;

    public MyRedisClient(){
        try {
            this.socket =  new Socket("39.108.169.57", 6379);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Redis 连接失败！");
        }
    }
    public String set(String key,String value) throws IOException {
        StringBuilder sb = new StringBuilder();
        sb.append("*3")
                .append("\r\n")
                .append("$")
                .append("set".length())
                .append("\r\n")
                .append("set")
                .append("\r\n")
                .append("$")
                .append(key.getBytes().length)
                .append("\r\n")
                .append(key)
                .append("\r\n")
                .append("$")
                .append(value.getBytes().length)
                .append("\r\n")
                .append(value)
                .append("\r\n");
        socket.getOutputStream().write(sb.toString().getBytes());
        byte[] buf = new byte[1024];
        socket.getInputStream().read(buf);
        return new String(buf);
    }

    public String get(String key) throws IOException{
        StringBuilder sb = new StringBuilder();
        sb.append("*2")
                .append("\r\n")
                .append("$")
                .append("get".length())
                .append("\r\n")
                .append("get")
                .append("\r\n")
                .append("$")
                .append(key.getBytes().length)
                .append("\r\n")
                .append(key)
                .append("\r\n");
        socket.getOutputStream().write(sb.toString().getBytes());
        byte[] buf = new byte[1024];
        socket.getInputStream().read(buf);
        return new String(buf);
    }

    public static void main(String[] args) throws IOException {
       String set = new MyRedisClient().set("k1", "江南一点雨");
       System.out.println(set);
       String k1 = new MyRedisClient().get("k1");
       System.out.println(k1);
    }
}
