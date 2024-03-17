package com.ea.group.four.attendancesystem.service;

import com.ea.group.four.attendancesystem.domain.AccountType;
import com.ea.group.four.attendancesystem.domain.Role;
import com.ea.group.four.attendancesystem.service.response.response.AccountResponse;
import com.ea.group.four.attendancesystem.service.response.response.AccountTypeResponse;
import edu.miu.common.service.BaseReadWriteService;

public interface AccountTypeService extends BaseReadWriteService<AccountTypeResponse, AccountType, Long> {
}
