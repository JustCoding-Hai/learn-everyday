package top.javahai.netty.http;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpServerCodec;

/**
 * @author Hai
 * @program: learn-everyday
 * @description: 通道的初始化处理
 * @create 2020/12/13 - 23:51
 **/
public class MyChannelInitializer extends ChannelInitializer<SocketChannel> {
    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        ChannelPipeline pipeline = ch.pipeline();

        //向管道加入处理器，HttpServerCodec是Netty提供的Http的编-解码器
        pipeline.addLast("MyHttpCodec",new HttpServerCodec());
        //加入自定义的处理器
        pipeline.addLast("MyHttpServerHandler",new MyHttpServerHandler());
    }
}
