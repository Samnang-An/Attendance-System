package com.ea.group.four.attendancesystem.service.response.response;

import com.ea.group.four.attendancesystem.domain.Member;
import com.ea.group.four.attendancesystem.domain.Scanner;
import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.Data;

@Data
public class ScanRecordResponse implements Serializable {

  private static final long serialVersionUID = 1L;
  private long recordId;
  private Scanner scanner;
  private Member member;
  private LocalDateTime scannedDateTime;
  private String status;
}
