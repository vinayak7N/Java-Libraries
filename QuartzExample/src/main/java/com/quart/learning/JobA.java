package com.quart.learning;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

public class JobA implements Job{

	public void execute(JobExecutionContext context) throws JobExecutionException {
		System.out.println("This is a Quartz JobA...");
	}

}
