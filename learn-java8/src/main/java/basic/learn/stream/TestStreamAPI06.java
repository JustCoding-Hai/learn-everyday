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
public class TestStreamAPI06 {
  /**
   * Stream的终止操作之规约：
   * 规约：reduce(T identity,BinaryOperator)/reduce(BinaryOperator),可以将流中元素结合起来，得到新的值
   */
  List<Employee> employees=Arrays.asList(new Employee(1,"张三",60),
          new Employee(2,"李四",50, State.BUSY),
          new Employee(3,"王五",45,State.BUSY),
          new Employee(23,"赵六",45,State.FREE),
          new Employee(21,"田七",40,State.VACATION),
          new Employee(24,"天启",35,State.FREE));
  @Test
  public void test01(){
    //计算整数数组累加
    List<Integer> integers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
    Integer result = integers.stream().reduce(5, (x, y) -> x + y);
    System.out.println(result);
    //计算平均年龄
    Optional<Integer> ageTotal = employees.stream().map(Employee::getAge).reduce(Integer::sum);
    long empNum=employees.stream().count();
    System.out.println(ageTotal.get()/empNum);
  }

}
