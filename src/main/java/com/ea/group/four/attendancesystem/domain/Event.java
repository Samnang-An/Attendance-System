package com.ea.group.four.attendancesystem.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Event implements Serializable {

  private static final long serialVersionUID = 1L;
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private long eventId;
  private String name;
  private String description;
  private LocalDate startDate;
  private LocalDate endDate;
  private String occurEvent;

  @OneToMany
  private List<Session> sessions = new ArrayList<>();

  @OneToMany
  private List<Member> members = new ArrayList<>();

}


