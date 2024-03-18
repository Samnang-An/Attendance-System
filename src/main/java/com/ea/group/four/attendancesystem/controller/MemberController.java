package com.ea.group.four.attendancesystem.controller;
import com.ea.group.four.attendancesystem.domain.Member;
import com.ea.group.four.attendancesystem.service.RoleService;
import com.ea.group.four.attendancesystem.service.response.MemberResponse;
import com.ea.group.four.attendancesystem.service.response.RoleResponse;
import edu.miu.common.controller.BaseReadWriteController;
import org.springframework.beans.factory.annotation.Autowired;
import com.ea.group.four.attendancesystem.domain.ScanRecord;
import com.ea.group.four.attendancesystem.service.MemberService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/members")
public class MemberController extends BaseReadWriteController<MemberResponse, Member, Long> {

    @Autowired
    private RoleService roleService;
    @Autowired
    MemberService memberService;

    @GetMapping("/{memeberId}/roles")
    public ResponseEntity<?> getRolesByMemberId(@PathVariable Long memberId) {
        List<RoleResponse> roles=memberService.getRolesByMemberId(memberId);
        if(roles!=null)
            return ResponseEntity.ok(roles);
       return ResponseEntity.notFound().build();
    }

    @GetMapping("/{memberId}/attendance")
    public ResponseEntity<?> getMemberAttendance(@PathVariable Long memberId) {
        List<ScanRecord> scanRecords = memberService.getMemberAttendance(memberId);
        return ResponseEntity.ok(scanRecords);
    }
    @PostMapping("/{memberId}/roles/{roleId}")
    public ResponseEntity<?> addRoleByMemberId(@PathVariable Long memberId, @ PathVariable Long roleId){
        try{
            memberService.addRoleByMemberId(memberId, roleId);
            return ResponseEntity.ok("Role added successfully");
        }catch (Exception e){

            return ResponseEntity.badRequest().body("Role not added");
        }
    }
    @DeleteMapping("/{memberId}/roles/{roleId}")
    public ResponseEntity<?> deleteRoleByMemberIdAndRoleId(@PathVariable Long memberId, @PathVariable Long roleId){
        try{
            memberService.deleteRoleByMemberIdAndRoleId(memberId, roleId);
            return ResponseEntity.ok("Role deleted successfully");
        }catch (Exception e){
            return ResponseEntity.badRequest().body("Role not deleted");
        }
    }
    @PutMapping("/{memberId}/roles/{roleId}")
    public ResponseEntity<?> updateRoleByMemberIdAndRoleId(@PathVariable Long memberId, @PathVariable Long roleId, @RequestBody RoleResponse roleResponse){
        try{
            memberService.updateRoleByMemberIdAndRoleId(memberId, roleId, roleResponse);
            return ResponseEntity.ok("Role updated successfully");
        }catch (Exception e){
            return ResponseEntity.badRequest().body("Role not updated");
        }
    }

}
