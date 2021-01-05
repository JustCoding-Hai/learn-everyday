package top.javahai.netty.inandoutboundhandler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * @author Hai
 * @program: learn-everyday
 * @description:
 * @create 2020/12/25 - 20:57
 **/
public class MyServerHandler extends SimpleChannelInboundHandler<Long> {
    /**
     * 读取前一个处理器MyByteToLongDecoder 解码后的数据
     * @param ctx
     * @param msg
     * @throws Exception
     */
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Long msg) throws Exception {
        System.out.println("MyServerHandler被调用");
        System.out.println(ctx.channel().remoteAddress()+"读取到的long: "+msg);
        //ctx.writeAndFlush("wo接收到了~");
        ctx.writeAndFlush(123L);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
