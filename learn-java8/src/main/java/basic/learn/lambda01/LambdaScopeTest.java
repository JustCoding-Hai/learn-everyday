package basic.learn.lambda01;

import java.util.ArrayList;

/**
 * Lambda作用域测试
 *
 * @author Hai
 * @program: learn-everyday
 * @description:
 * @create 2021/12/9 - 0:18
 **/
public class LambdaScopeTest {

    /**
     * 静态变量
     */
    private static String staticVar;

    /**
     * 实例变量
     */
    private  int instanceIntVar = 3 ;

    /**
     * 实例变量
     */
    private  String instanceVar;




    @FunctionalInterface
    interface VarChangeInterface{
        Integer change(String str);
    }

    /**
     * 测试实例变量
     */
    void testInstance(){

        instanceIntVar=2;
        VarChangeInterface varChangeInterface2 = (str2 -> {
            instanceIntVar =1;
            return 1;
        });
        Integer str2 =varChangeInterface2.change("111") ;
        System.out.println(instanceIntVar);
    }

    /**
     * 测试引用变量
     */
    private void  testReferenceVar(){
        ArrayList<String> list = new ArrayList<>();
        list.add("111");
        //访问外部引用局部引用变量
        VarChangeInterface varChangeInterface = ((str) -> {
            Integer.valueOf(list.get(0));
//            list = new ArrayList<>();
            return 1;
        });
        //修改局部引用变量
        list.set(0,"222");
        Integer str =varChangeInterface.change("");
        System.out.println(str);
    }

    /**
     * 测试静态变量
     */
    void testStaticVar(){
        staticVar="222";
        VarChangeInterface varChangeInterface = (str -> Integer.valueOf(str+staticVar));
        staticVar="333";
        Integer str =varChangeInterface.change("111") ;
        System.out.println(str);
    }

    /**
     * 测试实例变量
     */
    void testInstanceVar(){
        instanceVar="222";
        VarChangeInterface varChangeInterface = (str -> Integer.valueOf(str+instanceVar));
        instanceVar="333";
        Integer str =varChangeInterface.change("111") ;
        System.out.println(str);

        instanceIntVar=2;
        VarChangeInterface varChangeInterface2 = (str2 -> {
            instanceIntVar =1;
            return 1;
        });
//        instanceIntVar=3;
        Integer str1 =varChangeInterface.change("111") ;
        Integer str2 =varChangeInterface2.change("111") ;
        System.out.println(instanceIntVar);
    }

    public static void main(String[] args) {
//        new LambdaScopeTest().testReferenceVar();
//        new LambdaScopeTest().testStaticVar();
        new LambdaScopeTest().testInstanceVar();
    }
}
