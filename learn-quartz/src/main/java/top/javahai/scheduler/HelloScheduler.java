package top.javahai.scheduler;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import top.javahai.job.HelloJob;

/**
 * @author Hai
 * @date 2020/8/17 - 23:55
 */
public class HelloScheduler {
    /**
     * 特别注意：
     * 1.如果JobDetail和Trigger中的JobDataMap存在相同key的属性，Trigger中的key会
     * 覆盖JobDetail中的。比如：
     * Trigger中的usingJobData("name","edison")会覆盖JobBuilder中的usingJobData("name","ethan")
     * @param args
     * @throws SchedulerException
     */
    public static void main(String[] args) throws SchedulerException {
        //定义任务调度器Scheduler,从StdSchedulerFactory工厂中获取调度实例
        Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
        //定义任务实例JobBuilder
        JobDetail jobDetail = JobBuilder.newJob(HelloJob.class)
                .withIdentity("job1", "jobGroup1")
                .usingJobData("name","ethan")
                .usingJobData("count",1)
                .build();
        //获取任务的名称
        System.out.println(jobDetail.getKey().getName());
        //获取任务所在的组的名称,默认位DEFAULT
        System.out.println(jobDetail.getKey().getGroup());
        //获取所执行任务类的名称
        System.out.println(jobDetail.getJobClass().getName());
        //定义触发器，马上执行，每3秒执行一次
        SimpleTrigger trigger = TriggerBuilder.newTrigger()
                .withIdentity("trigger1", "triggerGroup1")
                .startNow()
                .withSchedule(SimpleScheduleBuilder.simpleSchedule().repeatSecondlyForever(3))
                .usingJobData("id",24)
                //.usingJobData("count",5)
                .build();
        //Scheduler关联任务和触发器，保证调度器按触发器定义的条件执行
        scheduler.scheduleJob(jobDetail,trigger);
        //启动
        scheduler.start();


    }
}
