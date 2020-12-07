package top.javahai.netty.simple;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.CharsetUtil;

/**
 * @author Hai
 * @program: learn-everyday
 * @description: 客户端处理器
 * @create 2020/12/6 - 19:16
 **/
public class NettyClientHandler extends ChannelInboundHandlerAdapter {

    /**
     * 通道就绪了就触发该方法
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("Client is :"+ctx);

        ctx.writeAndFlush(Unpooled.copiedBuffer("Hello Server!", CharsetUtil.UTF_8));
    }


    /**
     * 当通道有读取事件的时候触发
     * @param ctx
     * @param msg
     * @throws Exception
     */
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf buf = (ByteBuf) msg;

        System.out.println("服务器服务消息："+buf.toString(CharsetUtil.UTF_8));
        System.out.println("服务器地址："+ctx.channel().remoteAddress());
    }

    /**
     * 异常发生时触发
     * @param ctx
     * @param cause
     * @throws Exception
     */
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
