package com.ea.group.four.attendancesystem.service.response;

import com.ea.group.four.attendancesystem.domain.Member;
import com.ea.group.four.attendancesystem.domain.Session;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import lombok.Data;

@Data
public class EventResponse implements Serializable {

  private static final long serialVersionUID = 1L;
  private long eventId;
  private String name;
  private String description;
  private LocalDate startDate;
  private LocalDate endDate;
  private String occurEvent;

  private List<Session> sessions = new ArrayList<>();
  private List<Member> members = new ArrayList<>();
}
