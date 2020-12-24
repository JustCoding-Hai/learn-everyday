package top.javahai.netty.codec;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.CharsetUtil;

import java.util.concurrent.TimeUnit;

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
        //读取客户端发送来的StudentPojo.Student;
        StudentPOJO.Student student= (StudentPOJO.Student) msg;
        System.out.println(student.getId()+"-"+student.getName() );
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
