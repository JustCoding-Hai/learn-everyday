package top.javahai.netty.websocket;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler;
import io.netty.handler.stream.ChunkedWriteHandler;

/**
 * @author Hai
 * @program: learn-everyday
 * @description: websocket长连接服务器
 * @create 2020/12/22 - 23:34
 **/
public class MyNettyServer {
    public static void main(String[] args) {
        NioEventLoopGroup bossGroup = new NioEventLoopGroup(1);
        NioEventLoopGroup workerGroup = new NioEventLoopGroup();
        ServerBootstrap bootstrap = new ServerBootstrap();
        bootstrap.group(bossGroup,workerGroup)
                .channel(NioServerSocketChannel.class)
                .childHandler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel ch) throws Exception {
                        ChannelPipeline pipeline = ch.pipeline();
                        //基于Http协议，需要Http的编解码器
                        pipeline.addLast(new HttpServerCodec())
                                //处理块方式写
                                .addLast(new ChunkedWriteHandler())
                                /**
                                 *  http数据传输是分段的(浏览器发送大量数据的时候，会发出多次的Http请求)，
                                 *  使用HttpObjectAggregator处理将数据聚合起来传输。
                                 */
                                .addLast(new HttpObjectAggregator(8192))
                                /**
                                 * 1.websocket协议是以帧的形式传输数据的
                                 * 2.WebSocketServerProtocolHandler的核心功能是将http协议转换为websocket协议，保
                                 * 持浏览器和服务器的长连接
                                 * 3./hello，指注册的站点。浏览器通过ws://localhost:8080/hello传递数据到“hello”站点进行处理
                                 */
                                .addLast(new WebSocketServerProtocolHandler("/hello"))
                                //自定义的Handler，处理业务逻辑
                                .addLast(new MyTextWebSocketFrameHandler());

                    }
                });
        try {
            ChannelFuture channelFuture = bootstrap.bind(7000).sync();
            channelFuture.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }

}
