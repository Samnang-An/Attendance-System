package com.ea.group.four.attendancesystem.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class ScanRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long recordId;
    @ManyToOne
    private Scanner scanner;
    @ManyToOne
    private Member member;

    private LocalDateTime scannedDateTime;

    private  String status;

}
