package top.javahai.multithread.synchronic;

import com.sun.corba.se.impl.ior.WireObjectKeyTemplate;

/**
 * @author Hai
 * @program: learn-everyday
 * @description: 线程同步-银行取钱问题
 * @create 2021/2/10 - 22:41
 **/
public class Bank {
    public static void main(String[] args) {
        Account account = new Account(100, "买车存款");
        new Withdrawal(account,100,"丈夫").start();
        new Withdrawal(account,50,"妻子").start();

    }

}

/**
 * 个人账户
 */
class Account{
    /**
     * 余额
     */
    int money;
    /**
     * 卡名
     */
    String name;

    public Account(int money, String name) {
        this.money = money;
        this.name = name;
    }
}

/**
 *模拟取款的过程
 */
class Withdrawal extends Thread{
    /**
     * 账户
     */
    private Account account;
    /**
     * 要取多少钱
     */
    int drawMoney;
    /**
     * 取款人
     */
    String name;

    public Withdrawal(Account account,int drawMoney,String name){
        super(name);
        this.name=name;
        this.account=account;
        this.drawMoney=drawMoney;
    }

    @Override
    public void run() {
        synchronized (account){
            //判断还有没钱
            if (account.money-drawMoney<0){
                System.out.println(name+"取不了钱，账户余额不足");
                return;
            }
            //模拟方法延时
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            //卡里余额：原本的钱-要取的钱
            account.money=account.money-drawMoney;
            System.out.println(name+"取走了"+drawMoney);
            System.out.println(account.name+"当前余额："+account.money);
        }
    }
}
