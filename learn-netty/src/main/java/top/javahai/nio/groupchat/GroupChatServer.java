package top.javahai.nio.groupchat;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Iterator;
import java.util.Set;

/**
 * @author Hai
 * @program: learn-everyday
 * @description: 群聊服务器
 * @create 2020/12/5 - 11:29
 **/
public class GroupChatServer {

    private Selector selector;
    private ServerSocketChannel listenChannel;
    /**服务器绑定的地址*/
    private static final InetSocketAddress INET_SOCKET_ADDRESS=new InetSocketAddress(6667);

    /**
     * 初始化工作
     */
    public GroupChatServer() {
        try {
            selector=Selector.open();
            System.out.println("selector="+selector.hashCode());
            listenChannel=ServerSocketChannel.open();
            listenChannel.socket().bind(INET_SOCKET_ADDRESS);
            //设置为非阻塞的
            listenChannel.configureBlocking(false);
            listenChannel.register(selector, SelectionKey.OP_ACCEPT);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 监听通道的连接事件
     */
    public void listen(){
        while (true){
            try {
                if (selector.select(5000)>0){
                    //获取所有连接的SelectorKeys
                    Iterator<SelectionKey> keyIterator = selector.selectedKeys().iterator();
                    while (keyIterator.hasNext()){
                        SelectionKey key = keyIterator.next();
                        //如果是连接事件
                        if (key.isAcceptable()){
                            //创建一个socketChannel连接
                            SocketChannel socketChannel = listenChannel.accept();

                            System.out.println("socketChannel="+socketChannel.hashCode());
                            //设置为非阻塞的
                            socketChannel.configureBlocking(false);
                            //注册连接到Selector
                            socketChannel.register(selector,SelectionKey.OP_READ);
                            //通知新的连接产生
                            System.out.println(socketChannel.getRemoteAddress()+"上线了");
                        }
                        //如果是可读的，即socketChannel发来了消息
                        if (key.isReadable() && key.channel() instanceof SocketChannel){
                            readData(key);
                        }

                        //处理完key，需要从SelectionKey中移除
                        keyIterator.remove();
                    }

                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 读取SocketChannel通道中的消息并转发到其他的通道中
     * @param key
     */
    private void readData(SelectionKey key) throws IOException {
        SocketChannel socketChannel=null;
        try {
            socketChannel= ((SocketChannel) key.channel());
            //数据超出长度的情况？
            ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
            int count = socketChannel.read(byteBuffer);
            if(count>0){
                String msg = new String(byteBuffer.array());

                System.out.println(msg);

                //转发给其他客户端
                sendMsgToOthers(msg,socketChannel);
            }
        }catch (IOException e){
            try {
                System.out.println(socketChannel.getRemoteAddress()+"下线了");
                //取消注册
                key.cancel();
                //关闭通道
                socketChannel.close();
            }catch (Exception e1){
                e1.printStackTrace();
            }

        }

    }

    /**
     * 转发消息给除了自己的客户端
     * @param msg
     * @param self
     */
    private void sendMsgToOthers(String msg, SocketChannel self) throws IOException {
        //获取所有注册在Selector的selectionKeys
        Set<SelectionKey> selectionKeys = selector.keys();

        for (SelectionKey selectionKey : selectionKeys) {
            //获取通道
            SelectableChannel channel = selectionKey.channel();

            if (channel instanceof SocketChannel && channel!=self){
                SocketChannel destChannel = (SocketChannel) channel;

                destChannel.write(ByteBuffer.wrap(msg.getBytes()));
            }
        }

    }


    public static void main(String[] args) {
        GroupChatServer groupChatServer = new GroupChatServer();
        groupChatServer.listen();

    }
}
