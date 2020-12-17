package top.javahai.netty.buf;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.util.CharsetUtil;

import java.nio.charset.Charset;

/**
 * @author Hai
 * @program: learn-everyday
 * @description: NettyBufDemo02，练习ByteBuf相关的API
 * @create 2020/12/16 - 0:17
 **/
public class NettyBufDemo02 {
    public static void main(String[] args) {
        ByteBuf byteBuf = Unpooled.copiedBuffer("Hello,World!", Charset.forName("UTF-8"));
        if (byteBuf.hasArray()){
            byte[] array = byteBuf.array();
            //将字节数组转为字符串
            System.out.println(new String(array, CharsetUtil.UTF_8));

            System.out.println(byteBuf);
            System.out.println(byteBuf.arrayOffset());
            System.out.println(byteBuf.readerIndex());
            System.out.println(byteBuf.writerIndex());
            System.out.println(byteBuf.capacity());
            System.out.println(byteBuf.getByte(0));

            //可读的字节数
            int readableLength = byteBuf.readableBytes();
            System.out.println("可读的字节数:"+readableLength);
            //逐个取出字节
            for (int i = 0; i < readableLength; i++) {
                System.out.println(((char) byteBuf.getByte(i)));
            }
            //读取某个范围
            System.out.println(byteBuf.getCharSequence(2,4,CharsetUtil.UTF_8));
            System.out.println(byteBuf.getCharSequence(8,10,CharsetUtil.UTF_8));

        }
    }
}
