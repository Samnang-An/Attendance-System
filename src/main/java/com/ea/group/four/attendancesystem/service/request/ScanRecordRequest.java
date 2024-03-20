package com.ea.group.four.attendancesystem.service.request;

import lombok.Data;
import java.time.LocalDate;
import java.time.LocalTime;

@Data
public class ScanRecordRequest {

    private Long memberId;
    private LocalDate scannedDate;
    private LocalTime scannedTime;
}
