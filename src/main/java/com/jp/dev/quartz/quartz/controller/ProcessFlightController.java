package com.jp.dev.quartz.quartz.controller;

import com.jp.dev.quartz.quartz.business.ports.in.ProcessFlight;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class ProcessFlightController {

  private final ProcessFlight processFlight;

  @PostMapping("/process/flights")
  public ResponseEntity<Void> processFlight() {

    processFlight.process();

    return ResponseEntity.status(HttpStatus.CREATED).build();
  }
}
