package com.ea.group.four.attendancesystem.service.impl;

import com.ea.group.four.attendancesystem.domain.Event;
import com.ea.group.four.attendancesystem.domain.Session;
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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

@Service
public class SessionServiceImpl extends
    BaseReadWriteServiceImpl<SessionResponse, Session, Long> implements
    SessionService {

    @Autowired
    SessionToSessionResponseMapper sessionToSessionResponseMapper;

    @Autowired
    SessionResponseToSessionMapper sessionResponseToSessionMapper;

    @Autowired
    private SessionRepository sessionRepository;

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    EventToEventReponseMapper eventToEventReponseMapper;

    @Autowired
    EventReponseToEventMapper eventReponseToEventMapper;

    @Override
    public EventResponse addSession(Long eventId, SessionResponse sessionResponse) {
        System.out.println(sessionResponse);
        Optional<Event> eventOptional = eventRepository.findById(eventId);
        if(eventOptional.isPresent()) {
            Event event = eventOptional.get();
            LocalDate sessionDate = sessionResponse.getSessionDate();
            if(sessionDate.isBefore(event.getStartDate()) || sessionDate.isAfter(event.getEndDate())){
                return null;
            }
            Session newSession = sessionResponseToSessionMapper.map(sessionResponse);
            newSession.setEvent(event);
            sessionRepository.save(newSession);
            return eventToEventReponseMapper.map(event);
        }
        return null;
    }


}
