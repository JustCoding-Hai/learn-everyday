package top.javahai.netty.nio;

import java.nio.ByteBuffer;

/**
 * @author Hai
 * @program: learn-everyday
 * @description: buffer的put和get的类型问题
 * @create 2020/12/2 - 21:45
 **/
public class NIOPutGetByteBuffer {
    public static void main(String[] args) {
        ByteBuffer buffer = ByteBuffer.allocate(64);
        //放入
        buffer.putInt(111);
        buffer.putLong(9L);
        buffer.putChar('大');
        buffer.putShort((short)4);

        //翻转buffer
        buffer.flip();

        //取出
        System.out.println(buffer.getInt());
        System.out.println(buffer.getLong());
        System.out.println(buffer.getChar());
        //抛出java.nio.BufferUnderflowException异常
        System.out.println(buffer.getLong());



    }
}
