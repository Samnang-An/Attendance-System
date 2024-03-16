package com.ea.group.four.attendancesystem.controller;

import com.ea.group.four.attendancesystem.domain.Role;
import com.ea.group.four.attendancesystem.service.response.response.RoleResponse;
import edu.miu.common.controller.BaseReadWriteController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/roles")
public class RoleController extends BaseReadWriteController<RoleResponse, Role, Long> {

}
