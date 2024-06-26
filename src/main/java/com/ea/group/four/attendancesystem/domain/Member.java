package com.ea.group.four.attendancesystem.domain;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Member implements Serializable {

  private static final long serialVersionUID = 1L;
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long memberId;

  private String firstName;
  private String lastName;
  private String barcode;
  private String email;
  @OneToMany(cascade = CascadeType.MERGE)
  @JoinTable(name = "Member_Role")
  private List<Role> roles = new ArrayList<>();

  @ManyToMany(mappedBy = "members")
  @JsonIgnore
  private List<Event> events;

}
