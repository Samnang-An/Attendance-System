package com.ea.group.four.attendancesystem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

@SpringBootApplication
@ComponentScan({"com.ea.group.four.attendancesystem","edu.miu.common"})
public class AttendanceSystemApplication {

  public static void main(String[] args) {
    SpringApplication.run(AttendanceSystemApplication.class, args);
  }

}
