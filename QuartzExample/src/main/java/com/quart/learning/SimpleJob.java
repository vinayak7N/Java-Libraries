package com.quart.learning;

import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

public class SimpleJob implements Job {

	public void execute(JobExecutionContext context) throws JobExecutionException {
		
		JobDataMap jobDataMap = context.getJobDetail().getJobDataMap();
		
		String jobSays = jobDataMap.getString("jobSays");
		float myFloatValue = jobDataMap.getFloat("myFloatValue");
		System.out.println("Job says: "+jobSays+", and val is: "+myFloatValue);
	}

}
