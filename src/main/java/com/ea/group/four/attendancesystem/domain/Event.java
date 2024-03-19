package com.ea.group.four.attendancesystem.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Fetch;

import javax.swing.plaf.synth.SynthTreeUI;

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


  @Transient
  private Map<String,List<String>> schedule  = new HashMap<>();

  @ManyToMany(fetch = FetchType.LAZY)

  private List<Member> members;




}


