package top.javahai.netty.heartbeat;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import io.netty.handler.timeout.IdleStateHandler;

import java.util.concurrent.TimeUnit;

/**
 * @author Hai
 * @program: learn-everyday
 * @description: 心跳机制测试程序
 * @create 2020/12/17 - 23:33
 **/
public class NettyServer {
    public static void main(String[] args) {
        NioEventLoopGroup bossGroup = new NioEventLoopGroup(1);
        NioEventLoopGroup workerGroup = new NioEventLoopGroup();

        ServerBootstrap bootstrap = new ServerBootstrap();
        bootstrap.group(bossGroup,workerGroup)
                .channel(NioServerSocketChannel.class)
                //.handler(new LoggingHandler(LogLevel.ERROR))
                .childHandler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel ch) throws Exception {
                        ChannelPipeline pipeline = ch.pipeline();
                        /**
                         * 加入netty提供的IdleStateHandler，说明：
                         * 1.readerIdleTime:表示多长时间没有读，就会发送一个心跳检测包检测是否连接
                         * 2.writerIdleTime：表示多长时间没有写，就会发送一个心跳检测包检测是否连接
                         * 3.allIdleTime 表示多长时间没有读和写，就会发送一个心跳检测包检测是否连接
                         * 当IdleStateEvent触发后，就会传递给管道的下一个handler处理
                         */
                        pipeline.addLast(new IdleStateHandler(5,10,10, TimeUnit.SECONDS));
                        pipeline.addLast(new MyServerHandler());
                    }
                });
        try {
            ChannelFuture channelFuture = bootstrap.bind(8888).sync();
            channelFuture.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }
}
