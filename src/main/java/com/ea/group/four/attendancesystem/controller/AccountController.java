package com.ea.group.four.attendancesystem.controller;

import com.ea.group.four.attendancesystem.domain.Account;
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

  @Autowired
  ScannerRecordService scannerRecordService;

  public AccountController() {
  }
  public AccountController(ScannerRecordService scannerRecordService) {
    this.scannerRecordService = scannerRecordService;
  }

  @GetMapping("/{accountTypeId}/attendance/{fromDate}/{toDate}")
  public ResponseEntity<?> getRecords(@PathVariable long accountTypeId,
      @PathVariable String fromDate,
      @PathVariable String toDate) {
    List<ScanRecordResponse> records = scannerRecordService.findByAccountBetweenDates(accountTypeId,
        fromDate, toDate);
    return ResponseEntity.ok(records);
  }

}
