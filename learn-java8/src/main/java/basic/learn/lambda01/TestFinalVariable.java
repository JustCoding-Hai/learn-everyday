package basic.learn.lambda01;

/**
 * @author Hai
 * @program: learn-everyday
 * @description:
 * @create 2021/12/9 - 0:18
 **/
public class TestFinalVariable {

    interface VarTestInterface{
        Integer change(String str);
    }

    public static void main(String[] args) {
        //局部变量不使用final修饰
        int tempInt = 1;
        VarTestInterface var = (str -> {
            System.out.println(tempInt);;
        return 1;});
        //再次修改,不符合隐式final定义
//        tempInt =2;
        Integer str =var.change("111") ;
        System.out.println(str);
    }


    /**
     * lambda表达式里不允许声明一个与局部变量同名的参数或者局部变量。
     */
//    public void test1(){
//        //定义局部变量
//        String temp = "222";
//        Integer tempInt = 1;
//        VarTestInterface varTest01 = (tempInt -> Integer.valueOf(tempInt));
//        VarTestInterface varTest02 = (str -> {
//            Integer tempInt = 1;
//            Integer.valueOf(str);
//        });
//        //再次修改,不符合隐式final定义
//        temp = "333";
//        Integer str =varTest01.change("111") ;
//        System.out.println(str);
//        System.out.println(temp);
//
//    }

}
