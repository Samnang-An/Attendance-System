package com.ea.group.four.attendancesystem.service;

import com.ea.group.four.attendancesystem.domain.Event;
import com.ea.group.four.attendancesystem.domain.Member;
import com.ea.group.four.attendancesystem.service.response.EventResponse;
import com.ea.group.four.attendancesystem.service.response.ScanRecordResponse;
import com.ea.group.four.attendancesystem.service.response.SessionResponse;
import edu.miu.common.service.BaseReadWriteService;

import java.util.List;
import java.util.Map;

public interface EventService extends BaseReadWriteService<EventResponse, Event, Long> {


    public EventResponse create(EventResponse request);
    public SessionResponse addSession(Long eventId, SessionResponse session);
    public SessionResponse updateSession(Long eventId,SessionResponse sessionResponse);
    public void deleteSession(SessionResponse session);
    public EventResponse updateSchedule(Long eventId,Map<String,List<String>> schedule);
    public EventResponse addMembersToEvent(Long eventId, List<Member> members);
    public EventResponse removeMember(Long eventId,Long memberId);
    public List<ScanRecordResponse> calculateAttendanceOfEvent(Long eventId);


}
