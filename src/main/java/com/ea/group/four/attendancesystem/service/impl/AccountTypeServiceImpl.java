package com.ea.group.four.attendancesystem.service.impl;

import com.ea.group.four.attendancesystem.domain.AccountType;
import com.ea.group.four.attendancesystem.service.AccountTypeService;
import com.ea.group.four.attendancesystem.service.response.AccountTypeResponse;
import edu.miu.common.service.BaseReadWriteServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class AccountTypeServiceImpl extends
    BaseReadWriteServiceImpl<AccountTypeResponse, AccountType, Long> implements
    AccountTypeService {


}
