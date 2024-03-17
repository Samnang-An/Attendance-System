package com.ea.group.four.attendancesystem.service.mapper;

import com.ea.group.four.attendancesystem.domain.Role;
import com.ea.group.four.attendancesystem.service.response.RoleResponse;
import edu.miu.common.service.mapper.BaseMapper;
import ma.glasnost.orika.MapperFactory;
import org.springframework.stereotype.Component;

@Component
public class RoleResponseToRoleMapper extends BaseMapper<RoleResponse, Role> {

  public RoleResponseToRoleMapper(MapperFactory mapperFactory) {
    super(mapperFactory, RoleResponse.class, Role.class);
  }

}
