package com.ea.group.four.attendancesystem.service.impl;

import com.ea.group.four.attendancesystem.domain.Member;
import com.ea.group.four.attendancesystem.domain.ScanRecord;
import com.ea.group.four.attendancesystem.repository.MemberRepository;
import com.ea.group.four.attendancesystem.repository.ScannerRecordRepository;
import com.ea.group.four.attendancesystem.service.MemberAccountService;
import com.ea.group.four.attendancesystem.service.MemberService;
import com.ea.group.four.attendancesystem.service.RoleService;
import com.ea.group.four.attendancesystem.service.mapper.ScannerRecordToScannerRecordResponseMapper;
import com.ea.group.four.attendancesystem.service.response.AccountResponse;
import com.ea.group.four.attendancesystem.service.response.MemberResponse;
import com.ea.group.four.attendancesystem.service.response.RoleResponse;
import com.ea.group.four.attendancesystem.service.response.ScanRecordDTO;
import edu.miu.common.service.BaseReadWriteServiceImpl;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MemberServiceImpl extends
    BaseReadWriteServiceImpl<MemberResponse, Member, Long> implements MemberService {


  @Autowired
  ScannerRecordToScannerRecordResponseMapper scannerRecordMapper;
  @Autowired
  private ScannerRecordRepository scanRecordRepository;
  @Autowired
  private MemberRepository memberRepository;
  @Autowired
  private RoleService roleService;
  @Autowired
  private MemberAccountService memberAccountService;

  MemberServiceImpl() {

  }

  MemberServiceImpl(MemberRepository memberRepository, ScannerRecordRepository scannerRecordRepository) {
    this.memberRepository = memberRepository;
    this.scanRecordRepository = scannerRecordRepository;
  }

  @Override
  public MemberResponse create(MemberResponse memberResponse) {
    MemberResponse member = super.create(memberResponse);
    List<AccountResponse> accounts = member.getRoles().stream().map(RoleResponse::getAccounts)
        .flatMap(List::stream).toList();
    memberAccountService.assignMemberAccount(member, accounts);
    return member;
  }

  @Override
  public List<ScanRecord> getMemberAttendance(Long memberId) {
    Optional<Member> member = memberRepository.findById(memberId);
    List<ScanRecord> list = new ArrayList<>();
    if (member.isPresent()) {
      return scanRecordRepository.findByMember(member.get());
    }
    return list;
  }

  @Override
  public List<ScanRecordDTO> getMemberAttendanceForEvent(Long memberId, Long eventId) {
    List<ScanRecord> scanRecordList = scanRecordRepository.findByMemberAndEvent(memberId, eventId);
    if (scanRecordList.isEmpty()) {
      return Collections.emptyList();
    }
    List<ScanRecordDTO> scanRecordResponseList = new ArrayList<>();
    for (ScanRecord record : scanRecordList) {
      scanRecordResponseList.add(ScanRecordDTO.of(record));
    }
    return scanRecordResponseList;

  }


  public List<RoleResponse> getRolesByMemberId(Long memberId) {
    return findById(memberId).getRoles();
  }

  @Override
  public void addRoleByMemberId(Long memberId, Long roleId) {
    MemberResponse memberOptional = findById(memberId);
    RoleResponse roleOptional = roleService.findById(roleId);
    if (!memberOptional.getRoles().contains(roleOptional)) {
      memberOptional.getRoles().add(roleOptional);
      update(memberOptional.getMemberId(), memberOptional);
      memberAccountService.assignMemberAccount(memberOptional, roleOptional.getAccounts());
    }
  }

  @Override
  public void deleteRoleByMemberIdAndRoleId(Long memberId, Long roleId) {
    MemberResponse memberOptional = findById(memberId);
    memberOptional.getRoles().removeIf(role -> role.getRoleId() == roleId);
    update(memberOptional.getMemberId(), memberOptional);
    memberAccountService.refreshMemberAccount(memberOptional);
  }

  @Override
  public void updateRoleByMemberIdAndRoleId(Long memberId, Long roleId, RoleResponse roleResponse) {
    MemberResponse memberOptional = findById(memberId);
    RoleResponse roleOptional = roleService.findById(roleId);
    RoleResponse newRole = roleService.findById(roleResponse.getRoleId());
    if (memberOptional.getRoles().contains(roleOptional)) {
      memberOptional.getRoles().remove(roleOptional);
      memberOptional.getRoles().add(newRole);
      update(memberOptional.getMemberId(), memberOptional);
    }
  }
}

