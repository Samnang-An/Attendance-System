package com.ea.group.four.attendancesystem.service.response;

import com.ea.group.four.attendancesystem.domain.Account;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import lombok.Data;

@Data
public class RoleResponse implements Serializable {

  private static final long serialVersionUID = 1L;
  private long roleId;
  private String name;
  private List<Account> accounts = new ArrayList<>();

}
