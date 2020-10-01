import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author Hai
 * @date 2020/5/5 - 12:48
 */
public class Server {
  public static void main(String[] args) throws IOException {
    ServerSocket server=new ServerSocket(2000);
    System.out.println("服务端已就绪~");
    System.out.println("服务端信息：【"+"ip:"+server.getInetAddress().getHostAddress()+"p:"+server.getLocalPort());
    //等待客户端连接
    while (true){
      //等到客户端请求
      Socket client = server.accept();
      //构建异步线程
      clientHandler clientHandler = new clientHandler(client);
      //启动线程，处理客户端请求
      clientHandler.start();
    }
  }
  /*
  处理客户端消息
   */
  private static class clientHandler extends Thread{
    private Socket client;
    private Boolean flag=true;
    PrintStream socketOutput;
    BufferedReader socketInput;

    public clientHandler(Socket client){
      this.client=client;
    }
    @Override
    public void run() {
      System.out.println("客户端连接信息：【ip:"+client.getInetAddress()+"，port："+client.getLocalPort()+"】");
      try{
        //得到打印流，用于数据输出，回送数据到客户端
         socketOutput = new PrintStream(client.getOutputStream());
        //得到输入流，用于接受数据
         socketInput = new BufferedReader(
                new InputStreamReader(client.getInputStream()));
        do{
          //从客户端中拿到一条数据
          String str = socketInput.readLine();
          if("bye".equalsIgnoreCase(str)){
            flag=false;
            socketOutput.println("bye" );
          }else{
            System.out.println(str);
            socketOutput.println("回送数据："+str.length());
          }
        }while (flag);

      }catch (Exception e){
        e.printStackTrace();
        System.out.println("连接异常断开！");
      }finally {
        //释放资源
        try {
          socketInput.close();
          socketOutput.close();
          client.close();
          System.out.println("客户端已关闭,"+"客户端连接信息：【ip:"+client.getInetAddress().getHostAddress()+"，Port："+client.getLocalPort()+"】");
        }catch (Exception e){
          e.printStackTrace();
          System.out.println("关闭资源出错");
        }
      }
    }
  }
}
