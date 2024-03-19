package com.ea.group.four.attendancesystem.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class ScanRecord implements Serializable {

  private static final long serialVersionUID = 1L;
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "record_id")
  private long recordId;
  @ManyToOne
  @JoinColumn(name = "scanner_id")
  private Scanner scanner;
  @ManyToOne
  @JoinColumn(name = "member_id")
  private Member member;

  @Column(name = "scanned_date")
  private LocalDate scannedDate;

  @Column(name = "scanned_time")
  private LocalTime scannedTime;

  @Column(name = "status")
  private String status;

  @Column(name = "deprecated")
  private boolean isDeprecated;
}