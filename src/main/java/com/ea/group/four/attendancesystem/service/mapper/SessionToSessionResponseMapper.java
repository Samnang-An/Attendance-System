package com.ea.group.four.attendancesystem.service.mapper;

import com.ea.group.four.attendancesystem.domain.ScheduleSession;
import com.ea.group.four.attendancesystem.service.response.response.ScheduleSessionResponse;
import edu.miu.common.service.mapper.BaseMapper;
import ma.glasnost.orika.MapperFactory;
import org.springframework.stereotype.Component;

@Component
public class SessionToSessionResponseMapper extends BaseMapper<ScheduleSessionResponse, ScheduleSession> {

  public SessionToSessionResponseMapper(MapperFactory mapperFactory) {
    super(mapperFactory, ScheduleSessionResponse.class, ScheduleSession.class);
  }

}
