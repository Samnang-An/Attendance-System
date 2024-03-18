package com.ea.group.four.attendancesystem.service.impl;

import com.ea.group.four.attendancesystem.domain.Event;
import com.ea.group.four.attendancesystem.domain.Member;
import com.ea.group.four.attendancesystem.repository.EventRepository;
import com.ea.group.four.attendancesystem.service.EventService;
import com.ea.group.four.attendancesystem.service.mapper.EventToEventReponseMapper;
import com.ea.group.four.attendancesystem.service.response.EventResponse;
import edu.miu.common.service.BaseReadWriteServiceImpl;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EventServiceImpl extends
    BaseReadWriteServiceImpl<EventResponse, Event, Long> implements
    EventService {

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private EventToEventReponseMapper requestMapper;
    @Override
    public EventResponse addMembersToEvent(Long eventId, List<Member> members) {
        Optional<Event> optionalEvent = eventRepository.findById(eventId);
        if (optionalEvent.isPresent()){
            Event event = optionalEvent.get();
            List<Member> existingMembers = event.getMembers();
            existingMembers.addAll(members);
            event.setMembers(existingMembers);
            event = eventRepository.save(event);
            EventResponse eventResponse  = requestMapper.map(event);
            return eventResponse;
        }
        else{
            throw new EntityNotFoundException("Event not found with id: " + eventId);
        }

//        return null;
    }
}
