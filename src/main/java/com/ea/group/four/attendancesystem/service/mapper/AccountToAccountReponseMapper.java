package com.ea.group.four.attendancesystem.service.mapper;

import com.ea.group.four.attendancesystem.domain.Account;
import com.ea.group.four.attendancesystem.service.response.AccountResponse;
import edu.miu.common.service.mapper.BaseMapper;
import ma.glasnost.orika.MapperFactory;
import org.springframework.stereotype.Component;

@Component
public class AccountToAccountReponseMapper extends BaseMapper<Account, AccountResponse> {

  public AccountToAccountReponseMapper(MapperFactory mapperFactory) {
    super(mapperFactory, Account.class, AccountResponse.class);
  }

}
