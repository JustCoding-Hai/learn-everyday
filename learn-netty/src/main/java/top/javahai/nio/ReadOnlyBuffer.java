package top.javahai.nio;

import java.nio.ByteBuffer;

/**
 * @author Hai
 * @program: learn-everyday
 * @description: 将一个Buffer转换为只读Buffer
 * @create 2020/12/2 - 21:51
 **/
public class ReadOnlyBuffer {
    public static void main(String[] args) {
        ByteBuffer byteBuffer = ByteBuffer.allocate(64);

        byteBuffer.putInt(10);

        //创建了一个新的共享该缓冲区的只读Buffer
        ByteBuffer onlyReadBuffer = byteBuffer.asReadOnlyBuffer();

        byteBuffer.putInt(20);
        //原本的buffer仍然能写入数据
        byteBuffer.flip();

        System.out.println(byteBuffer.getInt());
        System.out.println(byteBuffer.getInt());

        //对只读Buffer放入数据，报异常
        onlyReadBuffer.putInt(20);

        onlyReadBuffer.flip();

        System.out.println(onlyReadBuffer.getInt());
        System.out.println(onlyReadBuffer.getInt());

    }
}
