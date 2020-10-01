package basic.learn.stream;

import basic.learn.lambda01.Employee;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Stream;

/**
 * @author Hai
 * @date 2020/7/4 - 22:36
 * Stream的操作步骤：
 * 1.Stream的创建
 * 2.中间操作
 * 3.终止操作（终端操作）
 */
public class TestStreamAPI04 {
  /**
   * Stream的中间操作之排序：
   * sorted()--自然排序(Comparable)
   * sorted(Comparator com)--定制排序(Comparator)
   */
  List<Employee> employees=Arrays.asList(new Employee(1,"张三",60),
          new Employee(2,"李四",50),
          new Employee(3,"王五",45),
          new Employee(23,"赵六",45),
          new Employee(21,"田七",45),
          new Employee(24,"天启",45));
  @Test
  public void test01(){
    List<String> list = Arrays.asList("ddd", "ccc", "bbb", "aaa");
    //默认使用泛型的实现Comparable接口中的compareTo方法进行比较
    list.stream().sorted().forEach(System.out::println);
  }
  @Test
  public void test02(){
    employees.stream().sorted((e1,e2)->{
      if (e1.getAge().equals(e2.getAge())){
        return e1.getId().compareTo(e2.getId());
      }else{
        return e1.getAge().compareTo(e2.getAge());
      }
    }).forEach(System.out::println);
  }


}
