package basic.learn.lambda01;

/**
 * @author Hai
 * @date 2020/7/3 - 22:36
 */
@FunctionalInterface
public interface MyFuncInterf<T> {

   public T getValue(String origin);
}
