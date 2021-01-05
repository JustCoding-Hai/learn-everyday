package top.javahai.netty.codec2;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.ByteToMessageDecoder;
import io.netty.handler.codec.protobuf.ProtobufEncoder;

/**
 * @author Hai
 * @program: learn-everyday
 * @description: Netty客户端
 * @create 2020/12/6 - 19:10
 **/
public class NettyClient {
    public static void main(String[] args) {

        NioEventLoopGroup group = new NioEventLoopGroup();

        try {
            //客户端启动对象
            Bootstrap bootstrap = new Bootstrap();
            //设置相关参数
            bootstrap.group(group)
                    .channel(NioSocketChannel.class)
                    .handler(new ChannelInitializer<SocketChannel>() {
                        //pipeline加入处理器
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            ch.pipeline().addLast(new ProtobufEncoder())
                                    .addLast(new NettyClientHandler());
                        }
                    });

            System.out.println("客户端启动成功！");
            //启动客户端去连接服务器端
            ChannelFuture channelFuture= bootstrap.connect("127.0.0.1", 6668).sync();
            //对关闭通道进行监听
            channelFuture.channel().closeFuture().sync();

        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            group.shutdownGracefully();
        }
    }
}
