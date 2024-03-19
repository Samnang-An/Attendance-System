package com.ea.group.four.attendancesystem.controller;

import com.ea.group.four.attendancesystem.domain.Member;
import com.ea.group.four.attendancesystem.domain.ScanRecord;
import com.ea.group.four.attendancesystem.service.MemberService;
import com.ea.group.four.attendancesystem.service.response.MemberResponse;
import com.ea.group.four.attendancesystem.service.response.ScanRecordDTO;
import com.ea.group.four.attendancesystem.service.response.ScanRecordResponse;
import edu.miu.common.controller.BaseReadWriteController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/members")
public class MemberController extends BaseReadWriteController<MemberResponse, Member, Long> {
    @Autowired
    MemberService memberService;

    @GetMapping("/{memberId}/attendance")
    public ResponseEntity<?> getMemberAttendance(@PathVariable Long memberId) {
        List<ScanRecord> scanRecords = memberService.getMemberAttendance(memberId);
        return ResponseEntity.ok(scanRecords);
    }

    @GetMapping("/{memberId}/events/{eventId}/attendance")
    public ResponseEntity<?> getMemberAttendanceForEvent(@PathVariable Long memberId, @PathVariable Long eventId){
        List<ScanRecordDTO> scanRecordResponseList =  memberService.getMemberAttendanceForEvent(memberId,eventId);
        return  ResponseEntity.ok(scanRecordResponseList);
    }


}
