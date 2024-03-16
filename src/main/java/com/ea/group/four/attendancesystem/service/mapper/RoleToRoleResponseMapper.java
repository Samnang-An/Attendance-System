package com.ea.group.four.attendancesystem.service.mapper;

import com.ea.group.four.attendancesystem.domain.Role;
import com.ea.group.four.attendancesystem.service.response.response.RoleResponse;
import edu.miu.common.service.mapper.BaseMapper;
import ma.glasnost.orika.MapperFactory;
import org.springframework.stereotype.Component;

@Component
public class RoleToRoleResponseMapper extends BaseMapper<Role, RoleResponse> {

  public RoleToRoleResponseMapper(MapperFactory mapperFactory) {
    super(mapperFactory, Role.class, RoleResponse.class);
  }

}
