package com.jp.dev.quartz.quartz.infrastructure.job;


import com.jp.dev.quartz.quartz.business.usecase.ProcessOrdersUseCase;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

@AllArgsConstructor
public class FlightJob implements Job {

  private final ProcessOrdersUseCase processOrdersUseCase;

  @Override
  public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
    JobDataMap jobDataMap = jobExecutionContext.getMergedJobDataMap();

    String jobId = (String) jobDataMap.get("jobID");
    System.out.println("Job Started-" + jobId + " at:" + LocalDateTime.now());
    try {
      Thread.sleep(10000);
      System.out.println(
          "Job Finished-"
              + jobExecutionContext.getJobDetail().getJobBuilder()
              + " at:"
              + LocalDateTime.now());

      System.out.println("Scheduling orders to flight:" + jobId + " at:" + LocalDateTime.now());
      processOrdersUseCase.process(jobId);
      System.out.println("Orders scheduled to flight:" + jobId + " at:" + LocalDateTime.now());
    } catch (InterruptedException e) {
      throw new RuntimeException(e);
    }
  }
}
