package top.javahai.job;

import org.quartz.*;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author Hai
 * @date 2020/8/17 - 23:52
 */
@PersistJobDataAfterExecution
public class HelloJob implements Job {

    private String name;

    /**
     * Quartz框架的默认的JobFactory实现类在初始化job实例对象，
     * 会自动调用这些setter方法设置与JobDataMap的键值同名的属性
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }


    /**
     * 有状态Job和无状态Job
     * 默认为无状态Job，每一次执行都会新创建一个JobDataMap。
     * 有状态Job，可以理解为会保存一些状态信息到JobDataMap，使用注解@PersistJobDataAfterExecution
     */
    private int count;

    public void setCount(int count) {
        this.count = count;
    }

    /**
     *
     * @param context
     * @throws JobExecutionException
     */
    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        //获取JobKey
        JobKey jobKey = context.getJobDetail().getKey();
        System.out.println("任务名"+jobKey.getName());
        System.out.println("任务组名"+jobKey.getGroup());
        //获取trigger
        TriggerKey triggerKey = context.getTrigger().getKey();
        System.out.println("触发器名："+ triggerKey.getName());
        System.out.println("触发器组名："+triggerKey.getGroup());
        System.out.println("获取任务的类名："+context.getJobDetail().getJobClass().getSimpleName());
        //从JobDetail对象中获取JavaDataMap
        System.out.println("name的值为："+context.getJobDetail().getJobDataMap().get("name"));
        //从Trigger对象中获取JavaDataMap
        System.out.println("id的值为："+context.getTrigger().getJobDataMap().get("id"));
        //获取其他
        System.out.println("正在执行的时间为："+dateFormat.format(context.getFireTime()));
        System.out.println("下一次执行的时间："+dateFormat.format(context.getNextFireTime()));
        System.out.println("print per 3 seconds,current time is :"+ dateFormat.format(new Date()));
        System.out.println("与键值同名的属性："+name);
        ++count;
        System.out.println("保存的状态："+count);
        //将count放入到JobDetail的JobDataMap中
        //ps：仅在JobDetail的JobDataMap中，且Trigger的JobDataMap没有定义相同的属性，才有效，
        context.getJobDetail().getJobDataMap().put("count",count);
        //将count放入Trigger的JobDataMap中
        //context.getTrigger().getJobDataMap().put("count",count);
    }
}
