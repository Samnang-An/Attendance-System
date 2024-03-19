package com.ea.group.four.attendancesystem.service.response;

import com.ea.group.four.attendancesystem.domain.Member;
import com.ea.group.four.attendancesystem.domain.ScanRecord;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Data
@Builder
public class ScanRecordDTO implements Serializable {

  private static final long serialVersionUID = 1L;
  private long recordId;
  private String name;
  private String barcode;
  private String email;
  private LocalDate scannedDate;
  private LocalTime scannedTime;
  private String status;


  public static ScanRecordDTO of (ScanRecord scanRecord){
    return ScanRecordDTO.builder()
            .recordId(scanRecord.getRecordId())
            .barcode(scanRecord.getMember().getBarcode())
            .name(scanRecord.getMember().getFirstName() +" "+ scanRecord.getMember().getLastName())
//            .lastName(scanRecord.getMember().getLastName())
            .email(scanRecord.getMember().getEmail())
            .scannedTime(scanRecord.getScannedTime())
            .scannedDate(scanRecord.getScannedDate())
            .status(scanRecord.getStatus())
            .build();
  }
}
