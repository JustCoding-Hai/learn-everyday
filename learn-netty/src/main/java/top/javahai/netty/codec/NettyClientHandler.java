package top.javahai.netty.codec;

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

        //ctx.writeAndFlush(Unpooled.copiedBuffer("Hello Server!", CharsetUtil.UTF_8));

        StudentPOJO.Student student = StudentPOJO.Student.newBuilder().setId(1).setName("Ethan").build();

        ctx.writeAndFlush(student);
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
