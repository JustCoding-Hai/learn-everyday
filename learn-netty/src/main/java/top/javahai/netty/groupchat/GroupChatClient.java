package top.javahai.netty.groupchat;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;

import java.util.Scanner;

/**
 * @author Hai
 * @program: learn-everyday
 * @description: 群聊客户端
 * @create 2020/12/16 - 23:55
 **/
public class GroupChatClient {
    private final static String HOST="127.0.0.1";
    private final static int PORT=8888;

    /**
     * 启动客户端程序，连接服务器
     */
    public void run() {
        NioEventLoopGroup eventExecutors = new NioEventLoopGroup(1);
        try {
            Bootstrap bootstrap = new Bootstrap()
                    .group(eventExecutors)
                    //设置客户端的通道实现
                    .channel(NioSocketChannel.class)
                    .handler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            ChannelPipeline pipeline = ch.pipeline();
                            pipeline.addLast("decoder",new StringDecoder());
                            pipeline.addLast("encoder",new StringEncoder());
                            pipeline.addLast("groupChatClientHandler",new GroupChatClientHandler());
                        }
                    });
            ChannelFuture channelFuture= bootstrap.connect(HOST, PORT).sync();

            Channel channel = channelFuture.channel();
            System.out.println("------"+channel.localAddress()+"------");
            //获取控制台的输入
            Scanner scanner = new Scanner(System.in);
            while (scanner.hasNextLine()){
                String msg = scanner.nextLine();
                channel.writeAndFlush(msg);
            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            eventExecutors.shutdownGracefully();
        }

    }

    public static void main(String[] args) {
        new GroupChatClient().run();
    }
}
