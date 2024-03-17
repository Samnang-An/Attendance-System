package com.ea.group.four.attendancesystem.service.impl;

import com.ea.group.four.attendancesystem.domain.Location;
import com.ea.group.four.attendancesystem.service.LocationService;
import com.ea.group.four.attendancesystem.service.response.response.LocationResponse;
import edu.miu.common.service.BaseReadWriteServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class LocationServiceImpl extends
    BaseReadWriteServiceImpl<LocationResponse, Location, Long> implements
    LocationService {

}
