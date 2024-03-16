package com.ea.group.four.attendancesystem.service.response.response;

import com.ea.group.four.attendancesystem.domain.AccountType;
import com.ea.group.four.attendancesystem.domain.Role;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import lombok.Data;

@Data
public class AccountResponse implements Serializable {

  private static final long serialVersionUID = 1L;
  private long accountId;
  private String name;
  private String description;
  private AccountType accountType;
  private List<Role> roles = new ArrayList<>();

}
