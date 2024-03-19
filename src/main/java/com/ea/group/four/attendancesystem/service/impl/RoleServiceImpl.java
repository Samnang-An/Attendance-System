package com.ea.group.four.attendancesystem.service.impl;

import com.ea.group.four.attendancesystem.domain.Account;
import com.ea.group.four.attendancesystem.domain.Role;
import com.ea.group.four.attendancesystem.service.RoleService;
import com.ea.group.four.attendancesystem.service.response.RoleResponse;
import edu.miu.common.service.BaseReadWriteServiceImpl;
import java.util.List;
import java.util.Objects;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl extends BaseReadWriteServiceImpl<RoleResponse, Role, Long> implements
    RoleService {

  @Override
  public RoleResponse assignAccountToRole(long roleId, List<Account> accounts) {
    RoleResponse role = findById(roleId);
    if (Objects.isNull(role)) {
      return null;
    }
    role.setAccounts(accounts);
    return create(role);
  }
}
