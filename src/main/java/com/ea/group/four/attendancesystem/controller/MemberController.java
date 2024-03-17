package com.ea.group.four.attendancesystem.controller;


import com.ea.group.four.attendancesystem.domain.Member;
import com.ea.group.four.attendancesystem.service.RoleService;
import com.ea.group.four.attendancesystem.service.response.response.MemberResponse;
import edu.miu.common.controller.BaseReadWriteController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/members")
public class MemberController extends BaseReadWriteController<MemberResponse, Member, Long> {

    @Autowired
    private RoleService roleService;

    @GetMapping("/members/{memeberId}/roles")
    public String getRolesByMemberId(@PathVariable Long memberId) {
        return roleService.findById(memberId).toString();
    }


}
