package chapther2;

/**
 * @author Hai
 * @program: learn-everyday
 * @description:
 * @create 2021/9/6 - 23:51
 **/
public class ClassLoaderTest {
    public static void main(String[] args) {
        //获取系统类加载器
        ClassLoader systemClassLoader = ClassLoader.getSystemClassLoader();
        System.out.println(systemClassLoader);//sun.misc.Launcher$AppClassLoader@58644d46

        //获取系统类加载器的上层
        ClassLoader extClassLoader = systemClassLoader.getParent();
        System.out.println(extClassLoader);//sun.misc.Launcher$ExtClassLoader@78308db1

        //获取extClassLoader的上层
        ClassLoader bootstrapClassLoader = extClassLoader.getParent();
        System.out.println(bootstrapClassLoader);//null，规定无法直接获取BootstrapClassLoader

        //获取自定义类的加载器,默认使用AppClassLoader
        System.out.println(ClassLoaderTest.class.getClassLoader());//sun.misc.Launcher$AppClassLoader@58644d46

        //String类使用引导类加载器进行加载，Java的核心类库都是使用引导类加载器进行加载的
        System.out.println(String.class.getClassLoader());

    }
}
