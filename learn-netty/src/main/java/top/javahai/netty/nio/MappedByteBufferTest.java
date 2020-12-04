package top.javahai.netty.nio;

import com.sun.glass.ui.Size;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @author Hai
 * @program: learn-everyday
 * @description: MappedByteBuffer类测试使用
 * @create 2020/12/2 - 22:56
 **/
public class MappedByteBufferTest {
    /**
     * NIO还提供了MappedByteBuffer，可以让文件直接在内存（堆外的内存）中进行修改，而如何同步到文件由NIO来完成
     *
     * MappedByteBuffer可让文件直接在内存(堆外内存)修改,操作系统不需要拷贝一次
     * @param args
     */
    public static void main(String[] args) throws IOException {
        RandomAccessFile randomAccessFile = new RandomAccessFile("D:\\idea_workplace\\learn-everyday\\learn-netty\\src\\main\\java\\top\\javahai\\netty\\nio\\1.txt", "rw");

        //获取对应的通道
        FileChannel channel = randomAccessFile.getChannel();

        //获取MappedByteBuffer,
        /**
         * MappedByteBuffer map(MapMode mode, long position, long size)
         *
         * mode:使用读写模式
         * position：可以直接修改的起始位置
         * size：映射到内存的大小（非索引位置），将文件的多少字节映射到内存中
         */
        MappedByteBuffer mappedByteBuffer = channel.map(FileChannel.MapMode.READ_WRITE, 0, 5);

        mappedByteBuffer.put(0,(byte) 'H');
        mappedByteBuffer.put(4,(byte) 'I');
        //抛出索引越界异常
        //mappedByteBuffer.put(5,(byte) 'A');

        randomAccessFile.close();


    }
}
