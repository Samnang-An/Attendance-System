package com.ea.group.four.attendancesystem.service.impl;

import com.ea.group.four.attendancesystem.domain.Event;
import com.ea.group.four.attendancesystem.domain.Session;
import com.ea.group.four.attendancesystem.exception.InvalidSessionException;
import com.ea.group.four.attendancesystem.repository.EventRepository;
import com.ea.group.four.attendancesystem.repository.SessionRepository;
import com.ea.group.four.attendancesystem.service.SessionService;
import com.ea.group.four.attendancesystem.service.mapper.EventReponseToEventMapper;
import com.ea.group.four.attendancesystem.service.mapper.EventToEventReponseMapper;
import com.ea.group.four.attendancesystem.service.mapper.SessionResponseToSessionMapper;
import com.ea.group.four.attendancesystem.service.mapper.SessionToSessionResponseMapper;
import com.ea.group.four.attendancesystem.service.response.EventResponse;
import com.ea.group.four.attendancesystem.service.response.SessionResponse;
import edu.miu.common.service.BaseReadWriteServiceImpl;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Optional;
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
    EventToEventReponseMapper eventToEventReponseMapper;
    @Autowired
    EventReponseToEventMapper eventReponseToEventMapper;
    @Autowired
    private SessionRepository sessionRepository;
    @Autowired
    private EventRepository eventRepository;

    @Override
    public EventResponse addSession(Long eventId, SessionResponse sessionResponse) {
        System.out.println(sessionResponse);
        Optional<Event> eventOptional = eventRepository.findById(eventId);
        if (eventOptional.isPresent()) {
            Event event = eventOptional.get();
            LocalDate sessionDate = sessionResponse.getSessionDate();
            if (sessionDate.isBefore(event.getStartDate()) || sessionDate.isAfter(event.getEndDate())) {
                return null;
            }
            Session newSession = sessionResponseToSessionMapper.map(sessionResponse);
            newSession.setEvent(event);
            sessionRepository.save(newSession);
            return eventToEventReponseMapper.map(event);
        }
        return null;
    }

    @Override
    public SessionResponse findByValidSession(Long eventId, LocalDate sessionDate, LocalTime startTime,
                                              LocalTime endTime) {
        return sessionToSessionResponseMapper.map(sessionRepository.findSessionByEventEventIdAndSessionDateAndStartTimeLessThanEqualAndEndTimeGreaterThanEqual(
                eventId, sessionDate, startTime, endTime));
    }

    @Override
    public SessionResponse updateSession(Long eventId, Long sessionId, SessionResponse sessionResponse) {
        Session session = sessionRepository.findByEvent_EventIdAndSessionId(eventId,sessionId);
        if(session == null){
            throw new InvalidSessionException("Invalid Session");
        }
        Session updatedSession = sessionResponseToSessionMapper.map(sessionResponse);

        return sessionToSessionResponseMapper.map(sessionRepository.save(updatedSession));
    }

    @Override
    public void deleteSession(SessionResponse sessionResponse) {
        sessionRepository.delete(sessionResponseToSessionMapper.map(sessionResponse));
    }


}