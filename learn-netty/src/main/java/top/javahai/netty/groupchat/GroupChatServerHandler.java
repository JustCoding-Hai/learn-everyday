package top.javahai.netty.groupchat;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.concurrent.GlobalEventExecutor;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author Hai
 * @program: learn-everyday
 * @description: 群聊通道处理器
 * @create 2020/12/16 - 21:42
 **/
public class GroupChatServerHandler extends SimpleChannelInboundHandler<String> {

    /**
     * 拓展：
     * 私聊实现思路：
     * 1.新建User类作为登录对象：id，name，channelId
     * 2.使用HashMap存放key=channelId，value=channel
     * 3.channelRead0读取消息获取发送对象，从HashMap中取对应通道写入数据
     * 4.handlerAdded放入通道到HashMap
     */

    /**
     *定义一个channel组，管理所有的channel，channelGroup一定要是静态的,因为每个客户端建立的通道都会新建一个Handler，
     * 如果为非静态，则每个通道不能共享ChannelGroup。
     */
    private static ChannelGroup channelGroup=new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);

    /**
     * 读取数据，并转发给除了自己以外的客户端
     * @param ctx
     * @param msg
     * @throws Exception
     */
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
        //当前发送消息的channel
        Channel currentChannel = ctx.channel();
        channelGroup.forEach(channel -> {
            //给其他的客户端转发消息
            if (channel!=currentChannel){
                channel.writeAndFlush(getCurrentTime()+"-客户端【"+currentChannel.remoteAddress()+"】:"+msg);
            } else{
                //给自己回显消息
                channel.writeAndFlush(getCurrentTime()+"-我:"+msg);
            }
        });

    }
    /**
     * Gets called after the ChannelHandler was added to the actual context and it's ready to handle events.
     * 该ChannelHandler添加到实际的ChannelHandlerContext后触发调用，此时广播用户登录的消息
     * @param ctx
     * @throws Exception
     */
    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        Channel channel = ctx.channel();
        //将channelGroup中的所有channel遍历并发送消息
        channelGroup.writeAndFlush(getCurrentTime()+"-客户端【"+channel.remoteAddress()+"】加入了聊天室");
        channelGroup.add(channel);
        System.out.println(channel.id());
    }

    /**
     * Gets called after the  ChannelHandler was removed from the actual context and it doesn't handle events.
     * 此时客户端断开连接，将某个客户离开的消息推送给当前在线的用户
     * @param ctx
     * @throws Exception
     */
    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        Channel channel = ctx.channel();
        channelGroup.writeAndFlush(getCurrentTime()+"-客户端【"+channel.remoteAddress()+"】离开了聊天室");
        //channel已经移出channelGroup，无需手动移出
        System.out.println("channelGroup size:"+channelGroup.size());
    }

    /**
     * * The {@link Channel} of the {@link ChannelHandlerContext} is now active
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println(getCurrentTime()+"-"+ctx.channel().remoteAddress()+"上线了~");
    }

    /**
     * The {@link Channel} of the {@link ChannelHandlerContext} was registered is now inactive and reached its
     * end of lifetime.
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        System.out.println(getCurrentTime()+"-"+ctx.channel().remoteAddress()+"下线了~");
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        ctx.close();
    }

    public String getCurrentTime(){
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return dateFormat.format(new Date());
    }

}
