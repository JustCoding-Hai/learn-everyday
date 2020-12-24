package top.javahai.netty.codec2;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import top.javahai.netty.codec.StudentPOJO;

import java.util.Random;

/**
 * @author Hai
 * @program: learn-everyday
 * @description: 客户端处理器
 * @create 2020/12/6 - 19:16
 **/
public class NettyClientHandler extends ChannelInboundHandlerAdapter {

    /**
     * 通道就绪了就触发该方法
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        int randomNum = new Random().nextInt(2);
        MyDataInfo.MyMessage message=null;
        if (0==randomNum){
            message=MyDataInfo.MyMessage.newBuilder().setDataType(MyDataInfo.MyMessage.DataType.StudentType).setStudent(
                    MyDataInfo.Student.newBuilder().setId(5).setName("科比").build()
            ).build();
        }else{
            message=MyDataInfo.MyMessage.newBuilder().setDataType(MyDataInfo.MyMessage.DataType.WorkerType).setWorker(
                    MyDataInfo.Worker.newBuilder().setName("打工人").setAge(18).build()
            ).build();
        }
        ctx.writeAndFlush(message);
    }

    /**
     * 异常发生时触发
     * @param ctx
     * @param cause
     * @throws Exception
     */
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
