package com.ea.group.four.attendancesystem.controller;

import com.ea.group.four.attendancesystem.domain.Location;
import com.ea.group.four.attendancesystem.service.response.LocationResponse;
import edu.miu.common.controller.BaseReadWriteController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/locations")
public class LocationController extends BaseReadWriteController<LocationResponse, Location, Long> {



}
