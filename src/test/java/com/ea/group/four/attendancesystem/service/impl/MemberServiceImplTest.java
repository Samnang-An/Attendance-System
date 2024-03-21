package com.ea.group.four.attendancesystem.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.ea.group.four.attendancesystem.domain.Event;
import com.ea.group.four.attendancesystem.domain.Member;

import com.ea.group.four.attendancesystem.domain.ScanRecord;

import com.ea.group.four.attendancesystem.repository.MemberRepository;
import com.ea.group.four.attendancesystem.repository.ScannerRecordRepository;
import com.ea.group.four.attendancesystem.service.MemberAccountService;
import com.ea.group.four.attendancesystem.service.RoleService;
import com.ea.group.four.attendancesystem.service.mapper.ScannerRecordToScannerRecordResponseMapper;
import com.ea.group.four.attendancesystem.service.response.AccountResponse;
import com.ea.group.four.attendancesystem.service.response.MemberResponse;
import com.ea.group.four.attendancesystem.service.response.RoleResponse;
import edu.miu.common.repository.BaseRepository;
import edu.miu.common.service.mapper.BaseMapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {MemberServiceImpl.class})
@ExtendWith(SpringExtension.class)
class MemberServiceImplTest {
    @MockBean
    private BaseMapper<Member, MemberResponse> baseMapper;

    @MockBean
    private BaseMapper<MemberResponse, Member> baseMapper2;

    @MockBean
    private BaseRepository<Member, Long> baseRepository;

    @MockBean
    private BaseRepository baseRepository2;

    @MockBean
    private MemberAccountService memberAccountService;

    @MockBean
    private MemberRepository memberRepository;

    @Autowired
    private MemberServiceImpl memberServiceImpl;

    @MockBean
    private RoleService roleService;

    @MockBean
    private ScannerRecordRepository scannerRecordRepository;

    @MockBean
    private ScannerRecordToScannerRecordResponseMapper scannerRecordToScannerRecordResponseMapper;

    /**
     * Method under test: {@link MemberServiceImpl#getRolesByMemberId(Long)}
     */
    @Test
    void testGetRolesByMemberId() {
        Member member = new Member();
        member.setBarcode("Barcode");
        member.setEmail("jane.doe@example.org");
        member.setEvents(new ArrayList<>());
        member.setFirstName("Jane");
        member.setLastName("Doe");
        member.setMemberId(1L);
        member.setRoles(new ArrayList<>());
        Optional<Member> ofResult = Optional.of(member);
        when(memberRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);

        MemberResponse memberResponse = new MemberResponse();
        memberResponse.setBarcode("Barcode");
        memberResponse.setEmail("jane.doe@example.org");
        memberResponse.setFirstName("Jane");
        memberResponse.setLastName("Doe");
        memberResponse.setMemberId(1L);
        ArrayList<RoleResponse> roles = new ArrayList<>();
        memberResponse.setRoles(roles);
        when(baseMapper.map(Mockito.<Member>any())).thenReturn(memberResponse);
        List<RoleResponse> actualRolesByMemberId = memberServiceImpl.getRolesByMemberId(1L);
        verify(baseMapper).map(Mockito.<Member>any());
        verify(memberRepository).findById(Mockito.<Long>any());
        assertTrue(actualRolesByMemberId.isEmpty());
        assertSame(roles, actualRolesByMemberId);
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
        List<ScanRecord> actualScanRecords = memberServiceImpl.getMemberAttendance(memberId);

        // Assert
        assertEquals(expectedScanRecords.size(), actualScanRecords.size());
    }


    /**
     * Method under test: {@link MemberServiceImpl#addRoleByMemberId(Long, Long)}
     */
    @Test
    void testAddRoleByMemberId() {
        doNothing().when(memberAccountService)
                .assignMemberAccount(Mockito.<MemberResponse>any(), Mockito.<List<AccountResponse>>any());

        Member member = new Member();
        member.setBarcode("Barcode");
        member.setEmail("jane.doe@example.org");
        member.setEvents(new ArrayList<>());
        member.setFirstName("Jane");
        member.setLastName("Doe");
        member.setMemberId(1L);
        member.setRoles(new ArrayList<>());
        Optional<Member> ofResult = Optional.of(member);

        Member member2 = new Member();
        member2.setBarcode("Barcode");
        member2.setEmail("jane.doe@example.org");
        member2.setEvents(new ArrayList<>());
        member2.setFirstName("Jane");
        member2.setLastName("Doe");
        member2.setMemberId(1L);
        member2.setRoles(new ArrayList<>());
        when(memberRepository.save(Mockito.<Member>any())).thenReturn(member2);
        when(memberRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);

        RoleResponse roleResponse = new RoleResponse();
        roleResponse.setAccounts(new ArrayList<>());
        roleResponse.setName("Name");
        roleResponse.setRoleId(1L);
        when(roleService.findById(Mockito.<Long>any())).thenReturn(roleResponse);

        MemberResponse memberResponse = new MemberResponse();
        memberResponse.setBarcode("Barcode");
        memberResponse.setEmail("jane.doe@example.org");
        memberResponse.setFirstName("Jane");
        memberResponse.setLastName("Doe");
        memberResponse.setMemberId(1L);
        memberResponse.setRoles(new ArrayList<>());
        when(baseMapper.map(Mockito.<Member>any())).thenReturn(memberResponse);

        Member member3 = new Member();
        member3.setBarcode("Barcode");
        member3.setEmail("jane.doe@example.org");
        member3.setEvents(new ArrayList<>());
        member3.setFirstName("Jane");
        member3.setLastName("Doe");
        member3.setMemberId(1L);
        member3.setRoles(new ArrayList<>());
        when(baseMapper2.map(Mockito.<MemberResponse>any(), Mockito.<Member>any())).thenReturn(member3);
        memberServiceImpl.addRoleByMemberId(1L, 1L);
        verify(memberAccountService).assignMemberAccount(Mockito.<MemberResponse>any(),
                Mockito.<List<AccountResponse>>any());
        verify(roleService).findById(Mockito.<Long>any());
        verify(baseMapper, atLeast(1)).map(Mockito.<Member>any());
        verify(baseMapper2).map(Mockito.<MemberResponse>any(), Mockito.<Member>any());
        verify(memberRepository, atLeast(1)).findById(Mockito.<Long>any());
        verify(memberRepository).save(Mockito.<Member>any());
    }

    /**
     * Method under test:
     * {@link MemberServiceImpl#deleteRoleByMemberIdAndRoleId(Long, Long)}
     */
    @Test
    void testDeleteRoleByMemberIdAndRoleId() {
        doNothing().when(memberAccountService).refreshMemberAccount(Mockito.<MemberResponse>any());

        Member member = new Member();
        member.setBarcode("Barcode");
        member.setEmail("jane.doe@example.org");
        member.setEvents(new ArrayList<>());
        member.setFirstName("Jane");
        member.setLastName("Doe");
        member.setMemberId(1L);
        member.setRoles(new ArrayList<>());
        Optional<Member> ofResult = Optional.of(member);

        Member member2 = new Member();
        member2.setBarcode("Barcode");
        member2.setEmail("jane.doe@example.org");
        member2.setEvents(new ArrayList<>());
        member2.setFirstName("Jane");
        member2.setLastName("Doe");
        member2.setMemberId(1L);
        member2.setRoles(new ArrayList<>());
        when(memberRepository.save(Mockito.<Member>any())).thenReturn(member2);
        when(memberRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);

        MemberResponse memberResponse = new MemberResponse();
        memberResponse.setBarcode("Barcode");
        memberResponse.setEmail("jane.doe@example.org");
        memberResponse.setFirstName("Jane");
        memberResponse.setLastName("Doe");
        memberResponse.setMemberId(1L);
        memberResponse.setRoles(new ArrayList<>());
        when(baseMapper.map(Mockito.<Member>any())).thenReturn(memberResponse);

        Member member3 = new Member();
        member3.setBarcode("Barcode");
        member3.setEmail("jane.doe@example.org");
        member3.setEvents(new ArrayList<>());
        member3.setFirstName("Jane");
        member3.setLastName("Doe");
        member3.setMemberId(1L);
        member3.setRoles(new ArrayList<>());
        when(baseMapper2.map(Mockito.<MemberResponse>any(), Mockito.<Member>any())).thenReturn(member3);
        memberServiceImpl.deleteRoleByMemberIdAndRoleId(1L, 1L);
        verify(memberAccountService).refreshMemberAccount(Mockito.<MemberResponse>any());
        verify(baseMapper, atLeast(1)).map(Mockito.<Member>any());
        verify(baseMapper2).map(Mockito.<MemberResponse>any(), Mockito.<Member>any());
        verify(memberRepository, atLeast(1)).findById(Mockito.<Long>any());
        verify(memberRepository).save(Mockito.<Member>any());
    }

    /**
     * Method under test:
     * {@link MemberServiceImpl#updateRoleByMemberIdAndRoleId(Long, Long, RoleResponse)}
     */
    @Test
    void testUpdateRoleByMemberIdAndRoleId() {
        Member member = new Member();
        member.setBarcode("Barcode");
        member.setEmail("jane.doe@example.org");
        ArrayList<Event> events = new ArrayList<>();
        member.setEvents(events);
        member.setFirstName("Jane");
        member.setLastName("Doe");
        member.setMemberId(1L);
        member.setRoles(new ArrayList<>());
        Optional<Member> ofResult = Optional.of(member);
        when(memberRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);

        RoleResponse roleResponse = new RoleResponse();
        roleResponse.setAccounts(new ArrayList<>());
        roleResponse.setName("Name");
        roleResponse.setRoleId(1L);
        when(roleService.findById(Mockito.<Long>any())).thenReturn(roleResponse);

        MemberResponse memberResponse = new MemberResponse();
        memberResponse.setBarcode("Barcode");
        memberResponse.setEmail("jane.doe@example.org");
        memberResponse.setFirstName("Jane");
        memberResponse.setLastName("Doe");
        memberResponse.setMemberId(1L);
        memberResponse.setRoles(new ArrayList<>());
        when(baseMapper.map(Mockito.<Member>any())).thenReturn(memberResponse);

        RoleResponse roleResponse2 = new RoleResponse();
        roleResponse2.setAccounts(new ArrayList<>());
        roleResponse2.setName("Name");
        roleResponse2.setRoleId(1L);
        memberServiceImpl.updateRoleByMemberIdAndRoleId(1L, 1L, roleResponse2);
        verify(roleService, atLeast(1)).findById(Mockito.<Long>any());
        verify(baseMapper).map(Mockito.<Member>any());
        verify(memberRepository).findById(Mockito.<Long>any());
        assertEquals("Name", roleResponse2.getName());
        assertEquals(1L, roleResponse2.getRoleId());
        assertEquals(events, roleResponse2.getAccounts());
    }

}

