package top.javahai.netty.nio;

import java.nio.IntBuffer;

/**
 * @author Hai
 * @program: learn-everyday
 * @description: 基础的Buffer使用
 * @create 2020/11/29 - 13:49
 **/
public class BasicBuffer {
    public static void main(String[] args) {
        IntBuffer intBuffer = IntBuffer.allocate(5);
        //向buffer中存放数据
        for (int i = 0; i < intBuffer.capacity(); i++) {
            intBuffer.put(i*2);
        }
        //读写切换，在执行一系列通道读取或放置操作之后，调用此方法以准备一系列通道写入或相对get操作。
        //重置limit、position、mark
        intBuffer.flip();

        //读取buffer的数据
        while (intBuffer.hasRemaining()){
            System.out.println(intBuffer.get());
        }

    }
}
