package local;

/**
 * @author Hai
 * @program: learn-everyday
 * @description:
 * @create 2021/12/12 - 23:08
 **/

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * 被攻击者应用程序
 */
public class App {

    private static Logger log = LogManager.getLogger(App.class);

    public void register(String username){
        //....
        log.error("{},注册了账号",username);
    }

}

