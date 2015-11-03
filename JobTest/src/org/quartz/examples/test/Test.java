package org.quartz.examples.test;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 * User: duanxx
 * Date: 13-9-17
 * Time: ÉÏÎç10:31
 * To change this template use File | Settings | File Templates.
 */
public class Test {
    public void run() throws SchedulerException {

        SchedulerFactory sf = new StdSchedulerFactory();
        Scheduler sched = sf.getScheduler();

        JobDetail job = JobBuilder.newJob(SimpleJob.class).withIdentity("job1", "group1").build();

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat();
        Date date = DateBuilder.dateOf(11, 56, 0, 17, 9, 2013);
        Date endTime = DateBuilder.dateOf(16, 0, 0, 17, 9, 2013);

        CronTrigger trigger = (CronTrigger) TriggerBuilder.newTrigger().withIdentity("trigger1", "group1").withSchedule(CronScheduleBuilder.cronSchedule("50/20 * * * * ?")).startAt(date).endAt(endTime).build();

        sched.scheduleJob(job, trigger);
        System.out.println(job.getKey());
        System.out.println(trigger.getKey());




        sched.start();
    }

    public static void main(String[] args) throws SchedulerException {
        Test test = new Test();
        test.run();
    }
}

