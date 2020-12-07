package top.javahai.nio;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;

/**
 * @author Hai
 * @program: learn-everyday
 * @description: 使用transferFrom方法拷贝文件
 * @create 2020/12/2 - 21:32
 **/
public class NIOFileChannel04 {
    public static void main(String[] args) throws IOException {
        //原文件
        FileInputStream fileInputStream = new FileInputStream("C:\\Users\\LENOVO\\Pictures\\测试\\a.jpeg");
        //拷贝出来的文件
        FileOutputStream fileOutputStream = new FileOutputStream("C:\\Users\\LENOVO\\Pictures\\测试\\b.jpg");

        //获取文件流
        FileChannel inputStreamChannel = fileInputStream.getChannel();
        FileChannel outputStreamChannel = fileOutputStream.getChannel();

        //拷贝操作
        outputStreamChannel.transferFrom(inputStreamChannel,0,inputStreamChannel.size());

        //关闭通道
        inputStreamChannel.close();
        outputStreamChannel.close();

        //关闭流
        fileInputStream.close();
        fileOutputStream.close();

    }
}
