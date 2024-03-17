package com.ea.group.four.attendancesystem.service.response;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;
import lombok.Data;

@Data
public class SessionResponse implements Serializable {

  private static final long serialVersionUID = 1L;
  private long sessionId;
  private LocalDate sessionDate;
  private LocalTime startTime;
  private LocalTime endTime;
}
