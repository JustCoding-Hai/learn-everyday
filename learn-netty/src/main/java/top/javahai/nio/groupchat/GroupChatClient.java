package top.javahai.nio.groupchat;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Scanner;

/**
 * @author Hai
 * @program: learn-everyday
 * @description: 群聊客户端
 * @create 2020/12/5 - 12:43
 **/
public class GroupChatClient {

    private Selector selector;

    private SocketChannel socketChannel;
    /**服务器的地址*/
    private final InetSocketAddress INET_SOCKET_ADDRESS=new InetSocketAddress("127.0.0.1",6667);
    private String username;


    /**
     * 客户端的初始化工作
     */
    public GroupChatClient() throws IOException {
        selector=Selector.open();
        System.out.println("selector="+selector);

        //连接服务器
        socketChannel= SocketChannel.open(INET_SOCKET_ADDRESS);
        socketChannel.configureBlocking(false);
        socketChannel.register(selector, SelectionKey.OP_READ);

        System.out.println("socketChannel="+socketChannel.hashCode());
        //socketChannel.getLocalAddress().toString():/127.0.0.1:4080
        username=socketChannel.getLocalAddress().toString().substring(1);
        System.out.println("客户端<"+username+">准备就绪~");
    }

    /**
     * 向服务端发送消息
     * @param msg
     */
    public void sendMsg(String msg){
        msg=username+": "+msg;
        //向通道写数据
        try {
            socketChannel.write(ByteBuffer.wrap(msg.getBytes()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 读取从服务器端发来的消息
     */
    public void readMsg(){
        try {
            int select = selector.select();
            if (select>0){
                Iterator<SelectionKey> keyIterator = selector.selectedKeys().iterator();
                while (keyIterator.hasNext()){
                    SelectionKey key = keyIterator.next();
                    if (key.isReadable()){
                        SocketChannel channel = (SocketChannel) key.channel();

                        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);

                        channel.read(byteBuffer);

                        String receiveMsg = new String(byteBuffer.array());

                        System.out.println(receiveMsg.trim());

                    }
                    keyIterator.remove();
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }





    }

    public static void main(String[] args) throws IOException {
        GroupChatClient groupChatClient = new GroupChatClient();
        //新启一个线程,每隔3秒读取一次服务器的消息
        new Thread(()->{
            while (true){
                groupChatClient.readMsg();
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        //发送消息到服务器
        Scanner scanner = new Scanner(System.in);

        while (scanner.hasNextLine()){
            String s = scanner.nextLine();
            groupChatClient.sendMsg(s);
        }




    }
}
