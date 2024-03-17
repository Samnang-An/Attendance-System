package com.ea.group.four.attendancesystem.service;

import com.ea.group.four.attendancesystem.domain.Account;
import com.ea.group.four.attendancesystem.domain.Role;
import com.ea.group.four.attendancesystem.service.response.response.AccountResponse;
import com.ea.group.four.attendancesystem.service.response.response.RoleResponse;
import edu.miu.common.service.BaseReadWriteService;

public interface AccountService extends BaseReadWriteService<AccountResponse, Account, Long> {
}
