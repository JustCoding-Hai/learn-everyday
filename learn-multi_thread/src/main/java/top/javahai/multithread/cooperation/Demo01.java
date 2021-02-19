package top.javahai.multithread.cooperation;

/**
 * @author Hai
 * @program: learn-everyday
 * @description: 生产者消费者问题
 * @create 2021/2/17 - 15:32
 **/
public class Demo01 {

    public static void main(String[] args) {
        Data data = new Data();

        new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                try {
                    data.increment();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "A").start();

        new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                try {
                    data.increment();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "B").start();

        new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                try {
                    data.decrement();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "C").start();
        new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                try {
                    data.decrement();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "D").start();
    }

}

    /**
     * 线程操作的资源类，判断等待业务、通知
     */
    class Data {
        private int number = 0;

        public synchronized void increment() throws InterruptedException {
            if (number != 0) {
                this.wait();
            }
            number++;
            System.out.println(Thread.currentThread().getName() + "=>" + number);
            //通知其他线程，我+1完毕了
            this.notifyAll();
        }

        public synchronized void decrement() throws InterruptedException {
            if (number == 0) {
                this.wait();
            }
            number--;
            System.out.println(Thread.currentThread().getName() + "=>" + number);
            //通知其他线程，我-1完毕了
            this.notifyAll();

        }
    }
