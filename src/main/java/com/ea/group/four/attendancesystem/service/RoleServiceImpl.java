package com.ea.group.four.attendancesystem.service;

import com.ea.group.four.attendancesystem.domain.Role;
import com.ea.group.four.attendancesystem.service.response.response.RoleResponse;
import edu.miu.common.service.BaseReadWriteServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl extends BaseReadWriteServiceImpl<RoleResponse, Role, Long> implements RoleService{
}
