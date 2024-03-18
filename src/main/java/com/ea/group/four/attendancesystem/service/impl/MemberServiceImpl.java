package com.ea.group.four.attendancesystem.service.impl;

import com.ea.group.four.attendancesystem.domain.Member;
import com.ea.group.four.attendancesystem.domain.ScanRecord;
import com.ea.group.four.attendancesystem.repository.MemberRepository;
import com.ea.group.four.attendancesystem.repository.ScannerRecordRepository;
import com.ea.group.four.attendancesystem.service.MemberService;
import com.ea.group.four.attendancesystem.service.response.MemberResponse;
import edu.miu.common.service.BaseReadWriteServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class MemberServiceImpl extends
    BaseReadWriteServiceImpl<MemberResponse, Member, Long> implements
    MemberService {

    @Autowired
    private ScannerRecordRepository scanRecordRepository;

    @Autowired
    private MemberRepository memberRepository;
    @Override
    public List<ScanRecord> getMemberAttendance(Long memberId) {
        Optional<Member> member = memberRepository.findById(memberId);
        List<ScanRecord> list = new ArrayList<>();
        if(member.isPresent()) {
            return scanRecordRepository.findByMember(member.get());
        }
        return list;
    }
}
