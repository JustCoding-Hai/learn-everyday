package top.javahai.netty.simple;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.util.NettyRuntime;

import java.net.Socket;

/**
 * @author Hai
 * @program: learn-everyday
 * @description: Netty入门服务器搭建
 * @create 2020/12/6 - 17:51
 **/
public class NettyServer {
    public static void main(String[] args) throws InterruptedException {


        //创建两个线程组bossGroup和workerGroup,两个都是无限循环
        //bossGroup只是处理连接请求
        NioEventLoopGroup bossGroup = new NioEventLoopGroup();
        //默认创建的线程数：系统的CPU处理器*2
        System.out.println("默认创建的线程数"+NettyRuntime.availableProcessors()*2);
        //workerGroup真正和客户端业务处理
        NioEventLoopGroup workerGroup = new NioEventLoopGroup();
        try{
            //创建服务器端的启动对象并配置参数
            ServerBootstrap bootstrap = new ServerBootstrap();

            bootstrap.group(bossGroup,workerGroup)
                    //使用NioServerSocket作为服务器的通道实现
                    .channel(NioServerSocketChannel.class)
                    //设置线程队列得到连接个数
                    .option(ChannelOption.SO_BACKLOG,128)
                    //设置保持活动连接状态
                    .childOption(ChannelOption.SO_KEEPALIVE,true)
                    //创建一个通道测试对象
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        //给pipeline设置处理器
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            ch.pipeline().addLast(new NettyServerHandler());
                        }
                    });
            System.out.println("服务器启动成功！");

            //绑定一个端口并且同步，生成一个ChannelFuture对象
            ChannelFuture channelFuture = bootstrap.bind(6668).sync();
            //对关闭通道进行监听
            channelFuture.channel().closeFuture().sync();

        }finally {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }


    }
}
