package com.ea.group.four.attendancesystem.service.mapper;

import com.ea.group.four.attendancesystem.domain.MemberAccount;
import com.ea.group.four.attendancesystem.service.response.MemberAccountResponse;
import edu.miu.common.service.mapper.BaseMapper;
import ma.glasnost.orika.MapperFactory;
import org.springframework.stereotype.Component;

@Component
public class MemberAccountResponseToMemberAccountMapper extends
    BaseMapper<MemberAccountResponse, MemberAccount> {

  public MemberAccountResponseToMemberAccountMapper(MapperFactory mapperFactory) {
    super(mapperFactory, MemberAccountResponse.class, MemberAccount.class);
  }

}
