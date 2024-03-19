package com.ea.group.four.attendancesystem.service;

import com.ea.group.four.attendancesystem.domain.Member;
import com.ea.group.four.attendancesystem.domain.ScanRecord;
import com.ea.group.four.attendancesystem.service.response.MemberResponse;
import com.ea.group.four.attendancesystem.service.response.ScanRecordDTO;
import com.ea.group.four.attendancesystem.service.response.ScanRecordResponse;
import edu.miu.common.service.BaseReadWriteService;

import java.util.List;

public interface MemberService extends BaseReadWriteService<MemberResponse, Member, Long> {
    public List<ScanRecord> getMemberAttendance(Long memberId);
    public List<ScanRecordDTO> getMemberAttendanceForEvent(Long memberId, Long eventId);
}
