package com.ea.group.four.attendancesystem.service.impl;

import com.ea.group.four.attendancesystem.domain.Account;
import com.ea.group.four.attendancesystem.domain.Role;
import com.ea.group.four.attendancesystem.repository.AccountRepository;
import com.ea.group.four.attendancesystem.repository.RoleRepository;
import com.ea.group.four.attendancesystem.service.AccountService;
import com.ea.group.four.attendancesystem.service.response.AccountResponse;
import edu.miu.common.service.BaseReadWriteServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AccountServiceImpl extends
    BaseReadWriteServiceImpl<AccountResponse, Account, Long> implements
    AccountService {
    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private RoleRepository roleRepository;

}
