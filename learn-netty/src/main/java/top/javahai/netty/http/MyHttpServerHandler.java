package top.javahai.netty.http;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.*;
import io.netty.util.CharsetUtil;
import sun.java2d.pipe.SpanIterator;

import java.net.URI;

/**
 * @author Hai
 * @program: learn-everyday
 * @description: Http服务器处理器
 * @create 2020/12/13 - 23:52
 **/
public class MyHttpServerHandler extends SimpleChannelInboundHandler<HttpObject> {
    /**
     * 1.SimpleChannelInboundHandler是ChannelInboundHandlerAdapter的子类
     * 2.HttpObject：包含客户端与服务器端相互通讯的数据
     * @param ctx
     * @param msg
     * @throws Exception
     */
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, HttpObject msg) throws Exception {
        if (msg instanceof HttpRequest){
            System.out.println("msg 类型:"+msg.getClass());
            System.out.println("客户端地址:"+ctx.channel().remoteAddress());
            //回复信息到浏览器
            ByteBuf content = Unpooled.copiedBuffer("Hello,我是服务器", CharsetUtil.UTF_8);
            HttpRequest httpRequest = (HttpRequest) msg;
            URI uri = new URI(httpRequest.uri());
            String noHandleRequest="/favicon.ico";
            if (noHandleRequest.equals(uri.getPath())){
                System.out.println("对/favicon.ico请求不做响应");
                return;
            }
            //构造Http响应
            FullHttpResponse response = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.OK, content);
            response.headers().set(HttpHeaderNames.CONTENT_TYPE,"text/plain");
            response.headers().set(HttpHeaderNames.CONTENT_LENGTH,content.readableBytes());
            //将构造Http响应返回
            ctx.writeAndFlush(response);
        }

    }
}
