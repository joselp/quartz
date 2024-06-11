package com.jp.dev.quartz.quartz;

import com.jp.dev.quartz.quartz.infrastructure.scheduler.JobScheduler;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class QuartzApplication {

	@Autowired
	private JobScheduler jobScheduler;

	public static void main(String[] args) {
		SpringApplication.run(QuartzApplication.class, args);
	}

	@PostConstruct
	private void startQuartz() {

		for (int i=0; i<10; i++) {
			jobScheduler.scheduleJob("flight", i);
		}
	}
}
