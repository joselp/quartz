package com.jp.dev.quartz.quartz.business.ports.out;

import org.quartz.JobDetail;
import org.quartz.Trigger;

public interface ScheduleJob {

  void schedule(JobDetail job, Trigger trigger);
}
