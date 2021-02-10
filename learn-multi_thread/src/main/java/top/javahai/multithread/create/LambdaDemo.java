package top.javahai.multithread.create;

/**
 * @author Hai
 * @program: learn-everyday
 * @description: Lambda接口的初步使用
 * @create 2021/2/10 - 16:32
 **/
public class LambdaDemo {


    /**
     * 局部内部类
     */
    class MyThread01 implements Runnable{
        @Override
        public void run() {
            System.out.println("局部内部类：用Lambda语法创建线程吧!");
        }
    }


    /**
     * 静态内部类
     */
    static class MyThread02 implements Runnable{
        @Override
        public void run() {
            System.out.println("静态内部类：对啊，用Lambda语法创建线程吧!");
        }
    }

    public static void main(String[] args) {
        /**
         * 匿名内部类
         */
        Runnable runnable = new Runnable(){

            @Override
            public void run() {
                System.out.println("匿名内部类：求求你，用Lambda语法创建线程吧!");
            }
        };
        //局部内部类方式
        LambdaDemo lambdaDemo = new LambdaDemo();
        MyThread01 myThread01 =lambdaDemo.new MyThread01();
        new Thread(myThread01).start();
        //静态内部类方式
        MyThread02 myThread02 = new MyThread02();
        new Thread(myThread02).start();
        //匿名内部类的方式
        new Thread(runnable).start();
        //Lambda方式
        new Thread(() -> System.out.println("使用Lambda就对了")).start();
    }
}
