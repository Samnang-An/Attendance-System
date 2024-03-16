package com.ea.group.four.attendancesystem.service.mapper;

import com.ea.group.four.attendancesystem.domain.Session;
import com.ea.group.four.attendancesystem.service.response.response.SessionResponse;
import edu.miu.common.service.mapper.BaseMapper;
import ma.glasnost.orika.MapperFactory;
import org.springframework.stereotype.Component;

@Component
public class SessionToSessionResponseMapper extends BaseMapper<SessionResponse, Session> {

  public SessionToSessionResponseMapper(MapperFactory mapperFactory) {
    super(mapperFactory, SessionResponse.class, Session.class);
  }

}
