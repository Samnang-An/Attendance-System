package com.ea.group.four.attendancesystem.service;

import com.ea.group.four.attendancesystem.domain.Event;
import com.ea.group.four.attendancesystem.domain.Member;
import com.ea.group.four.attendancesystem.service.response.EventResponse;
import edu.miu.common.service.BaseReadWriteService;

import java.util.List;

public interface EventService extends BaseReadWriteService<EventResponse, Event, Long> {

    public EventResponse addMembersToEvent(Long eventId, List<Member> members);

}
