package com.jp.dev.quartz.quartz.business.usecase;

import static com.jp.dev.quartz.quartz.business.usecase.JobCreatorFactory.*;

import com.jp.dev.quartz.quartz.business.ports.in.ProcessFlight;
import com.jp.dev.quartz.quartz.business.ports.out.ScheduleJob;
import lombok.AllArgsConstructor;
import org.quartz.JobDataMap;
import org.quartz.JobDetail;
import org.quartz.Trigger;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class ProcessFlightUseCase implements ProcessFlight {

  private final ScheduleJob scheduleJob;
  private final JobCreatorFactory jobCreatorFactory;
  private final TriggerCreator triggerCreator;

  @Override
  public void process() {

    for (int i = 0; i < 10; i++) {

      String jobId = String.valueOf(i);
      JobDataMap jobDataMap = new JobDataMap();
      jobDataMap.put("jobID", jobId);

      try {
        JobDetail job = jobCreatorFactory.createJob(FLIGHT, jobDataMap, jobId);
        Trigger trigger = triggerCreator.createTrigger(jobId);
        scheduleJob.schedule(job, trigger);

      } catch (Exception ex) {
        System.out.println("Error scheduling flight job");
      }
    }
  }
}
