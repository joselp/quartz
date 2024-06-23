package com.jp.dev.quartz.quartz.infrastructure.scheduler;

import com.jp.dev.quartz.quartz.business.ports.out.ScheduleJob;
import lombok.AllArgsConstructor;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.Trigger;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public class JobScheduler implements ScheduleJob {

  private final Scheduler scheduler;

  public void schedule(JobDetail job, Trigger trigger) {

    try {
      scheduler.scheduleJob(job, trigger);
    } catch (Exception ex) {
      ex.printStackTrace();
      System.out.println("Error scheduling job");
    }
  }
}
