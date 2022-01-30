package chapther2;

import sun.misc.Launcher;

import java.net.URL;

/**
 * @author Hai
 * @program: learn-everyday
 * @description:
 * @create 2021/9/6 - 23:51
 **/
public class ClassLoaderTest2 {
    public static void main(String[] args) {
        System.out.println("启动类加载器:");
        URL[] urLs = Launcher.getBootstrapClassPath().getURLs();
        for (URL urL : urLs) {
            System.out.println(urL.toExternalForm());
        }
        System.out.println("扩展类加载器:");
        String exDirs = System.getProperty("java.ext.dirs");
        for (String path : exDirs.split(";")) {
            System.out.println(path);
        }

    }
}
