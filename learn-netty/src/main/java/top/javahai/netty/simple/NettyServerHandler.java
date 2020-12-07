package top.javahai.netty.simple;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.CharsetUtil;

/**
 * @author Hai
 * @program: learn-everyday
 * @description: 连接处理器
 * @create 2020/12/6 - 18:22
 **/
public class NettyServerHandler extends ChannelInboundHandlerAdapter {

    /**
     * 读取客户端发来的数据
     * @param ctx 上下文对象，含有管道pipeline，通道channel，地址
     * @param msg 客户端发送的数据
     * @throws Exception
     */
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        //输出当前线程信息
        System.out.println(Thread.currentThread().getName());
        System.out.println(ctx.channel());
        System.out.println(ctx.pipeline());
        //将msg转为ByteBuf
        ByteBuf buf = (ByteBuf) msg;
        System.out.println("客户端发送来的消息："+buf.toString(CharsetUtil.UTF_8));
        System.out.println("客户端的地址是："+ctx.channel().remoteAddress());
    }

    /**
     * 读取数据完毕后往通道回送消息
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        //将数据写入到缓存并刷新,调用Unpooled.copiedBuffer进行重编码
        ctx.writeAndFlush(Unpooled.copiedBuffer("Hello，客户端",CharsetUtil.UTF_8));

    }

    /**
     * 处理异常，关闭通道
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
