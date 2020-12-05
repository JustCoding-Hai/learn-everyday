package top.javahai.netty.nio.zerocopy;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

/**
 * @author Hai
 * @program: learn-everyday
 * @description: 零拷贝处理服务器
 * @create 2020/12/5 - 20:44
 **/
public class NewIOServer {
    public static void main(String[] args) throws IOException {
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        ServerSocket serverSocket = serverSocketChannel.socket();
        serverSocket.bind(new InetSocketAddress(7001));
        ByteBuffer byteBuffer = ByteBuffer.allocate(4096);

        while (true){
            SocketChannel socketChannel = serverSocketChannel.accept();

            int readCount=0;

            while (readCount!=-1){
                readCount= socketChannel.read(byteBuffer);
            }
            byteBuffer.rewind();
        }
    }
}
