package heap;

/**
 * @author Hai
 * @program: learn-everyday
 * @description:
 * @create 2021/2/6 - 15:17
 **/
public class Demo {

    public static void main(String[] args) {
        //虚拟机初始化的内存
        long totalMemory = Runtime.getRuntime().totalMemory();
        //虚拟机能使用的最大内存
        long maxMemory = Runtime.getRuntime().maxMemory();

        System.out.println("虚拟机初始化的内存："+totalMemory+"KB,"+(totalMemory/(double)1024/1024)+"MB");
        System.out.println("虚拟机最大内存："+maxMemory+"KB,"+(maxMemory/(double)1024/1024)+"MB");
    }
}
