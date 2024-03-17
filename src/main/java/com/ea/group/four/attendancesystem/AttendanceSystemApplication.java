package com.ea.group.four.attendancesystem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"com.ea.group.four.attendancesystem", "edu.miu.common"})
public class AttendanceSystemApplication {

  public static void main(String[] args) {
    SpringApplication.run(AttendanceSystemApplication.class, args);
  }

}
