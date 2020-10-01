package basic.learn.stream;

import basic.learn.lambda01.Employee;
import basic.learn.lambda01.State;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/**
 * @author Hai
 * @date 2020/7/4 - 22:36
 * Stream的操作步骤：
 * 1.Stream的创建
 * 2.中间操作
 * 3.终止操作（终端操作）
 */
public class TestStreamAPI05 {
  /**
   * Stream的终止操作之查找与匹配：
   * allMatch——检查是否匹配所有元素
   * anyMatch——检查是否至少有一个匹配元素
   * noneMatch——检查是否没有匹配所有元素
   * findFirst--返回第一个元素
   * findAny--返回当前流中的任意元素
   * count--返回流中元素的总个数
   * max--返回流中的最大值
   * min--返回流中的最小值
   */
  List<Employee> employees=Arrays.asList(new Employee(1,"张三",60),
          new Employee(2,"李四",50, State.BUSY),
          new Employee(3,"王五",45,State.BUSY),
          new Employee(23,"赵六",45,State.FREE),
          new Employee(21,"田七",40,State.VACATION),
          new Employee(24,"天启",35,State.FREE));
  //测试allMatch，anyMatch，noneMatch
  @Test
  public void test01(){
    boolean allMatch = employees.stream().allMatch(e -> e.getAge() >= 45);
    System.out.println(allMatch);
    boolean anyMatch = employees.stream().anyMatch((e -> e.getAge() > 65));
    System.out.println(anyMatch);
    boolean noneMatch = employees.stream().noneMatch((e -> e.getAge() > 65));
    System.out.println(noneMatch);
  }
  //测试findFirst，findAny
  @Test
  public void test02(){
    Optional<Employee> first = employees.stream()
            .sorted((e1, e2) -> e1.getAge().compareTo(e2.getAge()))
            .findFirst();
    System.out.println(first.get());
    Optional<Employee> any = employees.stream().filter(e -> e.getState()==State.FREE).findAny();
    System.out.println(any.get());
  }
  //测试count，max，min
  @Test
  public void test03(){
    //测试count
    long count = employees.stream().count();
    System.out.println(count);
    //测试max
    Optional<Employee> max = employees.stream().max((e1, e2) -> e1.getAge().compareTo(e2.getAge()));
    System.out.println(max.get());
    //测试min
    Optional<Employee> min = employees.stream().min((e1, e2) -> e1.getAge().compareTo(e2.getAge()));
    System.out.println(min.get());
  }

}
