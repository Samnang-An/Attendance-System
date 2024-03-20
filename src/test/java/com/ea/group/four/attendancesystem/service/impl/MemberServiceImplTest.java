package com.ea.group.four.attendancesystem.service.impl;

import com.ea.group.four.attendancesystem.domain.Member;
import com.ea.group.four.attendancesystem.domain.ScanRecord;
import com.ea.group.four.attendancesystem.repository.MemberRepository;
import com.ea.group.four.attendancesystem.repository.ScannerRecordRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class MemberServiceImplTest {
    @Mock
    private MemberRepository memberRepository;

    @Mock
    private ScannerRecordRepository scannerRecordRepository;

    @InjectMocks
    private MemberServiceImpl memberService;

    @BeforeEach
    void setUp() {
        memberService = new MemberServiceImpl(memberRepository, scannerRecordRepository);
    }

    @Test
    void testGetMemberAttendance() {
        // Arrange
        Long memberId = 1L;
        Member member = new Member();
        member.setMemberId(memberId);

        List<ScanRecord> expectedScanRecords = new ArrayList<>();
        expectedScanRecords.add(new ScanRecord());
        expectedScanRecords.add(new ScanRecord());

        // Mocking behavior
        when(memberRepository.findById(memberId)).thenReturn(Optional.of(member));
        when(scannerRecordRepository.findByMember(member)).thenReturn(expectedScanRecords);

        // Act
        List<ScanRecord> actualScanRecords = memberService.getMemberAttendance(memberId);

        // Assert
        assertEquals(expectedScanRecords.size(), actualScanRecords.size());
    }
}