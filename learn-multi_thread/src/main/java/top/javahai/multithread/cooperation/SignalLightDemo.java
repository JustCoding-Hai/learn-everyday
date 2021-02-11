package top.javahai.multithread.cooperation;

import jdk.nashorn.internal.ir.VarNode;

/**
 * @author Hai
 * @program: learn-everyday
 * @description: 线程协作-信号灯法
 * @create 2021/2/11 - 16:34
 **/
public class SignalLightDemo {
    public static void main(String[] args) {
        ProductBuffer buffer = new ProductBuffer();
        new Producer2(buffer).start();
        new Consumer2(buffer).start();
    }

}


/**
 * 生产者
 */
class Producer2 extends Thread{

    ProductBuffer buffer;

    public Producer2(ProductBuffer buffer){
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
class Consumer2 extends Thread{
    ProductBuffer buffer;

    public Consumer2(ProductBuffer buffer){
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
 * 缓冲区
 */
class ProductBuffer{
    /**
     * 缓冲区容器
     */
    private Product[] products=new Product[10];
    /**
     * 容器大小计数器
     */
    private int count;
    /**
     * 信号灯
     */
    private boolean flag=true;


    /**
     * 生产者放入产品
     * @param product
     */
    public synchronized void push(Product product) throws InterruptedException {
        //如果容器满了，就需要等待消费者消费
        if (!flag||count==products.length){
            this.wait();
        }
        //如果容器没满，就将product放入到容器
        products[count]=product;
        count++;
        //flag取false，等消费者消费了设置该信号为true，生产者继续生产
        this.flag=!this.flag;
        // 通知消费者消费
        this.notifyAll();
    }

    /**
     * 消费者消费产品
     */
    public synchronized Product pop() throws InterruptedException {
        //如果没有产品，则等待
        if (flag||count==0){
            this.wait();
        }
        //有则消费
        count--;
        Product product=products[count];
        //信号flag改为true
        this.flag=!this.flag;
        //通知生产者生产
        this.notifyAll();
        return product;
    }

}
