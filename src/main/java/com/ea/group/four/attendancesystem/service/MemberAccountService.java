package com.ea.group.four.attendancesystem.service;

import com.ea.group.four.attendancesystem.domain.MemberAccount;
import com.ea.group.four.attendancesystem.service.response.response.MemberAccountResponse;
import edu.miu.common.service.BaseReadWriteService;

public interface MemberAccountService extends
    BaseReadWriteService<MemberAccountResponse, MemberAccount, Long> {

}
