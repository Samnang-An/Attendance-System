package com.ea.group.four.attendancesystem.controller;

import com.ea.group.four.attendancesystem.domain.Account;
import com.ea.group.four.attendancesystem.domain.Role;
import com.ea.group.four.attendancesystem.service.RoleService;
import com.ea.group.four.attendancesystem.service.response.AccountResponse;
import com.ea.group.four.attendancesystem.service.response.RoleResponse;
import edu.miu.common.controller.BaseReadWriteController;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/roles")
public class RoleController extends BaseReadWriteController<RoleResponse, Role, Long> {

  @Autowired
  private RoleService roleService;

  @PostMapping("/{roleId}/accounts/")
  public ResponseEntity<?> assignAccountToRole(@PathVariable long roleId,
      @RequestBody List<AccountResponse> accounts) {
    RoleResponse roleResponse = roleService.assignAccountToRole(roleId, accounts);
    return ResponseEntity.ok().body(roleResponse);
  }

}
