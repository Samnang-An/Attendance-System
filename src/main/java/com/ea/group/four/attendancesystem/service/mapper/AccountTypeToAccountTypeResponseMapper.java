package com.ea.group.four.attendancesystem.service.mapper;

import com.ea.group.four.attendancesystem.domain.AccountType;
import com.ea.group.four.attendancesystem.service.response.response.AccountTypeResponse;
import edu.miu.common.service.mapper.BaseMapper;
import ma.glasnost.orika.MapperFactory;
import org.springframework.stereotype.Component;

@Component
public class AccountTypeToAccountTypeResponseMapper extends
    BaseMapper<AccountType, AccountTypeResponse> {

  public AccountTypeToAccountTypeResponseMapper(MapperFactory mapperFactory) {
    super(mapperFactory, AccountType.class, AccountTypeResponse.class);
  }

}
