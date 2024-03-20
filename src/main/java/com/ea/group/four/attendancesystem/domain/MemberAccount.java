package com.ea.group.four.attendancesystem.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class MemberAccount implements Serializable {

  private static final long serialVersionUID = 1L;
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private long memberAccountId;
  @ManyToOne(fetch = FetchType.LAZY)
  private Member member;
  @ManyToOne(fetch = FetchType.LAZY)
  private Account account;
  private int defaultBalance;
  private boolean enabled;

}
