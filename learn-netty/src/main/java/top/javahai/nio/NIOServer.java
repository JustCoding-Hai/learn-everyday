package top.javahai.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

/**
 * @author Hai
 * @program: learn-everyday
 * @description: NIO服务器
 * @create 2020/12/4 - 11:19
 **/
public class NIOServer {
    public static void main(String[] args) throws IOException {

        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();

        Selector selector = Selector.open();

        serverSocketChannel.socket().bind(new InetSocketAddress(6666));

        //设置为非阻塞
        serverSocketChannel.configureBlocking(false);

        //将serverSocketChannel注册到Selector，设置关心的事件为OP_ACCEPT
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);

        while (true){
            if (selector.select(5000)==0){
                System.out.println("服务器等待了5s，没有接收到连接");
                continue;
            }
            //有连接请求,就获取相关的selectionKeys集合
            Set<SelectionKey> selectionKeys = selector.selectedKeys();

            Iterator<SelectionKey> keyIterator = selectionKeys.iterator();

            while (keyIterator.hasNext()){
                //获取SelectionKey
                SelectionKey key = keyIterator.next();
                //通过key对应的通道事件来进行特定的处理，如果是有新的客户端连接
                if (key.isAcceptable()){
                    SocketChannel socketChannel = serverSocketChannel.accept();
                    //设置为非阻塞
                    socketChannel.configureBlocking(false);
                    System.out.println("接收到的channel： "+socketChannel.hashCode());
                    //将新的客户端连接注册到选择器中,关注事件为read，同时绑定缓冲区
                    socketChannel.register(selector,SelectionKey.OP_READ, ByteBuffer.allocate(1024));

                }
                //发生OP_READ事件
                if (key.isReadable()) {
                    //通过key获取相应的channel
                    SocketChannel socketChannel = (SocketChannel) key.channel();
                    //通过key获取channel关联的buffer
                    ByteBuffer byteBuffer = (ByteBuffer) key.attachment();
                    socketChannel.read(byteBuffer);

                    System.out.println("从客户端获取数据："+new String(byteBuffer.array()));

                }
                //处理完成当前的key就移除
                keyIterator.remove();
            }
        }


    }
}
