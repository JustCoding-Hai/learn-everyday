import java.io.*;
import java.net.*;

/**
 * @author Hai
 * @date 2020/5/5 - 11:51
 */
public class Client {
  public static void main(String[] args) throws IOException {
    Socket socket=new Socket();
    //超时时间3s
    socket.setSoTimeout(3000);
    //连接本地，端口2000
    socket.connect(new InetSocketAddress(Inet4Address.getLocalHost(),2000));
    System.out.println("已发起服务器连接请求，并进入后续流程");
    System.out.println("客户端信息：【ip:"+socket.getLocalAddress().getHostAddress()+"，port:"+socket.getLocalPort()+"】");
    System.out.println("服务端信息：【ip:"+socket.getInetAddress().getHostAddress()+"，port:"+socket.getPort()+"】");
    try {
      todo(socket);
    }catch (Exception e){
      e.printStackTrace();
      System.out.println("异常关闭！");
    }finally {
     socket.close();
      System.out.println("客户端已退出！");
    }
  }

  private static void todo(Socket client) throws IOException {
    //构建键盘输入流
    InputStream in=System.in;
    BufferedReader input = new BufferedReader(new InputStreamReader(in));
    //构建Socket输入流，获取服务器返回的数据
    BufferedReader socketBufferedReader = new BufferedReader(
            new InputStreamReader(client.getInputStream()));
    //构建Socket输出流，发送数据到服务器
    PrintStream socketPrintStream = new PrintStream(client.getOutputStream());

    boolean flag=true;
    do{
      //读取一行
      String str = input.readLine();
      //发送到服务器
      socketPrintStream.println(str);
      //读取服务器返回值
      String msg = socketBufferedReader.readLine();
      if ("bye".equalsIgnoreCase(str)){
        flag=false;
      }else{
        System.out.println(msg);
      }
    }while (flag);

    //释放资源
    socketBufferedReader.close();
    socketPrintStream.close();
  }
}
