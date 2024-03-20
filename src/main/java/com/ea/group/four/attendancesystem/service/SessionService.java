package com.ea.group.four.attendancesystem.service;

import com.ea.group.four.attendancesystem.domain.Event;
import com.ea.group.four.attendancesystem.domain.Session;
import com.ea.group.four.attendancesystem.service.response.SessionResponse;
import edu.miu.common.service.BaseReadWriteService;
import java.time.LocalDate;
import java.time.LocalTime;


public interface SessionService extends BaseReadWriteService<SessionResponse, Session, Long> {

    public SessionResponse addSession(Event event, SessionResponse sessionResponse);

    SessionResponse findByValidSession(Long eventId, LocalDate sessionDate, LocalTime startTime, LocalTime endTime);

    public SessionResponse updateSession(Long eventId, SessionResponse sessionResponse);

    public void deleteSession(SessionResponse sessionResponse);

}