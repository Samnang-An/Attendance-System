package com.ea.group.four.attendancesystem.service.response;

import jakarta.persistence.Column;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;

@Data
public class ScanRecordResponse implements Serializable {

    private static final long serialVersionUID = 1L;
    private long recordId;
    private ScannerResponse scanner;
    private MemberResponse member;
    private LocalDate scannedDate;
    private LocalTime scannedTime;
    private String status;
    private boolean isDeprecated;
}

