package classloader;

/**
 * @author Hai
 * @program: learn-everyday
 * @description:
 * @create 2021/9/6 - 22:32
 **/
public class ClassInitTest1 {

    static class Son extends Father{


        public static int B = A;
    }

    static class Father{
        static int A=1;
        static {
            A =2;
        }
    }

    public static void main(String[] args) {
        System.out.println(Son.B);
    }
}
