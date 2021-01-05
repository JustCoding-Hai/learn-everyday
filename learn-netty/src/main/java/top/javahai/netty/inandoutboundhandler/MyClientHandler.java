package top.javahai.netty.inandoutboundhandler;

import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.CharsetUtil;

/**
 * @author Hai
 * @program: learn-everyday
 * @description:
 * @create 2020/12/25 - 21:12
 **/
public class MyClientHandler extends SimpleChannelInboundHandler<Long> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Long msg) throws Exception {

    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("MyClientHandler发送数据");
        //ctx.channel().writeAndFlush(123456789L);
        ctx.channel().writeAndFlush(Unpooled.copiedBuffer("abcdefghijklnmopq", CharsetUtil.UTF_8));
    }
}
