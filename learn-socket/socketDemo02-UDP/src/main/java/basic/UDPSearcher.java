package basic;

import java.io.IOException;
import java.net.*;

/**
 * @author Hai
 * @date 2020/5/5 - 21:51
 * UDP搜索者，用于搜索服务支持方，相当于客户端
 */
public class UDPSearcher {
  public static void main(String[] args) throws IOException {
    /*
    向服务器发送数据
     */
    System.out.println("basic.UDPSearcher started!");
    //1.创建DatagramSocket对象，作为发送者
    DatagramSocket ds = new DatagramSocket();
    //2.创建数据报，包含发送的数据信息
    String sendData = "Hello World！";
    byte[] sendDataBytes = sendData.getBytes();
    DatagramPacket sendPacket = new DatagramPacket(sendDataBytes,sendDataBytes.length);
    //3.定义服务器的地址、端口号
    sendPacket.setAddress(InetAddress.getLocalHost()); //获取本机的ip地址
    sendPacket.setPort(20000); //目标端口
    //4.发送数据
    ds.send(sendPacket);
     /*
    接收服务端的响应数据
     */
    //1.创建数据报，用于接收服务器端响应的数据
    final byte[] buf = new byte[1024];
    DatagramPacket receivePacket = new DatagramPacket(buf,buf.length);
    //2.接收服务器响应的数据
    ds.receive(receivePacket);
    //3.读取数据，打印收到的数据与发送者的信息
    String ip = receivePacket.getAddress().getHostAddress();
    int port = receivePacket.getPort();
    int dataLength = receivePacket.getLength();
    String data = new String(receivePacket.getData(), 0, dataLength);
    System.out.println("发送者的ip地址:"+ip+"\n端口号:"+port+"\n发送的数据:"+data);
    //4.关闭资源
    System.out.println("basic.UDPSearcher finished!");
    ds.close();
  }
}
