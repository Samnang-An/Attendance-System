package com.ea.group.four.attendancesystem.service;

import com.ea.group.four.attendancesystem.domain.AccountType;
import com.ea.group.four.attendancesystem.service.response.AccountTypeResponse;
import edu.miu.common.service.BaseReadWriteService;

public interface AccountTypeService extends
    BaseReadWriteService<AccountTypeResponse, AccountType, Long> {

}
