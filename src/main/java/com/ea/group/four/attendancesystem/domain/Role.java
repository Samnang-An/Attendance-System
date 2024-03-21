package com.ea.group.four.attendancesystem.domain;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Role implements Serializable {

  private static final long serialVersionUID = 1L;
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private long roleId;
  private String name;

  @ManyToMany(cascade = CascadeType.MERGE)
  private List<Account> accounts;

}
