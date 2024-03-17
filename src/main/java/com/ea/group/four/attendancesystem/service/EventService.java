package com.ea.group.four.attendancesystem.service;

import com.ea.group.four.attendancesystem.domain.Event;
import com.ea.group.four.attendancesystem.domain.Role;
import com.ea.group.four.attendancesystem.service.response.response.AccountResponse;
import com.ea.group.four.attendancesystem.service.response.response.EventResponse;
import edu.miu.common.service.BaseReadWriteService;

public interface EventService extends BaseReadWriteService<EventResponse, Event, Long> {
}
