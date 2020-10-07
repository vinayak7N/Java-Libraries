package com.quart.learning;

import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;

public class QuartzClass {

	public static void main(String[] args) {

		SchedulerFactory stdSchedulerFactory = new StdSchedulerFactory();

		try {
			Scheduler scheduler = stdSchedulerFactory.getScheduler();
			
			JobDetail job = JobBuilder.newJob(SimpleJob.class)
								.withIdentity("myJob","group1")
								.usingJobData("jobSays","Hello Quartz Scheduler!")
								.usingJobData("myFloatValue",3.14f)
								.build();
			
			JobDetail jobA = JobBuilder.newJob(JobA.class)
								.withIdentity("jobA","group2")
								.build();
			
			JobDetail jobB = JobBuilder.newJob(JobB.class)
								.withIdentity("jobB","group2")
								.build();
			 
			
			Trigger trigger = TriggerBuilder
									.newTrigger()
									.withIdentity("myTrigger","group1")
									.startNow()
									.withSchedule(SimpleScheduleBuilder.simpleSchedule().withIntervalInSeconds(1).repeatForever())
									.build();
			
			Trigger triggerA = TriggerBuilder
									.newTrigger()
									.withIdentity("triggerA","group2")
									.startNow()
									.withPriority(15)
									.withSchedule(SimpleScheduleBuilder.simpleSchedule().withIntervalInSeconds(5).repeatForever())
									.build();
			Trigger triggerB = TriggerBuilder
									.newTrigger()
									.withIdentity("triggerB","group2")
									.startNow()
									.withPriority(10)
									.withSchedule(SimpleScheduleBuilder.simpleSchedule().withIntervalInSeconds(5).repeatForever())
									.build();
							
					
			scheduler.scheduleJob(job,trigger);
			scheduler.scheduleJob(jobA,triggerA);
			scheduler.scheduleJob(jobB, triggerB);
			scheduler.start();
							
		} catch (SchedulerException ex) {
			System.out.println(ex.getMessage());
		}
	}

}
