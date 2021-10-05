package chapther2.vmstack;

/**
 * @author Hai
 * @program: learn-everyday
 * @description:
 * @create 2021/9/20 - 23:45
 **/

public class VirtualMethodTest {
    public static void main(String[] args) {

        Son.print("hi");
    }
}
class Father {

    public Father(){
        System.out.println("father的构造器");
    }

    public static void showStatic(String str){
        System.out.println("father static "+str);
    }

    public final void showFinal(){
        System.out.println("father show final");
    }

    public void showCommon(){
        System.out.println("father 普通方法");
    }


    public static void print(String str) {
        System.out.println("father:"+str);
    }

    private void show(String str) {
        System.out.println("father:"+str);
    }
}
class Son extends Father{

    public Son(){
        super();
    }

    public Son(int age){
        this();
    }

    public static void showStatic(String str){
        System.out.println("son static "+str);
    }

    private void showPrivate(String str) {
        System.out.println("son:"+str);
    }


    public void show(String str) {
        //invokestatic
        showStatic("aa");
        //invokestatic
        Father.showStatic("bb");
        //invokespecial
        showPrivate("hi");
        //invokespecial
        super.showCommon();
        //invokevirtual
        showFinal();
        //invokevirtual
        showCommon();
        //invokevirtual
        info();

        MethodInterface in = null;
        //invokeinterface
        in.methodA();
    }

    public void info(){

    }

    public static void main(String[] args) {
        Son son = new Son();
        son.show("321");
    }


}
interface MethodInterface{

    void methodA();
}


