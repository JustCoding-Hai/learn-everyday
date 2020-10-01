package basic.learn.stream;

import basic.learn.lambda01.Employee;
import basic.learn.lambda01.State;
import org.junit.Test;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author Hai
 * @date 2020/7/4 - 22:36
 * Stream的操作步骤：
 * 1.Stream的创建
 * 2.中间操作
 * 3.终止操作（终端操作）
 */
public class TestStreamAPI07 {
  /**
   * Stream的终止操作之收集：
   * collect--将流转化为其他形式，接收一个Collector接口的实现，用于给Stream中元素做汇总的方法。
   *
   */
  List<Employee> employees=Arrays.asList(new Employee(1,"张三",60,State.VACATION),
          new Employee(2,"李四",50, State.BUSY),
          new Employee(3,"王五",45,State.BUSY),
          new Employee(23,"赵六",45,State.FREE),
          new Employee(23,"赵六",45,State.FREE),
          new Employee(21,"田七",40,State.VACATION),
          new Employee(24,"天启",35,State.FREE));
  @Test
  public void test01(){
    List<String> list = employees.stream().map(Employee::getName).collect(Collectors.toList());
    list.forEach(System.out::println);
    System.out.println("-----------------");
    Set<String> set = employees.stream().map(Employee::getName).collect(Collectors.toSet());
    set.forEach(System.out::println);
    System.out.println("-----------------");
    HashSet<String> hashSet = employees.stream().map(Employee::getName).collect(Collectors.toCollection(HashSet::new));
    hashSet.forEach(System.out::println);
  }
  @Test
  public void test02(){
    //总数
    Long sum= employees.stream().collect(Collectors.counting());
    System.out.println(sum);
    //平均值
    Double averageAge = employees.stream().collect(Collectors.averagingDouble(Employee::getAge));
    System.out.println(averageAge);
    //某属性的综合
    Integer ageSum = employees.stream().collect(Collectors.summingInt(Employee::getAge));
    System.out.println(ageSum);
    //最大值
    Optional<Employee> maxAgeEmployee = employees.stream().collect(Collectors.maxBy((e1, e2) -> e1.getAge().compareTo(e2.getAge())));
    System.out.println(maxAgeEmployee.get());
    //最小值
    Optional<Employee> minAgeEmployee = employees.stream().collect(Collectors.minBy((e1, e2) -> e1.getAge().compareTo(e2.getAge())));
    System.out.println(minAgeEmployee.get());
    //所有数据
    IntSummaryStatistics totalData = employees.stream().collect(Collectors.summarizingInt(Employee::getAge));
    System.out.println(totalData);
  }
  //分组
  @Test
  public void test03(){
    Map<State, List<Employee>> stateListMap = employees.stream().collect(Collectors.groupingBy(Employee::getState));
    System.out.println(stateListMap);
  }
  //多级分组
  @Test
  public void test04(){
    Map<State, Map<String, List<Employee>>> collect = employees.stream()
            .collect(Collectors.groupingBy(Employee::getState, Collectors.groupingBy((e) -> {
              if (((Employee) e).getAge() <= 30) {
                return "青年";
              } else if (((Employee) e).getAge() <= 50) {
                return "中年";
              } else {
                return "老年";
              }
            })));
    System.out.println(collect);
  }
  //分区
  @Test
  public void test08(){
    Map<Boolean, List<Employee>> collect = employees.stream().collect(Collectors.partitioningBy(employee -> employee.getAge() > 40));
    System.out.println(collect);

  }

}
