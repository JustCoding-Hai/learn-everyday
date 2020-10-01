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
public class TestStreamAPI03 {
  /**
   * Stream的中间操作之映射：
   * 1.map——接收Lambda，将元素转化成其他形式或提取信息。接收一个函数作为参数，
   * 该函数会被应用到每个元素上，并将其映射成一个新的元素。
   * 2.flatMap--接收一个函数作为参数，将流中的每个值都换成另一个流，然后把所有流连接成一个流。
   */
  List<Employee> employees=Arrays.asList(new Employee(1,"张三",60),
          new Employee(2,"李四",50),
          new Employee(3,"王五",45),
          new Employee(26,"赵六",45),
          new Employee(26,"赵六",45),
          new Employee(26,"赵六",45));

  /**
   * 测试map：
   * map——接收Lambda，将元素转化成其他形式或提取信息。
   * 接收一个函数作为参数，该函数会被应用到每个元素上，并将其映射成一个新的元素。
   */

  @Test
  public void test01(){
    List<String> list = Arrays.asList("a", "bb", "ccc", "dddd");
    //将list中元素转化成其他形式
    list.stream()
            .map(s->s.toUpperCase())
            .forEach(System.out::print);
    //原集合不受到影响
    list.forEach(System.out::print);
    //提取list中元素的信息
    employees.stream()
            .map(employee -> employee.getName())
            .forEach(System.out::print);
  }
  /**
   * 测试flatMap：
   * flatMap--接收一个函数作为参数，将流中的每个值都换成另一个流，然后把所有流连接成一个流
   *
   */
  //提取字符串中的每个字符到list中，并放入stream流
  public static Stream<Character> filterCharacter(String str){
    ArrayList<Character> list = new ArrayList<>();
    for (Character ch:str.toCharArray()) {
      list.add(ch);
    }
    return list.stream();
  }
  //测试flatMap
  @Test
  public void test02(){
    List<String> list = Arrays.asList("a", "bb", "ccc", "dddd");
    Stream<Stream<Character>> streams = list.stream()
            .map(TestStreamAPI03::filterCharacter);//得到的流的形式类似{{a},{b,b},{c,c,c},{d,d,d,d}}
    //不使用flatMap函数
    System.out.println("不使用flatMap函数");
    streams.forEach(stream->stream.forEach(System.out::print));
    //使用flatMap函数
    System.out.println("\n使用flatMap函数");
    Stream<Character> streams2 = list.stream()
            .flatMap(TestStreamAPI03::filterCharacter);//得到流的形式类似{a,b,b,c,c,c,d,d,d,d}
    streams2.forEach(System.out::print);
  }
}
