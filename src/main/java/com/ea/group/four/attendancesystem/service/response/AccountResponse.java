package com.ea.group.four.attendancesystem.service.response;

import com.ea.group.four.attendancesystem.domain.AccountType;
import java.io.Serializable;
import lombok.Data;

@Data
public class AccountResponse implements Serializable {

  private static final long serialVersionUID = 1L;
  private long accountId;
  private String name;
  private String description;
  private AccountType accountType;

}
