package top.javahai.netty.inandoutboundhandler;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageCodec;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.List;

/**
 * @author Hai
 * @program: learn-everyday
 * @description:
 * @create 2020/12/25 - 20:50
 **/
public class MyByteToLongDecoder extends ByteToMessageCodec<Long> {
    private static final int MAX_READ_BYTE=8;
    @Override
    protected void encode(ChannelHandlerContext ctx, Long msg, ByteBuf out) throws Exception {

        System.out.println("Server encode message to Byte");
    }

    /**
     *
     * @param ctx 上下文对象
     * @param in 入站的ByteBuf
     * @param out List集合，存放解码后的数据。将解码后的数据传给下一个Handler
     * @throws Exception
     */
    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
        System.out.println("MyByteToLongDecoder被调用");
        //读取超过或等于8个字节才放入到集合中
        if (in.readableBytes()>=MAX_READ_BYTE){
            out.add(in.readLong());
        }

    }
}
