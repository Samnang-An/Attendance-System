package com.ea.group.four.attendancesystem.response;

import com.ea.group.four.attendancesystem.domain.Account;
import com.ea.group.four.attendancesystem.domain.Member;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Data
public class MemberAccountResponse {
    private long memberAccountId;
    private Member member;
    private Account account;
    private int defaultBalance;
    private boolean enabled;
}
