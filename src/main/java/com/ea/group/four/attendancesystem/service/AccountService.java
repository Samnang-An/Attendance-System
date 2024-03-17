package com.ea.group.four.attendancesystem.service;

import com.ea.group.four.attendancesystem.domain.Account;
import com.ea.group.four.attendancesystem.service.response.AccountResponse;
import edu.miu.common.service.BaseReadWriteService;

public interface AccountService extends BaseReadWriteService<AccountResponse, Account, Long> {

}
