package com.ea.group.four.attendancesystem.controller;

import com.ea.group.four.attendancesystem.domain.Account;
import com.ea.group.four.attendancesystem.domain.ScanRecord;
import com.ea.group.four.attendancesystem.service.ScannerRecordService;
import com.ea.group.four.attendancesystem.service.response.AccountResponse;
import com.ea.group.four.attendancesystem.service.response.ScanRecordResponse;
import edu.miu.common.controller.BaseReadWriteController;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/accounts")
public class AccountController extends BaseReadWriteController<AccountResponse, Account, Long> {




}
