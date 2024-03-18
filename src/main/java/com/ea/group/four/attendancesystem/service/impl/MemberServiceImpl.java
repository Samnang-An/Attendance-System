package com.ea.group.four.attendancesystem.service.impl;

import com.ea.group.four.attendancesystem.domain.Member;
import com.ea.group.four.attendancesystem.domain.Role;
import com.ea.group.four.attendancesystem.domain.ScanRecord;
import com.ea.group.four.attendancesystem.repository.MemberRepository;
import com.ea.group.four.attendancesystem.repository.RoleRepository;
import com.ea.group.four.attendancesystem.repository.ScannerRecordRepository;
import com.ea.group.four.attendancesystem.service.MemberService;
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
      Optional<Member> member=  memberRepository.findById(memberId);
      if (member.isPresent()) {
          List<RoleResponse> roleResponses = new ArrayList<>();
          for (Role role : member.get().getRoles()) {
              roleResponses.add(roleToRoleResponseMapper.map(role));
          }
          return roleResponses;
      }
        return null;
    }

    @Override
    public void addRoleByMemberId(Long memberId, Long roleId) {
        Optional<Member> memberOptional= memberRepository.findById(memberId);
        Optional<Role> roleOptional= roleRepository.findById(roleId);
        if(memberOptional.isPresent() && roleOptional.isPresent()){
            Member member= memberOptional.get();
            Role role= roleOptional.get();
            //Add the role to the member if it does not exist
            if (!member.getRoles().contains(role)) {
                member.getRoles().add(role);
                memberRepository.save(member);
            }
        }
    }

    @Override
    public void deleteRoleByMemberIdAndRoleId(Long memberId, Long roleId) {
        Optional<Member> memberOptional= memberRepository.findById(memberId);
        if(memberOptional.isPresent()){
            Member member= memberOptional.get();
            //Remove the role from the member if it exists
            member.getRoles().removeIf(role -> role.getRoleId()==roleId);
            memberRepository.save(member);
        }
    }

    @Override
    public void updateRoleByMemberIdAndRoleId(Long memberId, Long roleId, RoleResponse roleResponse) {
        Optional<Member> memberOptional= memberRepository.findById(memberId);
        if(memberOptional.isPresent()){
            Member member=memberOptional.get();
            // Find the role to update
            for(Role role: member.getRoles()){
                if(role.getRoleId()==roleId){
                    role.setName(roleResponse.getName());
                    //Save the updated role to the database
                    roleRepository.save(role);
                    break;
                }
            }
        }

    }


}
