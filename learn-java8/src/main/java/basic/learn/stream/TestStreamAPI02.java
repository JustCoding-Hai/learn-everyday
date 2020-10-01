package basic.learn.stream;

import basic.learn.lambda01.Employee;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
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
public class TestStreamAPI02 {
  /**
   * Stream的中间操作之筛选和切片：
   * filter——接收Lambda，从流中排除某些元素
   * limit——截断流，使其元素不超过给定数量
   * skip--跳过元素，返回一个扔掉了前n个元素的流，若流中的元素不足n个，则返回空流。与limit互补
   * distinct--筛选，去取流中的hashcode()和equals相同的元素
   */
  List<Employee> employees=Arrays.asList(new Employee(1,"张三",60),
          new Employee(2,"李四",50),
          new Employee(3,"王五",45),
          new Employee(26,"赵六",45),
          new Employee(26,"赵六",45),
          new Employee(26,"赵六",45));
  //filter——接收Lambda，从流中排除某些元素
  @Test
  public void test01(){
    //中间操作：不会执行任何操作
    Stream<Employee> stream = employees.stream().filter((employee -> {
      return employee.getAge() > 45;
    }));
    //终止操作：一次性执行全部内容，即“惰性求值”
    stream.forEach(System.out::println);
  }
  //limit——截断流，使其元素不超过给定数量
  @Test
  public void test02(){
    employees.stream()
            .filter(e->e.getAge()>18)
            .limit(3)
            .forEach(System.out::println);
  }
  //skip--跳过元素，返回一个扔掉了前n个元素的流，若流中的元素不足n个，则返回空流。与limit互补
  @Test
  public void test03(){
    employees.stream()
            .skip(2)
            .forEach(System.out::println);
  }
  // distinct--筛选，去取流中的hashcode()和equals相同的元素
  @Test
  public void test04(){
    employees.stream()
            .distinct()
            .forEach(System.out::println);
  }

}
