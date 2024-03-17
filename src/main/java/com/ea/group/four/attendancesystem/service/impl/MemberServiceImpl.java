package com.ea.group.four.attendancesystem.service.impl;

import com.ea.group.four.attendancesystem.domain.Member;
import com.ea.group.four.attendancesystem.repository.MemberRepository;
import com.ea.group.four.attendancesystem.service.MemberService;
import com.ea.group.four.attendancesystem.service.response.MemberResponse;
import edu.miu.common.service.BaseReadWriteServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MemberServiceImpl extends
    BaseReadWriteServiceImpl<MemberResponse, Member, Long> implements
    MemberService {
    @Autowired
    private MemberRepository memberRepository;

}
