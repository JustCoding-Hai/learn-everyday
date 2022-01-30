package chapther2;

import java.util.Date;

/**
 * @author Hai
 * @program: learn-everyday
 * @description: 局部变量测试
 * @create 2021/9/17 - 22:37
 **/
public class LocalVariablesTest {
    public static void main(String[] args) {
        LocalVariablesTest localVariablesTest = new LocalVariablesTest();
        int num = 10;
        localVariablesTest.test1();
    }

    private void test1() {
        Date date = new Date();
        String name = "hello";
        String info = test2(date, name);
        System.out.println(date + name);
    }

    private String test2(Date date, String name) {
        date = null;
        name = "hi";
        double weight = 130.5;
        char gender = '男';
        return date + name ;
    }
}
