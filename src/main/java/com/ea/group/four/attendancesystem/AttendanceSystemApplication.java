package com.ea.group.four.attendancesystem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;


@SpringBootApplication(scanBasePackages = {"edu.miu.common", "com.ea.group.four.attendancesystem"})
public class AttendanceSystemApplication {

  public static void main(String[] args) {
    SpringApplication.run(AttendanceSystemApplication.class, args);
  }

}
