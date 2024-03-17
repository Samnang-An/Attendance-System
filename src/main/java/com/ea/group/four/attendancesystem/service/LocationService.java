package com.ea.group.four.attendancesystem.service;

import com.ea.group.four.attendancesystem.domain.Event;
import com.ea.group.four.attendancesystem.domain.Location;
import com.ea.group.four.attendancesystem.service.response.response.EventResponse;
import com.ea.group.four.attendancesystem.service.response.response.LocationResponse;
import edu.miu.common.service.BaseReadWriteService;

public interface LocationService extends BaseReadWriteService<LocationResponse, Location, Long> {
}
