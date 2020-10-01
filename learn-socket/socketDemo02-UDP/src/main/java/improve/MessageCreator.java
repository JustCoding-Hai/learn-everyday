package improve;

/**
 * @author Hai
 * @date 2020/5/6 - 9:24
 *构建消息实体
 */
public class MessageCreator {
  private static final String SN_HEADER="收到暗号，我是(SN)：";//提供服务端使用
  private static final String POST_HEADER="这是暗号，请回电端口（Port）：";//提供客户端使用

  public static String buildWithPort(int port){
    return POST_HEADER+port;
  }
  public static int parsePort(String data){
    if (data.startsWith(POST_HEADER)){
      return Integer.parseInt(data.substring(POST_HEADER.length()));
    }
   return -1;
  }
  public static String buildWithSn(String sn){
    return SN_HEADER+sn;
  }
  public static String parseSn(String data){
    if (data.startsWith(SN_HEADER)){
      return data.substring(POST_HEADER.length());
    }
    return null;
  }
}
