package com.ea.group.four.attendancesystem.service;

import com.ea.group.four.attendancesystem.domain.Member;
import com.ea.group.four.attendancesystem.service.response.response.MemberResponse;
import edu.miu.common.service.BaseReadWriteService;

public interface MemberService extends BaseReadWriteService<MemberResponse, Member, Long> {

}
