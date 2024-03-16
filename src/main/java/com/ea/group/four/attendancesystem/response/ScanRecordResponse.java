package com.ea.group.four.attendancesystem.response;

import com.ea.group.four.attendancesystem.domain.Member;
import com.ea.group.four.attendancesystem.domain.Scanner;
import jakarta.persistence.ManyToOne;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ScanRecordResponse {
    private long recordId;
    private Scanner scanner;
    private Member member;
    private LocalDateTime scannedDateTime;
    private  String status;
}
