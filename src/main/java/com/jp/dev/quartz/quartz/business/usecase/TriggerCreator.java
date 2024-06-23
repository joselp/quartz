package com.jp.dev.quartz.quartz.business.usecase;

import org.quartz.SimpleScheduleBuilder;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.springframework.stereotype.Component;

@Component
public class TriggerCreator {

  public Trigger createTrigger(String jobId) {
    return TriggerBuilder.newTrigger()
        .withIdentity("triggerIdentity" + jobId)
        .startNow()
        .withSchedule(
            SimpleScheduleBuilder.simpleSchedule().withIntervalInSeconds(10).withRepeatCount(0))
        .build();
  }
}
