package basic.learn.optional;

import basic.learn.lambda01.Employee;
import org.junit.Test;

import java.util.Optional;

/**
 * @author Hai
 * @date 2020/8/10 - 23:53
 */
public class TestOptional01 {
    @Test
    public void test1(){
        Optional<Employee> employee = Optional.of(new Employee());
        System.out.println(employee.get());
        //空的optional实例
//        Optional<Object> empty = Optional.empty();
//        Object o = empty.get();
//        System.out.println(o);
        Optional<Object> o1 = Optional.ofNullable(null);
        System.out.println(o1);
    }
}
