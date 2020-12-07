package top.javahai.bio;


import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @author Hai
 * @program: learn-everyday
 * @description: BIO服务器类
 * @create 2020/11/28 - 23:17
 **/
public class BIOServer {
    public static void main(String[] args) {
        //1.创建一个线程池
        ExecutorService cachedThreadPool = Executors.newCachedThreadPool();

        try {
            ServerSocket serverSocket = new ServerSocket(6666);
            System.out.println("启动服务器");
            while (true){
                System.out.println("等待连接。。。。");
                Socket socket = serverSocket.accept();
                System.out.println("连接了一个客户端");
                //2.如果有客户端连接，就创建一个线程
                cachedThreadPool.execute(() -> handler(socket));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }



    }

    private static void handler(Socket socket) {

        byte[] bytes = new byte[1024];

        try {
            //通过socket获取输入流
            InputStream inputStream = socket.getInputStream();
            //循环读取客户端发送的数据
            while (true){
                System.out.println("线程信息 id="+Thread.currentThread().getId()+ "线程名 name="+Thread.currentThread().getName());

                System.out.println("reading。。。。");

                //从输入流中读取一定数量的字节，并将其存储到缓冲区数组中 ，返回值为缓冲区数组的字节数
                //注意：直到输入了有效的输入流或在检测到文件末尾或引发异常之前，此方法将阻塞
                int read=inputStream.read(bytes);

                if (read!=-1){
                    System.out.println(new String(bytes,0,read));
                }else{
                    break;
                }

            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            System.out.println("关闭和客户端的连接");
            try{
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
