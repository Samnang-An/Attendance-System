package com.ea.group.four.attendancesystem.service.mapper;

import com.ea.group.four.attendancesystem.domain.AccountType;
import com.ea.group.four.attendancesystem.service.response.AccountTypeResponse;
import edu.miu.common.service.mapper.BaseMapper;
import ma.glasnost.orika.MapperFactory;
import org.springframework.stereotype.Component;

@Component
public class AccountTypeResponseToAccountTypeMapper extends
    BaseMapper<AccountTypeResponse, AccountType> {

  public AccountTypeResponseToAccountTypeMapper(MapperFactory mapperFactory) {
    super(mapperFactory, AccountTypeResponse.class, AccountType.class);
  }
}
