package com.ea.group.four.attendancesystem.service;

import com.ea.group.four.attendancesystem.domain.MemberAccount;
import com.ea.group.four.attendancesystem.repository.MemberAccountRepository;
import com.ea.group.four.attendancesystem.service.response.AccountResponse;
import com.ea.group.four.attendancesystem.service.response.MemberAccountResponse;
import com.ea.group.four.attendancesystem.service.response.MemberResponse;
import edu.miu.common.service.BaseReadWriteService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public interface MemberAccountService extends
    BaseReadWriteService<MemberAccountResponse, MemberAccount, Long> {

  List<MemberAccountResponse> findAllValidAccount();

  void assignMemberAccount(MemberResponse memberId, List<AccountResponse> accounts);

  void refreshMemberAccount(MemberResponse memberOptional);

  MemberAccountResponse findByMemberIdAndAccountTypeId(long memberId, long accountTypeId);


}
