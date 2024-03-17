package com.ea.group.four.attendancesystem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"edu.miu.common", "com.ea.group.four.attendancesystem"})
public class AttendanceSystemApplication {

  public static void main(String[] args) {
    SpringApplication.run(AttendanceSystemApplication.class, args);
  }

}
