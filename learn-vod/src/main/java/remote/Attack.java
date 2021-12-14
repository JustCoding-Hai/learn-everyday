package remote;

/**
 * @author Hai
 * @program: learn-everyday
 * @description:
 * @create 2021/12/12 - 23:09
 **/

import javax.naming.Context;
import javax.naming.Name;
import javax.naming.spi.ObjectFactory;
import java.util.Hashtable;

/**
 * 攻击者
 */
public class Attack implements ObjectFactory {

    static {
        System.out.println("静态代码块攻击");
    }


    @Override
    public Object getObjectInstance(Object obj, Name name, Context nameCtx, Hashtable<?, ?> environment) throws Exception {
        System.out.println("你被攻击了");
        return "【攻击者】";
    }
}
