package basic.learn.lambda01;

import org.junit.Test;

import java.io.PrintStream;
import java.util.Comparator;
import java.util.function.BiPredicate;
import java.util.function.Consumer;
import java.util.function.Supplier;

/**
 * @author Hai
 * @date 2020/6/13 - 16:21
 * 方法引用：若Lambda体中的内容有方法已经实现了，我们可以使用“方法引用”
 *          可以理解为方法引用是Lambda表达式的另外一种表现形式
 * 主要有三种语法格式：
 *  对象::实例方法名
 *  类::静态方法名
 *  类::实例方法名
 *
 * 注意：
 * 1.lambda体中调用方法的参数列表与返回值类型，要与函数式接口中抽象方法的函数列表和返回值类型保持一致
 * 2.若Lambda参数列表中的第一参数是实例方法的调用者，而第二个参数是实例方法的参数时，可以使用ClassName::method
 */
public class TestLambda04 {
  /**
   *PrintStream中的println方法定义
   *     public void println(String x) {
   *         synchronized (this) {
   *             print(x);
   *             newLine();
   *         }
   *     }
   */
  //对象::实例方法名
  @Test
  public void test1(){
    PrintStream out = System.out;
    Consumer<String> consumer=out::println;
    consumer.accept("hello");
    Consumer<String> consumer02=out::println;
    consumer.accept("hello");
    Employee employee = new Employee(1, "admin", 18);
    Supplier<String> supplier=employee::getName;
    System.out.println(supplier.get());
  }
  // 类::静态方法名
  @Test
  public void test2(){
    /**
     * Integer类中的静态方法compare的定义：
     *     public static int compare(int x, int y) {
     *         return (x < y) ? -1 : ((x == y) ? 0 : 1);
     *     }
     */
    Comparator<Integer> comparable=(x,y)->Integer.compare(x,y);
    //使用方法引用实现相同效果
    Comparator<Integer> integerComparable=Integer::compare;
    System.out.println(integerComparable.compare(4,2));//结果：1
    System.out.println(comparable.compare(4,2));//结果：1
  }
  //  类::实例方法名
  @Test
  public void test3(){
    BiPredicate<String,String> bp=(x,y)->x.equals(y);
    //使用方法引用实现相同效果
    BiPredicate<String,String> bp2=String::equals;
    System.out.println(bp.test("1","2"));//结果：false
    System.out.println(bp.test("1","2"));//结果：false
  }
}
