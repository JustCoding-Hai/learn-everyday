package top.javahai.nio;

import java.io.IOException;
import java.net.Inet4Address;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Arrays;

/**
 * @author Hai
 * @program: learn-everyday
 * @description: Buffer的Scattering和Gathering
 * @create 2020/12/2 - 23:27
 **/
public class ScatteringAndGatheringTest {

    /**
     * 通过多个Buffer实现读写操作
     *
     * Scattering:将数据写入到buffer时，采用buffer数组依次写入
     * Gathering:从buffer读取数据时，可以采用buffer数组，依次读
     * @param args
     */
    public static void main(String[] args) throws IOException {
        //使用
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        InetSocketAddress inetSocketAddress = new InetSocketAddress(7000);
        //绑定端口到socket并启动
        serverSocketChannel.socket().bind(inetSocketAddress);
        //创建buffer数组
        ByteBuffer[] byteBuffers = new ByteBuffer[2];
        byteBuffers[0]=ByteBuffer.allocate(5);
        byteBuffers[1]=ByteBuffer.allocate(3);

        //接收客户端的连接
        SocketChannel socketChannel = serverSocketChannel.accept();

        //假定消息长度为8
        int messageLength=8;

        while (true){
            int byteRead=0;

            while (byteRead<messageLength){
                long length = socketChannel.read(byteBuffers);
                byteRead+=length;
                System.out.println("byteRead="+byteRead);
                Arrays.asList(byteBuffers).stream()
                        .map(buffer->"postion="+buffer.position()+",limit="+buffer.limit())
                        .forEach(System.out::println);

                Arrays.asList(byteBuffers).forEach(byteBuffer -> byteBuffer.flip());

            }
            //将数据读出显示到客户端
            long byteWrite=0;
            while (byteWrite<messageLength){
                long length=socketChannel.write(byteBuffers);
                byteWrite+=length;
            }
            //将所有的buffer清空
            Arrays.asList(byteBuffers).forEach(byteBuffer -> byteBuffer.clear());

            System.out.println("byteRead:="+byteRead+"byteWrite="+byteWrite+",messageLength="+messageLength);

            socketChannel.close();

            serverSocketChannel.close();

        }



    }
}
