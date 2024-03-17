package com.ea.group.four.attendancesystem.service.impl;

import com.ea.group.four.attendancesystem.domain.Account;
import com.ea.group.four.attendancesystem.service.AccountService;
import com.ea.group.four.attendancesystem.service.response.AccountResponse;
import edu.miu.common.service.BaseReadWriteServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class AccountServiceImpl extends
    BaseReadWriteServiceImpl<AccountResponse, Account, Long> implements
    AccountService {

}
