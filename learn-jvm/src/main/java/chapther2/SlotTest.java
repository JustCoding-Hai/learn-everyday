package chapther2;

/**
 * @author Hai
 * @program: learn-everyday
 * @description: 局部变量表的slot相关demo
 * @create 2021/9/20 - 11:44
 **/
public class SlotTest {

    public void localVar1() {
        int a = 0;
        System.out.println(a);
        int b = 0;
    }

    public void localVar2() {
        {
            int a = 0;
            System.out.println(a);
        }
        int b = 0;

    }

    public static void main(String[] args) {

    }
}
