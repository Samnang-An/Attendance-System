package com.ea.group.four.attendancesystem.service.impl;

import com.ea.group.four.attendancesystem.domain.MemberAccount;
import com.ea.group.four.attendancesystem.service.MemberAccountService;
import com.ea.group.four.attendancesystem.service.response.MemberAccountResponse;
import edu.miu.common.service.BaseReadWriteServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class MemberAccountServiceImpl extends
    BaseReadWriteServiceImpl<MemberAccountResponse, MemberAccount, Long> implements
    MemberAccountService {

}
