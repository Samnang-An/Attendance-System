package com.ea.group.four.attendancesystem.domain;

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
  @GeneratedValue(strategy = GenerationType.AUTO)
  private long sessionId;

  private LocalDate sessionDate;
  private LocalTime startTime;
  private LocalTime endTime;

  @ManyToOne
  @JoinColumn(name = "event_id")
  private Event event;

}
