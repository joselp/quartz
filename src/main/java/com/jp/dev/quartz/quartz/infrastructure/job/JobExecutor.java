package com.jp.dev.quartz.quartz.infrastructure.job;

import java.time.LocalDateTime;
import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

public class JobExecutor implements Job {

  @Override
  public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
    JobDataMap jobDataMap = jobExecutionContext.getMergedJobDataMap();

    String jobId = (String) jobDataMap.get("jobID");
    System.out.println("Job Started-"+jobId+" at:"+ LocalDateTime.now());
    try {
      Thread.sleep(2000);
      System.out.println("Job Finished-"+jobId+" at:"+ LocalDateTime.now());
    } catch (InterruptedException e) {
      throw new RuntimeException(e);
    }
  }
}
