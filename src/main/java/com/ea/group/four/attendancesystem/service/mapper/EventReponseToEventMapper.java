package com.ea.group.four.attendancesystem.service.mapper;

import com.ea.group.four.attendancesystem.domain.Event;
import com.ea.group.four.attendancesystem.service.response.response.EventResponse;
import edu.miu.common.service.mapper.BaseMapper;
import ma.glasnost.orika.MapperFactory;
import org.springframework.stereotype.Component;

@Component
public class EventReponseToEventMapper extends BaseMapper<EventResponse, Event> {

  public EventReponseToEventMapper(MapperFactory mapperFactory) {
    super(mapperFactory, EventResponse.class, Event.class);
  }

}
