package com.jp.dev.quartz.quartz.business.usecase;

import com.jp.dev.quartz.quartz.infrastructure.job.FlightJob;
import com.jp.dev.quartz.quartz.infrastructure.job.OrderJob;
import org.quartz.JobBuilder;
import org.quartz.JobDataMap;
import org.quartz.JobDetail;
import org.springframework.stereotype.Component;

@Component
public class JobCreatorFactory {

  public static final String FLIGHT = "flight";
  public static final String ORDER = "order";

  public JobDetail createJob(String jobType, JobDataMap jobDataMap, String jobId)
      throws Exception {

    return switch (jobType) {
      case FLIGHT ->
          JobBuilder.newJob(FlightJob.class)
              .withIdentity(String.format("%s-%s", jobType, jobId))
              .withDescription(String.format("%s-%s", jobType, jobId))
              .usingJobData(jobDataMap)
              .build();
      case ORDER ->
          JobBuilder.newJob(OrderJob.class)
              .withIdentity(String.format("%s-%s", jobType, jobId))
              .withDescription(String.format("%s-%s", jobType, jobId))
              .usingJobData(jobDataMap)
              .build();
      default -> throw new Exception("No JobType found");
    };
  }
}
