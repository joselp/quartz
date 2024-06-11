package com.jp.dev.quartz.quartz.infrastructure.scheduler;

import com.jp.dev.quartz.quartz.infrastructure.job.JobExecutor;
import lombok.AllArgsConstructor;
import org.quartz.JobBuilder;
import org.quartz.JobDataMap;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public class JobScheduler {

  private final Scheduler scheduler;

  public void scheduleJob(String jobType, int jobId) {

    try{
      JobDataMap jobDataMap = new JobDataMap();
      jobDataMap.put("jobID", String.valueOf(jobId));

      JobDetail job = createJobDetail(jobDataMap, String.valueOf(jobId));

      Trigger trigger = createTrigger(String.valueOf(jobId));

      scheduler.scheduleJob(job, trigger);
    } catch (Exception ex) {
      ex.printStackTrace();
      System.out.println("Error scheduling job");
    }

  }

  private Trigger createTrigger(String jobId) {
    return TriggerBuilder.newTrigger()
        .withIdentity("triggerIdentity"+jobId)
        .startNow()
        .withSchedule(SimpleScheduleBuilder.simpleSchedule()
            .withIntervalInSeconds(10)
            .withRepeatCount(0))
        .build();
  }

  private JobDetail createJobDetail(JobDataMap jobDataMap, String jobId) {
    return JobBuilder.newJob(JobExecutor.class)
        .withIdentity("jobIdentity"+jobId)
        .usingJobData(jobDataMap)
        .build();
  }
}
