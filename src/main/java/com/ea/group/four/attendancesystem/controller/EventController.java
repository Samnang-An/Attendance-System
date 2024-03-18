package com.ea.group.four.attendancesystem.controller;

import com.ea.group.four.attendancesystem.domain.Event;
import com.ea.group.four.attendancesystem.service.response.EventResponse;
import edu.miu.common.controller.BaseReadWriteController;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/events")
public class EventController extends BaseReadWriteController<EventResponse, Event,Long> {


}
