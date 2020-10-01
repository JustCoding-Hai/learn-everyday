package basic.learn.stream;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Stream;

/**
 * @author Hai
 * @date 2020/7/4 - 22:36
 * Stream的操作步骤：
 * 1.Stream的创建
 * 2.中间操作
 * 3.终止操作（终端操作）
 */
public class TestStreamAPI01 {
  /**
   * Stream的创建方式
   */
  @Test
  public void test01(){
    //1.通过Collection系列集合提供的stream()或parallelStream()获取
    ArrayList<String> list = new ArrayList<>();
    Stream<String> stream1 = list.stream();
    //2.通过Arrays中的静态方法stream()获取
    String[] strings=new String[10];
    Stream<String> stream2 = Arrays.stream(strings);
    //3.通过Stream类中的静态方法of()获取
    Stream<String> stream3 = Stream.of(strings);
    //4.创建无限流
    //4.1迭代
    Stream<Integer> stream4 = Stream.iterate(0, x-> x + 2);
    //4.2生成
    Stream<Double> stream5 = Stream.generate(() -> Math.random());
  }
}
