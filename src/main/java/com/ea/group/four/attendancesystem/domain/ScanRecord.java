package com.ea.group.four.attendancesystem.domain;

import jakarta.persistence.*;

import java.io.Serializable;
import java.time.LocalDateTime;
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
  private long recordId;
  @ManyToOne
  private Scanner scanner;
  @ManyToOne
  private Member member;

  private LocalDateTime scannedDateTime;

  private String status;

}
