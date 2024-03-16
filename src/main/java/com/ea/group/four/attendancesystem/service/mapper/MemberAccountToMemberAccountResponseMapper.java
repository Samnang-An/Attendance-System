package com.ea.group.four.attendancesystem.service.mapper;

import com.ea.group.four.attendancesystem.domain.Location;
import com.ea.group.four.attendancesystem.domain.MemberAccount;
import com.ea.group.four.attendancesystem.service.response.response.LocationResponse;
import com.ea.group.four.attendancesystem.service.response.response.MemberAccountResponse;
import edu.miu.common.service.mapper.BaseMapper;
import ma.glasnost.orika.MapperFactory;
import org.springframework.stereotype.Component;

@Component
public class MemberAccountToMemberAccountResponseMapper extends BaseMapper<MemberAccount, MemberAccountResponse> {

  public MemberAccountToMemberAccountResponseMapper(MapperFactory mapperFactory) {
    super(mapperFactory, MemberAccount.class, MemberAccountResponse.class);
  }

}
