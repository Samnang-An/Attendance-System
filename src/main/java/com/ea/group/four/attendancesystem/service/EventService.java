package com.ea.group.four.attendancesystem.service;

import com.ea.group.four.attendancesystem.domain.Event;
import com.ea.group.four.attendancesystem.domain.Member;
import com.ea.group.four.attendancesystem.service.response.EventResponse;
import com.ea.group.four.attendancesystem.service.response.ScanRecordResponse;
import edu.miu.common.service.BaseReadWriteService;

import java.util.List;
import java.util.Map;

public interface EventService extends BaseReadWriteService<EventResponse, Event, Long> {


    public EventResponse create(EventResponse request);
    public EventResponse addMembersToEvent(Long eventId, List<Member> members);

    public List<ScanRecordResponse> calculateAttendanceOfEvent(Long eventId);

}
