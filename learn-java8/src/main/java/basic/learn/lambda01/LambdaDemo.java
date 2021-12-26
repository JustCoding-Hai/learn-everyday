package basic.learn.lambda01;

/**
 * @author Hai
 * @program: learn-everyday
 * @description:
 * @create 2021/12/21 - 23:11
 **/
public class LambdaDemo {

    /**
     * 成员内部类
     */
    class MyThread01 implements Runnable{
        @Override
        public void run() {
            System.out.println("成员内部类：用Lambda语法创建线程吧!");
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
         * 局部内部类
         */
        class MyThread03 implements Runnable{
            @Override
            public void run() {
                System.out.println("局部内部类：用Lambda语法创建线程吧!");
            }
        }

        int i= 2;
        /**
         * 匿名内部类
         */
        Runnable runnable = new Runnable(){

            @Override
            public void run() {
                System.out.println(i);
                System.out.println("匿名内部类：求求你，用Lambda语法创建线程吧!");
            }
        };
        //成员内部类方式
        LambdaDemo lambdaDemo = new LambdaDemo();
        MyThread01 myThread01 =lambdaDemo.new MyThread01();
        new Thread(myThread01).start();
        //静态内部类方式
        MyThread02 myThread02 = new MyThread02();
        new Thread(myThread02).start();
        //局部内部类
        MyThread03 myThread03 = new MyThread03();
        new Thread(myThread03).start();
        //匿名内部类的方式
        new Thread(runnable).start();
    }
}

