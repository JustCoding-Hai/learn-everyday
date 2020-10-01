package basic.learn.lambda01;

import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @author Hai
 * @date 2020/6/13 - 14:54
 *
 * 练习Lambda表达式
 */
public class TestLambda02 {
  List<Employee> employees= Arrays.asList(new Employee(1,"张三",60),
          new Employee(2,"李四",50),
          new Employee(3,"王五",45),
          new Employee(26,"赵六",45)
          );
  /**
   *     public static <T> void sort(List<T> list, Comparator<? super T> c) {
   *         Object[] a = list.toArray();
   *         Arrays.sort(a, (Comparator)c);
   *         ListIterator<T> i = list.listIterator();
   *         for (int j=0; j<a.length; j++) {
   *             i.next();
   *             i.set((T)a[j]);
   *         }
   *     }
   * 使用Comparator接口的compare方法配合sort方法对集合进行排序
   * 相当于实现Comparator接口中的compare方法。
   */
  @Test
  public void test1(){
    Collections.sort(employees,(e1,e2)->{
      if (e1.getAge()==e2.getAge()){
        return e1.getId().compareTo(e2.getId());
      }
      else
        return e1.getAge().compareTo(e2.getAge());
    });
    System.out.println(employees);
  }
}
