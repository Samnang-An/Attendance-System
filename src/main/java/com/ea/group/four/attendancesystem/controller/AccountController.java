package com.ea.group.four.attendancesystem.controller;

import com.ea.group.four.attendancesystem.domain.Account;
import com.ea.group.four.attendancesystem.service.response.AccountResponse;
import edu.miu.common.controller.BaseReadWriteController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/accounts")
public class AccountController extends BaseReadWriteController<AccountResponse, Account, Long> {

}
