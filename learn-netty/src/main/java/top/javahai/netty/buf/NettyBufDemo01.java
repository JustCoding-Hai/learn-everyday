package top.javahai.netty.buf;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;

/**
 * @author Hai
 * @program: learn-everyday
 * @description: ByteBuf简单使用
 * @create 2020/12/15 - 23:44
 **/
public class NettyBufDemo01 {
    /**
     * 在Netty的Buffer，不需要使用flip方法进行反转，其底层维护了readerIndex和writerIndex。
     * 通过readerIndex、writerIndex和capacity将buffer分为三个区域：
     * 0~通过readerIndex 已经读取的区域
     * writerIndex~capacity 可以写的区域
     * readerIndex~writerIndex 可以读取的区域
     * @param args
     */
    public static void main(String[] args) {
        ByteBuf buffer = Unpooled.buffer(10);
        System.out.println(buffer.capacity());
        //写入缓冲区
        for (int i = 0; i < buffer.capacity(); i++) {
            buffer.writeByte(i);
        }
        //获取缓冲区容量
        System.out.println(buffer.capacity());
        //获取缓冲区内容
        for (int i = 0; i < buffer.capacity(); i++) {
            System.out.println(buffer.readByte());
        }
    }
}
