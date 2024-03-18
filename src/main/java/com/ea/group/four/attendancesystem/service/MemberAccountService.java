package com.ea.group.four.attendancesystem.service;

import com.ea.group.four.attendancesystem.domain.MemberAccount;
import com.ea.group.four.attendancesystem.service.response.MemberAccountResponse;
import edu.miu.common.service.BaseReadWriteService;
import java.util.List;

public interface MemberAccountService extends
    BaseReadWriteService<MemberAccountResponse, MemberAccount, Long> {

  List<MemberAccount> findAllValidAccount();

}
