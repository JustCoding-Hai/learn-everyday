package top.javahai.multithread.cooperation;

/**
 * @author Hai
 * @program: learn-everyday
 * @description: 线程协作Demo
 * @create 2021/2/11 - 12:36
 **/
public class ThreadCooperationDemo {
    public static void main(String[] args) {
        Buffer buffer = new Buffer();
        new Producer(buffer).start();
        new Consumer(buffer).start();

    }
}

/**
 * 生产者
 */
class Producer extends Thread{

    Buffer buffer;

    public Producer(Buffer buffer){
        this.buffer=buffer;
    }

    /**
     * 生产产品
     */
    @Override
    public void run() {
        for (int i = 1; i <=10; i++) {
            try {
                buffer.push(new Product(i));
                System.out.println("生产者-生产了"+i+"号产品");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

/**
 * 消费者
 */
class Consumer extends Thread{
    Buffer buffer;

    public Consumer(Buffer buffer){
        this.buffer=buffer;
    }

    @Override
    public void run() {
        for (int i = 1; i <= 10; i++) {
            try {
                System.out.println("消费者-消费了"+buffer.pop().getNo()+"号产品");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

/**
 * 产品
 */
class Product{
    private int no;

    public Product(int no) {
        this.no = no;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }
}
/**
 * 缓冲区 信号灯
 */
class Buffer{
    /**
     * 缓冲区容器
     */
    private Product[] products=new Product[10];
    /**
     * 容器大小计数器
     */
    private int count;

    /**
     * 生产者放入产品
     * @param product
     */
    public synchronized void push(Product product) throws InterruptedException {
        //如果容器满了，就需要等待消费者消费
        if (count==products.length){
            this.wait();
        }
        //如果容器没满，就将product放入到容器
        products[count]=product;
        count++;
        // 通知消费者消费
        this.notifyAll();
    }

    /**
     * 消费者消费产品
     */
    public synchronized Product pop() throws InterruptedException {
        //如果没有产品，则等待
        if (count==0){
            this.wait();
        }
        //有则消费
        count--;
        Product product=products[count];
        //通知生产者生产
        this.notifyAll();
        return product;
    }


}

