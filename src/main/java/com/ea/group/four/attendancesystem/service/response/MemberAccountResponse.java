package com.ea.group.four.attendancesystem.service.response;

import java.io.Serializable;
import lombok.Data;

@Data
public class MemberAccountResponse implements Serializable {

  private static final long serialVersionUID = 1L;
  private long memberAccountId;
  private MemberResponse member;
  private AccountResponse account;
  private int defaultBalance;
  private boolean enabled;
}
