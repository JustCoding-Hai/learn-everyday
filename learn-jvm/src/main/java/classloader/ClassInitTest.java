package classloader;

/**
 * @author Hai
 * @program: learn-everyday
 * @description:
 * @create 2021/9/6 - 22:32
 **/
public class ClassInitTest {
    private static int num = 10;
    private static int anInt = 100;

    static {
        anInt = 200;
        num =20;
    }


    public static void main(String[] args) {
        System.out.println(num);
    }
}
