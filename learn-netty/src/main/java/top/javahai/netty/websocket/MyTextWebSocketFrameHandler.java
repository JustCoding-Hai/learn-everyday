package top.javahai.netty.websocket;

import com.sun.javafx.binding.StringFormatter;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @author Hai
 * @program: learn-everyday
 * @description: TextWebSocketFrameHandler处理器
 * @create 2020/12/22 - 23:55
 **/
public class MyTextWebSocketFrameHandler extends SimpleChannelInboundHandler<TextWebSocketFrame> {
    /**
     * TextWebSocketFrame类型，表示一个文本帧
     * @param ctx
     * @param msg
     * @throws Exception
     */
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, TextWebSocketFrame msg) throws Exception {
        System.out.println("服务器受到消息："+msg.text());
        //回复消息
        ctx.channel().writeAndFlush(new TextWebSocketFrame(
                LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))+" 我收到啦！你说的是：["+msg.text()+"]吗？"));
    }

    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        System.out.println("handlerAdded 被调用："+ctx.channel().id().asShortText());
        System.out.println("handlerAdded 被调用："+ctx.channel().id().asLongText());
    }

    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        System.out.println("handlerRemoved 被调用："+ctx.channel().id().asLongText());
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        super.exceptionCaught(ctx, cause);
        System.out.println("发生了异常");
    }
}
