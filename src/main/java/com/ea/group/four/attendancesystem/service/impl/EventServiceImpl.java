package com.ea.group.four.attendancesystem.service.impl;

import com.ea.group.four.attendancesystem.domain.Event;
import com.ea.group.four.attendancesystem.service.EventService;
import com.ea.group.four.attendancesystem.service.response.EventResponse;
import edu.miu.common.service.BaseReadWriteServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class EventServiceImpl extends
    BaseReadWriteServiceImpl<EventResponse, Event, Long> implements
    EventService {

}
