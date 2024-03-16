package com.ea.group.four.attendancesystem.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class MemberAccount {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long memberAccountId;
    @ManyToOne
    private Member member;
    @ManyToOne
    private Account account;
    private int defaultBalance;
    private boolean enabled;

}
