package com.ea.group.four.attendancesystem.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.*;

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
public class Session implements Serializable {

  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long sessionId;

  private LocalDate sessionDate;
  private LocalTime startTime;
  private LocalTime endTime;
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "event_id")
  @JsonBackReference
  private Event event;

}
