package top.javahai.netty.groupchat;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;

/**
 * @author Hai
 * @program: learn-everyday
 * @description: 群聊服务器实现
 * @create 2020/12/16 - 21:41
 **/
public class GroupChatServer {
    /**
     * 监听的端口号
     */
    private int port;

    public GroupChatServer(int port) {
        this.port = port;
    }

    /**
     * 创建服务端线程组，处理客户端的请求
     */
    public void run() {
        //创建两个线程组
        NioEventLoopGroup bossGroup = new NioEventLoopGroup(1);
        NioEventLoopGroup workerGroup = new NioEventLoopGroup();
        try {
            //创建服务器启动对象并配置参数
            ServerBootstrap bootstrap = new ServerBootstrap();
            bootstrap.group(bossGroup,workerGroup)
                    //设置服务端的通道实现
                    .channel(NioServerSocketChannel.class)
                    //初始化服务器可连接的队列大小
                    .option(ChannelOption.SO_BACKLOG,128)
                    //设置接收到的通道一直保持连接状态
                    .childOption(ChannelOption.SO_KEEPALIVE,true)
                    //设置接收到的通道的处理器
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            //获取pipeline
                            ChannelPipeline pipeline = ch.pipeline();
                            //向管道加入解码器
                            pipeline.addLast("decoder",new StringDecoder());
                            //向管道加入编码器
                            pipeline.addLast("encoder",new StringEncoder());
                            //向管道加入自己的业务处理器
                            pipeline.addLast("groupChatHandler",new GroupChatServerHandler());
                        }
                    });
            ChannelFuture channelFuture = bootstrap.bind(port).sync();
            //添加服务器启动的监听事件
            channelFuture.addListener(future -> {
                if (future.isSuccess()){
                    System.out.println("Netty服务器启动成功！");
                }else{
                    System.out.println("Netty服务器启动失败！");
                }
            });
            //监听关闭事件
            channelFuture.channel().closeFuture().sync();

        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
            System.out.println("Netty服务器关闭！");
        }

    }

    public static void main(String[] args) {
        new GroupChatServer(8888).run();
    }
}
