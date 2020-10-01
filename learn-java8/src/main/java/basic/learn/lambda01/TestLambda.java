package basic.learn.lambda01;

import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Comparator;
import java.util.function.Consumer;
import java.util.function.Supplier;

/**
 * @author Hai
 * @date 2020/6/12 - 16:28
 * Java8引入的新的操作符“->”该操作符称为箭头操作符或lambda操作符：
 *  左侧：Lambda表达式的参数列表
 *  右侧：Lambda表达式中所需执行的功能，即Lambda体
 *
 * Lambda表达式需要“函数式接口”的支持
 * 函数式接口：接口中只有一个抽象方法的接口，称为函数式接口。可以使用@FunctionalInstance修饰
 * 可以检查是否是函数式接口
 * * lambda表达式的语法练习：
 */
public class TestLambda{

  //自定义函数式接口并使用
  @Test
  public void test(){
    MyFuncInterface funcInterface=(x,y)->x*y;
    System.out.println(funcInterface.doMulti(3,8));
  }

  //1.无返回值，无参数
  @Test
  public void test01(){
    Runnable runnable=()-> System.out.println("Runnable 运行");
    runnable.run();//结果：Runnable 运行
  }
  //2.无返回值，有一个参数
  @Test
  public void test02(){
    Consumer<String> consumer=(x)-> System.out.println(x);
    consumer.accept("Hello Consumer");//结果：Hello Consumer
  }
  //3.无返回值，只有一个参数，小括号可以不写
  @Test
  public void test03(){
    Consumer consumer=x-> System.out.println(x);
    consumer.accept("Hello！");
  }
  //4.有返回值，有两个以上的参数，并且lambda体中有多条语句
  @Test
  public void test04(){
    Comparator<Integer> com=(x, y)->{
      System.out.println("函数式接口");
      return Integer.compare(x,y);
    };
    System.out.println(com.compare(2,4));//结果：-1
  }
  //5.有返回值，若Lambda体中只有一条语句，return和大括号都可以省略不写
  @Test
  public void test05(){
    Comparator<Integer> com=(x, y)-> Integer.compare(x,y);
    System.out.println(com.compare(4,2));//1
  }
  //6.Lambda表达式的参数列表的数据类型可以省略不写，
  // 因为JVM可以通过上下文推断出数据类型，即“类型推断”
  @Test
  public void test06() {
    Comparator<Integer> com = (Integer x, Integer y) -> Integer.compare(x, y);
    System.out.println(com.compare(4, 2));//结果：1
  }

  public String toLowerString(MyFuncInterf<String> mf,String origin){
    return mf.getValue(origin);
  }
  @Test
  public void test07(){
      String value=toLowerString((str)->{
        return str.toLowerCase();
      },"ABC");
    System.out.println(value);//结果ABC
  }
}
