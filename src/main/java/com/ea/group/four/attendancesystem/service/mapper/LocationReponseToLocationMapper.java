package com.ea.group.four.attendancesystem.service.mapper;

import com.ea.group.four.attendancesystem.domain.Location;
import com.ea.group.four.attendancesystem.service.response.response.LocationResponse;
import edu.miu.common.service.mapper.BaseMapper;
import ma.glasnost.orika.MapperFactory;
import org.springframework.stereotype.Component;

@Component
public class LocationReponseToLocationMapper extends BaseMapper<LocationResponse, Location> {

  public LocationReponseToLocationMapper(MapperFactory mapperFactory) {
    super(mapperFactory, LocationResponse.class, Location.class);
  }

}
