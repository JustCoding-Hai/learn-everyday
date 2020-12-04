package top.javahai.netty.nio;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @author Hai
 * @program: learn-everyday
 * @description: 使用一个Buffer完成读取和写入
 * @create 2020/11/29 - 19:15
 **/
public class NIOFileChannel03 {
    public static void main(String[] args) throws IOException {
        //创建两个通道，分别负责读和写
        File file = new File("D:\\idea_workplace\\learn-everyday\\learn-netty\\src\\main\\java\\top\\javahai\\netty\\nio\\1.txt");
        FileInputStream fileInputStream = new FileInputStream(file);
        FileChannel fileChannel01 = fileInputStream.getChannel();

        FileOutputStream fileOutputStream = new FileOutputStream("D:\\idea_workplace\\learn-everyday\\learn-netty\\src\\main\\java\\top\\javahai\\netty\\nio\\2.txt");
        FileChannel fileChannel02 = fileOutputStream.getChannel();

        //创建字节缓冲区，大小为10个字节
        ByteBuffer byteBuffer = ByteBuffer.allocate(10);
        //循环读取,写入数据到缓冲区
        while (true){

            int read = fileChannel01.read(byteBuffer);
            System.out.println("read="+ read);
            //当返回-1时表示已经读取完了
            if (read==-1){
                break;
            }
            //反转缓冲区
            byteBuffer.flip();

            //将Buffer中的数据写入到fileChannel02通道中
            fileChannel02.write(byteBuffer);
            //清空Buffer缓冲区，不清空的话，由于第一次读取可能缓冲区已经满了，一直返回1，导致死循环
            byteBuffer.clear();
        }

        //关闭流
        fileInputStream.close();
        fileOutputStream.close();

    }
}
