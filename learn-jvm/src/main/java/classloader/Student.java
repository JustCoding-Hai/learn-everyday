package classloader;

/**
 * @author Hai
 * @program: learn-everyday
 * @description:
 * @create 2021/2/5 - 23:09
 **/
public class Student {
    public static void main(String[] args) throws ClassNotFoundException {
        Student student = new Student();
        //获取Class对象的三种方式与获取类加载器
        System.out.println(student.getClass().getClassLoader());
        System.out.println(Student.class.getClassLoader());
        System.out.println(Class.forName("classloader.Student").getClassLoader());
        //JDK自带的类,不需要类加载
        System.out.println(String.class.getClassLoader());
    }
}
