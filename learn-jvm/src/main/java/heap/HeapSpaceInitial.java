package heap;

/**
 * @author Hai
 * @program: learn-everyday
 * @description: 堆空间大小DEMO
 * @create 2021/9/29 - 0:02
 **/
public class HeapSpaceInitial {
    public static void main(String[] args) {
        //返回JVM中堆内存总量
        long initialMemory = Runtime.getRuntime().totalMemory() / 1024 / 1024;
        //返回JVM试图使用的最大堆内存量
        long maxMemory = Runtime.getRuntime().maxMemory() / 1024 / 1024;
        System.out.println("-Xms:" + initialMemory + "M");
        System.out.println("-Xmx:" + maxMemory + "M");

//        System.out.println("系统内存大小为：" + initialMemory * 64 / 1024 + "G");
//        System.out.println("系统内存大小为：" + maxMemory * 4 / 1024 + "G");
    }
}
