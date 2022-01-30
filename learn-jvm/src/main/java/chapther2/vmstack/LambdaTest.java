package chapther2.vmstack;

/**
 * @author Hai
 * @program: learn-everyday
 * @description:
 * @create 2021/9/21 - 13:08
 **/
public class LambdaTest {
    public void lambda(Func func){
        return;
    }

    public static void main(String[] args) {
        LambdaTest lambdaTest = new LambdaTest();
        Func func=s->true;
        lambdaTest.lambda(func);
        lambdaTest.lambda(s->true);
    }
}


@FunctionalInterface
interface Func{
    public boolean func(String str);
}
