package com.ea.group.four.attendancesystem.domain;


import java.io.Serializable;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Column;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.JoinColumn;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Scanner implements Serializable {

  private static final long serialVersionUID = 1L;
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "scanner_id")
  private long scannerId;

  @ManyToOne
  @JoinColumn(name = "account_type_id", nullable = false)
  private AccountType accountType;
  @ManyToOne
  @JoinColumn(name = "location_id", nullable = false)
  private Location location;
  @ManyToOne
  @JoinColumn(name = "event_id", nullable = false)
  private Event event;
}
