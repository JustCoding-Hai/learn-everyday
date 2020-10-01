package basic.learn.lambda01;

import org.junit.Test;

import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * @author Hai
 * @date 2020/6/13 - 23:59
 * 构造器引用:
 * 语法：类名::new
 * 注意:调用的构造器的参数列表要与函数式接口中抽象方法的参数列表保持一致。
 *
 * 数组引用：
 * 语法：类型[]::new
 *
 */
public class TestLambda05 {
  //构造器引用
  @Test
  public void test01(){
    //引用无参构造器
    Supplier<Employee> supplier=Employee::new;
    System.out.println(supplier.get());
    //引用有参构造器
    Function<Integer,Employee> function=Employee::new;
    System.out.println(function.apply(21));
    BiFunction<Integer,Integer,Employee> biFunction=Employee::new;
    System.out.println(biFunction.apply(8,24));
  }
  //数组引用
  @Test
  public void test02(){
    Function<Integer,String[]> function=String[]::new;
    String[] apply = function.apply(10);
    System.out.println(apply.length);//结果：10

  }

}
