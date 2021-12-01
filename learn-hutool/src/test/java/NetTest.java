import cn.hutool.core.net.NetUtil;
import cn.hutool.core.util.URLUtil;
import org.junit.Test;

/**
 * 网络相关方法测试
 *
 * @author huangjinhai
 * @date 2021-11-29
 */
public class NetTest {

    @Test
    public void testNet() {
        String ip = "192.168.2.65";
        long ipLong = 2130706433L;
        String long2ip = NetUtil.longToIpv4(ipLong);
        System.out.println(long2ip);
        long ip2long = NetUtil.ipv4ToLong(ip);
        System.out.println(ip2long);
        // 端口探测
        System.out.println("端口是否连通：" + NetUtil.isUsableLocalPort(6379));
        System.out.println("端口是否有效：" + NetUtil.isValidPort(77777));
        // IP脱敏
        System.out.println(NetUtil.hideIpPart(ip));
        // 获取网卡信息
        System.out.println(NetUtil.getNetworkInterfaces());
        // eth4 (Realtek PCIe GBE Family Controller)
        System.out.println(NetUtil.getNetworkInterface("eth4"));
        System.out.println(NetUtil.LOCAL_IP);
        System.out.println("=============================");
        System.out.println(NetUtil.getLocalHostName());

    }

    @Test
    public void testUrl() {
        System.out.println(URLUtil.toURI("http://www.baidu.com/df/sdf/df").getHost());
    }

    //TODO 图形验证码、图片处理、正则、身份证、脱敏、压缩、XML等工具类测试使用

    /**
     * PS：HUTOOL里面的HTTPUTIL，大家别用，实际工作中用apache或者其它的，因为hutool里的所有工具类源码是不引入外部其它依赖的，
     * 所以它的http工具类是没有连接池的，实际生产中应用会有很大的风险，大家摒弃
     */


}
