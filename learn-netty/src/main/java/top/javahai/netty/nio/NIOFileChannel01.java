package top.javahai.netty.nio;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @author Hai
 * @program: learn-everyday
 * @description: 文件通道Demo01
 * @create 2020/11/29 - 18:11
 **/
public class NIOFileChannel01 {
    public static void main(String[] args) throws IOException {
        //一个汉字占三个字节
        String str="Hello,张三";
        //创建一个输出流
        FileOutputStream fileOutputStream = new FileOutputStream("D:\\file01.txt");
        //通过FileOutPutStream获取对应的FileChannel
        FileChannel fileChannel = fileOutputStream.getChannel();
        //创建一个缓冲区ByteBuffer
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
        //将str放入到缓冲区中byteBuffer
        byteBuffer.put(str.getBytes());
        //对byteBuffer进行翻转,以进行写操作
        byteBuffer.flip();

        //将byteBuffer数据写入到fileChannel中
        fileChannel.write(byteBuffer);
//        fileChannel.read(byteBuffer);
//        System.out.println(new String(byteBuffer.array()));
        fileOutputStream.close();
    }
}
