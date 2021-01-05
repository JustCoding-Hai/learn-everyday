package top.javahai.netty.inandoutboundhandler;

import com.sun.xml.internal.bind.v2.runtime.output.StAXExStreamWriterOutput;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageCodec;
import io.netty.handler.codec.MessageToByteEncoder;

import java.util.List;

/**
 * @author Hai
 * @program: learn-everyday
 * @description:
 * @create 2020/12/25 - 21:06
 **/
public class MyLongToByteEncoder extends ByteToMessageCodec<Long> {

    /**
     * 客户端向服务端发送消息。向对消息进行编码处理，转码成字节
     * @param ctx
     * @param msg
     * @param out
     * @throws Exception
     */
    @Override
    protected void encode(ChannelHandlerContext ctx, Long msg, ByteBuf out) throws Exception {
        System.out.println("MyLongToByteEncoder encode 被调用");
        System.out.println("发送的msg："+msg);
        out.writeLong(msg);
    }

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
        System.out.println("MyLongToByteEncoder decode 被调用");
        out.add(in.readLong());
    }

    /**
     *
     * 如果发送的不是该编码器指定的数据类型，则发送的数据不会经过这个编码器的encode方法处理。
     *
     * 具体执行写的操作如下：
     *
     *     @Override
     *     public void write(ChannelHandlerContext ctx, Object msg, ChannelPromise promise) throws Exception {
     *         ByteBuf buf = null;
     *         try {
     *             if (acceptOutboundMessage(msg)) {
     *                 @SuppressWarnings("unchecked")
     *                 I cast = (I) msg;
     *                 buf = allocateBuffer(ctx, cast, preferDirect);
     *                 try {
     *                     encode(ctx, cast, buf);
     *                 } finally {
     *                     ReferenceCountUtil.release(cast);
     *                 }
     *
     *                 if (buf.isReadable()) {
     *                     ctx.write(buf, promise);
     *                 } else {
     *                     buf.release();
     *                     ctx.write(Unpooled.EMPTY_BUFFER, promise);
     *                 }
     *                 buf = null;
     *             } else {
     *                 ctx.write(msg, promise);
     *             }
     *         } catch (EncoderException e) {
     *             throw e;
     *         } catch (Throwable e) {
     *             throw new EncoderException(e);
     *         } finally {
     *             if (buf != null) {
     *                 buf.release();
     *             }
     *         }
     *     }
     */
}
