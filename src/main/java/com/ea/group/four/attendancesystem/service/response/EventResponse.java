package com.ea.group.four.attendancesystem.service.response;

import com.ea.group.four.attendancesystem.domain.Member;
import com.ea.group.four.attendancesystem.domain.Session;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import lombok.Data;

@Data
public class EventResponse implements Serializable {

  private static final long serialVersionUID = 1L;
  private long eventId;
  private String name;
  private String description;
  private LocalDate startDate;
  private LocalDate endDate;

  private Map<String,List<String>> schedule;

  private List<Member> members = new ArrayList<>();
}
