package top.javahai.netty.nio;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @author Hai
 * @program: learn-everyday
 * @description: 读本地文件数据
 * @create 2020/11/29 - 18:55
 **/
public class NIOFileChannel02 {
    public static void main(String[] args) throws IOException {

        //创建文件输入流
        File file = new File("D:\\file01.txt");
        FileInputStream fileInputStream = new FileInputStream(file);
        //通过FileInputStream获取对应的FileChannel(FileChannelImpl)
        FileChannel fileChannel = fileInputStream.getChannel();

        //创建缓冲区
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);

        //将通道的数据读取到缓冲区中
        fileChannel.read(byteBuffer);

        //转为String并输出
        System.out.println(new String(byteBuffer.array()));

        fileInputStream.close();
    }

}
