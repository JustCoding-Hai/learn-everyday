package top.javahai.netty.heartbeat;

import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandler;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.timeout.IdleStateEvent;
import io.netty.util.NettyRuntime;
import io.netty.util.concurrent.EventExecutorGroup;

/**
 * @author Hai
 * @program: learn-everyday
 * @description:
 * @create 2020/12/17 - 23:43
 **/
public class MyServerHandler extends ChannelInboundHandlerAdapter {

    /**
     * 当前一个处理器的IdleStateEvent触发后，就触发了后一个处理器的userEventTriggered进行处理
     * @param ctx
     * @param evt
     * @throws Exception
     */
    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        if (evt instanceof IdleStateEvent){
            IdleStateEvent event=(IdleStateEvent)evt;
            String eventType=null;
            switch (event.state()){
                case READER_IDLE:
                    eventType="读空闲";
                    break;
                case WRITER_IDLE:
                    eventType="写空闲";
                    break;
                case ALL_IDLE:
                    eventType="读写空闲";
                    break;
                 default:
                     eventType="";
            }
            System.out.println(ctx.channel().remoteAddress()+"出现了空闲："+eventType);
        }

    }
}
