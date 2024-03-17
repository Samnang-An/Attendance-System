package com.ea.group.four.attendancesystem.controller;

import com.ea.group.four.attendancesystem.domain.AccountType;
import com.ea.group.four.attendancesystem.service.response.AccountTypeResponse;
import edu.miu.common.controller.BaseReadWriteController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/account-types")
public class AccountTypeController extends
    BaseReadWriteController<AccountTypeResponse, AccountType, Long> {

}
