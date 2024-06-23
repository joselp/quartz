package com.jp.dev.quartz.quartz.business.usecase;

import static com.jp.dev.quartz.quartz.business.usecase.JobCreatorFactory.ORDER;

import com.jp.dev.quartz.quartz.business.ports.out.ScheduleJob;
import lombok.AllArgsConstructor;
import org.quartz.JobDataMap;
import org.quartz.JobDetail;
import org.quartz.Trigger;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class ProcessOrdersUseCase {

  private final ScheduleJob scheduleJob;
  private final JobCreatorFactory jobCreatorFactory;
  private final TriggerCreator triggerCreator;

  public void process(String flightId) {

    for (int i = 0; i < 1000; i++) {

      JobDataMap jobDataMap = new JobDataMap();
      String jobId = String.format("%s-%s", flightId, i);
      jobDataMap.put("jobID", jobId);

      try {
        JobDetail job = jobCreatorFactory.createJob(ORDER, jobDataMap, jobId);
        Trigger trigger = triggerCreator.createTrigger(jobId);
        scheduleJob.schedule(job, trigger);

      } catch (Exception ex) {
        System.out.println("Error scheduling order jobs");
      }
    }
  }
}
