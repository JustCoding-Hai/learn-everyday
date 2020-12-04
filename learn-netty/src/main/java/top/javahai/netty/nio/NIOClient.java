package top.javahai.netty.nio;

import java.io.IOException;
import java.net.Inet4Address;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

/**
 * @author Hai
 * @program: learn-everyday
 * @description: NIO客户端
 * @create 2020/12/4 - 11:53
 **/
public class NIOClient {
    public static void main(String[] args) throws IOException {
        //创建一个网络通道
        SocketChannel socketChannel = SocketChannel.open();
        //设置为非阻塞
        socketChannel.configureBlocking(false);

        //服务器的IP地址和端口号
        InetSocketAddress inetSocketAddress = new InetSocketAddress("127.0.0.1", 6666);

        //连接服务器
        if (!socketChannel.connect(inetSocketAddress)){
            while (!socketChannel.finishConnect()){
                System.out.println("因为连接需要时间，客户端不会阻塞，可以做其它工作");
            }
        }

        //连接成功后发送数据
        String s="Hello World!";
        //打包字节数组数据到buffer中
        ByteBuffer byteBuffer = ByteBuffer.wrap(s.getBytes());

        //将buffer的数据写入到通道中
        socketChannel.write(byteBuffer);

        System.in.read();

    }
}
