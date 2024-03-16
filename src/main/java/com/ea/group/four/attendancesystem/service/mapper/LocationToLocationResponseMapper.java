package com.ea.group.four.attendancesystem.service.mapper;

import com.ea.group.four.attendancesystem.domain.Location;
import com.ea.group.four.attendancesystem.service.response.response.LocationResponse;
import edu.miu.common.service.mapper.BaseMapper;
import ma.glasnost.orika.MapperFactory;
import org.springframework.stereotype.Component;

@Component
public class LocationToLocationResponseMapper extends BaseMapper<Location, LocationResponse> {

  public LocationToLocationResponseMapper(MapperFactory mapperFactory) {
    super(mapperFactory, Location.class, LocationResponse.class);
  }

}
