package com.ea.group.four.attendancesystem.service;

import com.ea.group.four.attendancesystem.domain.Role;
import com.ea.group.four.attendancesystem.service.response.AccountResponse;
import com.ea.group.four.attendancesystem.service.response.RoleResponse;
import edu.miu.common.service.BaseReadWriteService;
import java.util.List;

public interface RoleService extends BaseReadWriteService<RoleResponse, Role, Long> {

  RoleResponse assignAccountToRole(long roleId, List<AccountResponse> accounts);
}
