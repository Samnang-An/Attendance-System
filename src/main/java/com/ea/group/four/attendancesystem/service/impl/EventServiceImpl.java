package com.ea.group.four.attendancesystem.service.impl;

import com.ea.group.four.attendancesystem.domain.Event;
import com.ea.group.four.attendancesystem.domain.Member;
import com.ea.group.four.attendancesystem.domain.Session;
import com.ea.group.four.attendancesystem.integration.jms.JMSSender;
import com.ea.group.four.attendancesystem.repository.EventRepository;
import com.ea.group.four.attendancesystem.repository.SessionRepository;
import com.ea.group.four.attendancesystem.service.EventService;
import com.ea.group.four.attendancesystem.service.mapper.EventToEventReponseMapper;
import com.ea.group.four.attendancesystem.service.mapper.EventReponseToEventMapper;
import com.ea.group.four.attendancesystem.service.response.EventResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import edu.miu.common.service.BaseReadWriteServiceImpl;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.support.SimpleTriggerContext;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class EventServiceImpl extends
    BaseReadWriteServiceImpl<EventResponse, Event, Long> implements
    EventService {


    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private EventToEventReponseMapper revertRequestMapper;

    @Autowired
    private  EventReponseToEventMapper requestMapper;

    @Autowired
    private SessionRepository sessionRepository;

    @Autowired
    private JMSSender jmsSender;

    @Override
    public EventResponse create(EventResponse request) {
        Event event = requestMapper.map(request);
        Map<String,List<String>> schedule = event.getSchedule();

        eventRepository.save(event);
        String eventMessage = convertEventAndScheduleToString(event,schedule);
        jmsSender.sendJMSMessage(eventMessage,"event.schedule.queue");

        return revertRequestMapper.map(event);
    }


    public String convertEventAndScheduleToString(Event event, Map<String,List<String>> schedule){
        try{
            ObjectMapper objectMapper = new ObjectMapper();
            String eventIdJson = objectMapper.writeValueAsString(event.getEventId());
            String scheduleJson = objectMapper.writeValueAsString(schedule);
            String eventMessage = eventIdJson + "###" + scheduleJson;
            return eventMessage;

        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }


    @Override
    public EventResponse addMembersToEvent(Long eventId, List<Member> members) {
        Optional<Event> optionalEvent = eventRepository.findById(eventId);
        if (optionalEvent.isPresent()){
            Event event = optionalEvent.get();
            List<Member> existingMembers = event.getMembers();
            existingMembers.addAll(members);
            event.setMembers(existingMembers);
            event = eventRepository.save(event);
            EventResponse eventResponse  = revertRequestMapper.map(event);
            return eventResponse;
        }
        else{
            throw new EntityNotFoundException("Event not found with id: " + eventId);
        }


    }
}
