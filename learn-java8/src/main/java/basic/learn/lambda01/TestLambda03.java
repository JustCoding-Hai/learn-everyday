package basic.learn.lambda01;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

/**
 * @author Hai
 * @date 2020/6/13 - 15:23
 *
 * Java8内置的四大核心函数式接口
 * 1.Consumer<T>:消费型接口
 *     void accept(T t)
 * 2.Supplier<T>：供给型接口
 *     T get()
 * 3.Function<T,R>:函数型接口
 *    R apply(T t)
 * 4.Predicate<T>:断言型接口
 *    boolean test(T t)
 */
public class TestLambda03 {

  //  1.Consumer<T>:消费型接口   void accept(T t)
  @Test
  public void test01(){
    makeMoney(100,t-> System.out.println("今天赚了"+t));
  }
  public void makeMoney(Integer money, Consumer<Integer> consumer){
    consumer.accept(money);
  }
  /**
   * 2.Supplier<T>：供给型接口   T get()
   * 产生指定的整数集合放到集合中
   * 综合使用Consumer接口实现遍历list，Iterable接口的forEach方法的定义：
   *     default void forEach(Consumer<? super T> action) {
   *         Objects.requireNonNull(action);
   *         for (T t : this) {
   *             action.accept(t);
   *         }
   *     }
   */
  @Test
  public void test02(){
    List list = addNumInList(10, () -> (int) (Math.random() * 100));
    list.forEach(t-> System.out.println(t));
  }
  public List addNumInList(int size, Supplier<Integer> supplier){
    List<Integer> list=new ArrayList();
    for (int i = 0; i < size; i++) {
      list.add(supplier.get());
    }
    return list;
  }

  /**
   * 3. Function<T,R>:函数型接口  R apply(T t)
   * 使用函数式接口处理字符串。
   */
  @Test
  public void test03(){
    System.out.println(handleStr("abc",(String s)->s.toUpperCase()));
  }
  public String handleStr(String s,Function<String,String> f){
    return f.apply(s);
  }
  /**
   *  4.Predicate<T>:断言型接口  boolean test(T t)
   *  自定义条件过滤字符串集合
   */
  @Test
  public void test04(){
    List<String> strings = Arrays.asList("啊啊啊", "2333", "666", "?????????");
    List<String> stringList = filterStr(strings, (s) -> s.length() > 3);
    for (String s : stringList) {
      System.out.println(s);
    }
  }
  public List<String> filterStr(List<String> list, Predicate<String> predicate){
    ArrayList result = new ArrayList();
    for (int i = 0; i < list.size(); i++) {
      if (predicate.test(list.get(i))){
        result.add(list.get(i));
      }
    }
    return result;
  }
}
