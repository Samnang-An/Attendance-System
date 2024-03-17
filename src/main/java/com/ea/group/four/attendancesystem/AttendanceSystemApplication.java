package com.ea.group.four.attendancesystem;

import com.ea.group.four.attendancesystem.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"edu.miu.common", "com.ea.group.four.attendancesystem"})
public class AttendanceSystemApplication {
  public static void main(String[] args) {
    SpringApplication.run(AttendanceSystemApplication.class, args);
  }


}
