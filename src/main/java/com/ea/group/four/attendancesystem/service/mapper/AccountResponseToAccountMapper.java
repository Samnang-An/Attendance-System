package com.ea.group.four.attendancesystem.service.mapper;

import com.ea.group.four.attendancesystem.domain.Account;
import com.ea.group.four.attendancesystem.service.response.response.AccountResponse;
import edu.miu.common.service.mapper.BaseMapper;
import ma.glasnost.orika.MapperFactory;
import org.springframework.stereotype.Component;

@Component
public class AccountResponseToAccountMapper extends BaseMapper<AccountResponse, Account> {

  public AccountResponseToAccountMapper(MapperFactory mapperFactory) {
    super(mapperFactory, AccountResponse.class, Account.class);
  }
}
