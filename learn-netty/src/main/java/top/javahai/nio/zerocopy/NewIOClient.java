package top.javahai.nio.zerocopy;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.FileChannel;
import java.nio.channels.SocketChannel;

/**
 * @author Hai
 * @program: learn-everyday
 * @description: 零拷贝文件io客户端
 * @create 2020/12/5 - 20:49
 **/
public class NewIOClient {
    public static void main(String[] args) throws IOException {
        SocketChannel socketChannel = SocketChannel.open();

        socketChannel.connect(new InetSocketAddress("localhost",7001));

        String fileName="D:\\BaiduNetdiskDownload\\尚硅谷Netty学习资料\\资料\\nio.pdf";

        FileChannel fileChannel = new FileInputStream(fileName).getChannel();

        long beginTime = System.currentTimeMillis();

        //transferTo使用零拷贝
        long transferCount = fileChannel.transferTo(0, fileChannel.size(), socketChannel);

        System.out.println("发送的总字节数："+transferCount+"，耗时："+(System.currentTimeMillis()-beginTime));

        fileChannel.close();
    }
}
