package improve;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.util.UUID;

/**
 * @author Hai
 * @date 2020/5/5 - 21:50
 * UDP服务提供者，相当于服务端
 */
public class UDPProvider {
  public static void main(String[] args) throws IOException {
    //生成唯一标识
    String sn=UUID.randomUUID().toString();
    Provider provider = new Provider(sn);
    provider.start();
    //读取到控制台输入即进行退出
    System.in.read();
    provider.exit();
  }
  private static class Provider extends Thread{
    private String sn;
    private boolean done=false;
    private DatagramSocket ds;

    public Provider(String sn){
      this.sn=sn;
    }

    @Override
    public void run() {
      /*
    接收客户端发送的数据
     */
      try {
      System.out.println("UDPProvider started!");
      //1.创建作为接收者(服务端)DatagramSocket，指定一个端口用于数据接收
       ds = new DatagramSocket(20000);
       while (!done) {
         //2.构建数据报作为接收实体，用于接收客户端发送的数据
         final byte[] buf = new byte[1024];//创建字节数组，指定接收的数据报的大小
         DatagramPacket receivePacket = new DatagramPacket(buf,buf.length);
         //3.接收客户端发送的数据
         ds.receive(receivePacket);//此方法在接收到数据报之前会一直阻塞
         //4.读取数据，打印收到的信息与发送者的信息
         String ip = receivePacket.getAddress().getHostAddress();//发送者的ip地址
         int port = receivePacket.getPort();  //端口号
         int dataLength = receivePacket.getLength();
         String data = new String(receivePacket.getData(), dataLength);

         System.out.println("发送者的ip地址:" + ip);
         System.out.println("端口号:" + port);
         //发送的数据包
         System.out.println("发送的数据:" + data);
        /*
        响应客户端，构建一份回送数据
         */
         int responsePort=MessageCreator.parsePort(data);
         if (responsePort!=-1&&port==responsePort) {
           String responseData = MessageCreator.buildWithSn(sn);
           byte[] responseDataBytes = responseData.getBytes();
           DatagramPacket responsePacket = new DatagramPacket(responseDataBytes, responseDataBytes.length,
                   receivePacket.getAddress(), responsePort);
           ds.send(responsePacket);
         }
       }
      } catch (IOException ignored) {//忽略异常信息
       //e.printStackTrace();
      }finally {
        close();
      }
      //关闭资源
      System.out.println("UDPProvider finished!");
    }
    private void close(){
      if (ds!=null){
        ds.close();
        ds=null;
      }
    }
    void exit(){
      done=true;
      close();
    }
  }
}
