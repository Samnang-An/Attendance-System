package com.ea.group.four.attendancesystem.service.impl;

import com.ea.group.four.attendancesystem.domain.Event;
import com.ea.group.four.attendancesystem.domain.Session;
import com.ea.group.four.attendancesystem.exception.InvalidSessionException;
import com.ea.group.four.attendancesystem.repository.SessionRepository;
import com.ea.group.four.attendancesystem.service.SessionService;
import com.ea.group.four.attendancesystem.service.mapper.SessionResponseToSessionMapper;
import com.ea.group.four.attendancesystem.service.mapper.SessionToSessionResponseMapper;
import com.ea.group.four.attendancesystem.service.response.SessionResponse;
import edu.miu.common.service.BaseReadWriteServiceImpl;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class SessionServiceImpl extends
        BaseReadWriteServiceImpl<SessionResponse, Session, Long> implements
        SessionService {

    @Autowired
    SessionToSessionResponseMapper sessionToSessionResponseMapper;

    @Autowired
    SessionResponseToSessionMapper sessionResponseToSessionMapper;

    @Autowired
    private SessionRepository sessionRepository;

    public SessionServiceImpl(SessionToSessionResponseMapper sessionToSessionResponseMapper, SessionResponseToSessionMapper sessionResponseToSessionMapper, SessionRepository sessionRepository) {
        this.sessionToSessionResponseMapper = sessionToSessionResponseMapper;
        this.sessionResponseToSessionMapper = sessionResponseToSessionMapper;
        this.sessionRepository = sessionRepository;
    }

    @Override
    public SessionResponse addSession(Event event, SessionResponse sessionResponse) {
            LocalDate sessionDate = sessionResponse.getSessionDate();
            if (sessionDate.isBefore(event.getStartDate()) || sessionDate.isAfter(event.getEndDate())) {
                throw new InvalidSessionException(" Session is not inside the event timeline");
            }
            Session newSession = sessionResponseToSessionMapper.map(sessionResponse);
            newSession.setEvent(event);
            sessionRepository.save(newSession);
            return  sessionToSessionResponseMapper.map(newSession);
    }

    @Override
    public SessionResponse findByValidSession(Long eventId, LocalDate sessionDate, LocalTime startTime,
                                              LocalTime endTime) {
        return sessionToSessionResponseMapper.map(sessionRepository.findSessionByEventEventIdAndSessionDateAndStartTimeLessThanEqualAndEndTimeGreaterThanEqual(
                eventId, sessionDate, startTime, endTime));
    }

    @Override
    public SessionResponse updateSession(Event event, SessionResponse sessionResponse) {
        if(sessionRepository.findSessionByEventEventIdAndSessionId(event.getEventId(), sessionResponse.getSessionId()) == null){
            throw new InvalidSessionException("Invalid Session");
        }
        Session updatedSession = sessionResponseToSessionMapper.map(sessionResponse);
        updatedSession.setEvent(event);
        SessionResponse updatedSessionResponse = sessionToSessionResponseMapper.map(sessionRepository.save(updatedSession));
        updatedSessionResponse.setEventId(event.getEventId());
        return updatedSessionResponse;
    }

    @Override
    public void deleteSession(SessionResponse sessionResponse) {
        sessionRepository.delete(sessionResponseToSessionMapper.map(sessionResponse));
    }

    @Override
    public List<SessionResponse> findAllSessionsInEvent(Long eventId) {
        List<Session> sessionList = sessionRepository.findAllByEventEventId(eventId);
        List<SessionResponse> sessionResponseList = new ArrayList<>();
        for(Session session: sessionList){
                   SessionResponse tempResponse = sessionToSessionResponseMapper.map(session);
                   tempResponse.setEventId(eventId);
            sessionResponseList.add(tempResponse);
        }
        return sessionResponseList;
    }

}