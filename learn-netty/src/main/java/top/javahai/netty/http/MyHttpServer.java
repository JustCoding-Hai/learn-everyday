package top.javahai.netty.http;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * @author Hai
 * @program: learn-everyday
 * @description: 模拟HTTP服务器
 * @create 2020/12/13 - 23:51
 **/
public class MyHttpServer {
    public static void main(String[] args) {
        //创建两个线程组
        NioEventLoopGroup bossGroup = new NioEventLoopGroup();
        NioEventLoopGroup workerGroup = new NioEventLoopGroup();

        try {

            ServerBootstrap bootstrap = new ServerBootstrap();
            bootstrap.group(bossGroup,workerGroup).channel(NioServerSocketChannel.class)
                    .childHandler(new MyChannelInitializer());
            //绑定一个端口并且同步，生成一个ChannelFuture对象，启动服务器
            ChannelFuture channelFuture = bootstrap.bind(6668).sync();
            channelFuture.addListener(future->{
                if (future.isSuccess()){
                    System.out.println("启动服务器成功！");
                }else{
                    System.out.println("启动服务器失败！");
                }
            });
            //对关闭通道进行监听
            channelFuture.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }


    }
}
