package com.ea.group.four.attendancesystem;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication(scanBasePackages = {"edu.miu.common", "com.ea.group.four.attendancesystem"})
@EnableScheduling
@EnableJms
public class AttendanceSystemApplication {
  public static void main(String[] args) {
    SpringApplication.run(AttendanceSystemApplication.class, args);
  }


}
