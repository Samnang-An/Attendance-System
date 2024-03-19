package com.ea.group.four.attendancesystem.service.response;

import com.ea.group.four.attendancesystem.domain.Member;
import com.ea.group.four.attendancesystem.domain.Scanner;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;

import lombok.Data;

@Data
public class ScanRecordResponse implements Serializable {

  private static final long serialVersionUID = 1L;
  private long recordId;
  private ScannerResponse scanner;
  private MemberResponse member;
//  private LocalDate scannedDate;
//  private LocalTime scannedTime;
//  private String status;
//  private boolean deprecated;
}