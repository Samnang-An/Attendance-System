package com.ea.group.four.attendancesystem.service;

import com.ea.group.four.attendancesystem.domain.Member;
import com.ea.group.four.attendancesystem.domain.ScanRecord;
import com.ea.group.four.attendancesystem.service.response.MemberResponse;
import com.ea.group.four.attendancesystem.service.response.ScanRecordDTO;
import com.ea.group.four.attendancesystem.service.response.RoleResponse;
import edu.miu.common.service.BaseReadWriteService;
import java.util.List;

public interface MemberService extends BaseReadWriteService<MemberResponse, Member, Long> {
    public List<ScanRecord> getMemberAttendance(Long memberId);
    public List<ScanRecordDTO> getMemberAttendanceForEvent(Long memberId, Long eventId);
    public List<RoleResponse> getRolesByMemberId(Long memberId);
    // Add Role by MemberId
    public void addRoleByMemberId(Long memberId, Long roleId);
    // Delete role by member id and role id
    public void deleteRoleByMemberIdAndRoleId(Long memberId, Long roleId);
    // Update Role by MemberId and RoleId
    void updateRoleByMemberIdAndRoleId(Long memberId, Long roleId, RoleResponse roleResponse);

}
