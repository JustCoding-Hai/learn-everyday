package basic;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

/**
 * @author Hai
 * @date 2020/5/5 - 21:50
 * UDP服务提供者，相当于服务端
 */
public class UDPProvider {
  public static void main(String[] args) throws IOException {
     /*
    接收客户端发送的数据
     */
    System.out.println("basic.UDPProvider started!");
    //1.创建作为接收者(服务端)DatagramSocket，指定一个端口用于数据接收
    DatagramSocket ds = new DatagramSocket(20000);
    //2.构建数据报作为接收实体，用于接收客户端发送的数据
    final byte[] buf = new byte[1024];//创建字节数组，指定接收的数据报的大小
    DatagramPacket receivePacket = new DatagramPacket(buf,0, buf.length);
    //3.接收客户端发送的数据
    ds.receive(receivePacket);//此方法在接收到数据报之前会一直阻塞
    //4.读取数据，打印收到的信息与发送者的信息
    String ip = receivePacket.getAddress().getHostAddress();//发送者的ip地址
    int port = receivePacket.getPort();  //端口号
    int dataLength = receivePacket.getLength();
    String data = new String(receivePacket.getData(), 0, dataLength);
    System.out.println("发送者的ip地址:"+ip);
    System.out.println("端口号:"+port);
    //发送的数据包
    System.out.println("发送的数据:"+data);
    /*
    响应客户端，构建一份回送数据
     */
    String receiveData = "接收到的数据长度为：" + dataLength;
    byte[] receiveDataBytes = receiveData.getBytes();
    DatagramPacket responsePacket = new DatagramPacket(receiveDataBytes,receiveDataBytes.length, receivePacket.getSocketAddress());
    ds.send(responsePacket);
    //关闭资源
    System.out.println("basic.UDPProvider finished!");
    ds.close();
  }
}
