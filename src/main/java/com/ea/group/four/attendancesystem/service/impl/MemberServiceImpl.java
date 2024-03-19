package com.ea.group.four.attendancesystem.service.impl;

import com.ea.group.four.attendancesystem.domain.Account;
import com.ea.group.four.attendancesystem.domain.Member;
import com.ea.group.four.attendancesystem.domain.Role;
import com.ea.group.four.attendancesystem.domain.ScanRecord;
import com.ea.group.four.attendancesystem.repository.MemberRepository;
import com.ea.group.four.attendancesystem.repository.RoleRepository;
import com.ea.group.four.attendancesystem.repository.ScannerRecordRepository;
import com.ea.group.four.attendancesystem.service.MemberService;
import com.ea.group.four.attendancesystem.service.RoleService;
import com.ea.group.four.attendancesystem.service.mapper.RoleResponseToRoleMapper;
import com.ea.group.four.attendancesystem.service.mapper.RoleToRoleResponseMapper;
import com.ea.group.four.attendancesystem.service.response.MemberResponse;
import com.ea.group.four.attendancesystem.service.response.RoleResponse;
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
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private RoleToRoleResponseMapper roleToRoleResponseMapper;
    @Autowired
    private RoleResponseToRoleMapper roleResponseToRoleMapper;
    @Autowired
    private RoleService roleService;
    @Override
    public List<ScanRecord> getMemberAttendance(Long memberId) {
        Optional<Member> member = memberRepository.findById(memberId);
        List<ScanRecord> list = new ArrayList<>();
        if(member.isPresent()) {
            return scanRecordRepository.findByMember(member.get());
        }
        return list;
    }

    @Override
    public List<RoleResponse> getRolesByMemberId(Long memberId) {
      return findById(memberId).getRoles();
    }

    @Override
    public void addRoleByMemberId(Long memberId, Long roleId) {
        MemberResponse memberOptional=  findById(memberId);
        RoleResponse roleOptional= roleService.findById(roleId);
            if ( !memberOptional.getRoles().contains(roleOptional)) {
                memberOptional.getRoles().add(roleOptional);
               update(memberOptional.getMemberId(), memberOptional);
            }
        }

    @Override
    public void deleteRoleByMemberIdAndRoleId(Long memberId, Long roleId) {
        MemberResponse memberOptional= findById(memberId);
        memberOptional.getRoles().removeIf(role -> role.getRoleId()==roleId);
        update(memberOptional.getMemberId(), memberOptional);
    }

    @Override
    public void updateRoleByMemberIdAndRoleId(Long memberId, Long roleId, RoleResponse roleResponse) {
        MemberResponse memberOptional = findById(memberId);
        RoleResponse roleOptional= roleService.findById(roleId);
        RoleResponse newRole=roleService.findById(roleResponse.getRoleId());
        if (memberOptional.getRoles().contains(roleOptional)) {
            memberOptional.getRoles().remove(roleOptional);
            memberOptional.getRoles().add(newRole);
            update(memberOptional.getMemberId(), memberOptional);
            }
        }
    }

