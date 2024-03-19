package com.ea.group.four.attendancesystem.service.impl;

import com.ea.group.four.attendancesystem.domain.Event;
import com.ea.group.four.attendancesystem.domain.Member;
import com.ea.group.four.attendancesystem.domain.ScanRecord;
import com.ea.group.four.attendancesystem.domain.Scanner;
import com.ea.group.four.attendancesystem.integration.jms.JMSSender;
import com.ea.group.four.attendancesystem.repository.EventRepository;
import com.ea.group.four.attendancesystem.repository.ScannerRecordRepository;
import com.ea.group.four.attendancesystem.service.EventService;
import com.ea.group.four.attendancesystem.service.ScannerService;
import com.ea.group.four.attendancesystem.service.mapper.EventReponseToEventMapper;
import com.ea.group.four.attendancesystem.service.mapper.EventToEventReponseMapper;
import com.ea.group.four.attendancesystem.service.mapper.ScannerRecordToScannerRecordResponseMapper;
import com.ea.group.four.attendancesystem.service.response.EventResponse;
import com.ea.group.four.attendancesystem.service.response.ScanRecordResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import edu.miu.common.service.BaseReadWriteServiceImpl;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class EventServiceImpl extends
        BaseReadWriteServiceImpl<EventResponse, Event, Long> implements
        EventService {


    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private EventToEventReponseMapper revertRequestMapper;


    @Autowired
    private EventReponseToEventMapper requestMapper;

    @Autowired
    private ScannerRecordToScannerRecordResponseMapper revertScanRecordMapper;

    @Autowired
    private JMSSender jmsSender;

    @Autowired
    private ScannerService scannerService;

    @Autowired
    private ScannerRecordRepository scannerRecordRepository;

    @Override
    public EventResponse create(EventResponse request) {
        Event event = requestMapper.map(request);
        Map<String, List<String>> schedule = event.getSchedule();

        eventRepository.save(event);
        String eventMessage = convertEventAndScheduleToString(event, schedule);
        jmsSender.sendJMSMessage(eventMessage, "event.schedule.queue");

        return revertRequestMapper.map(event);
    }


    public String convertEventAndScheduleToString(Event event, Map<String, List<String>> schedule) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            String eventIdJson = objectMapper.writeValueAsString(event.getEventId());
            String scheduleJson = objectMapper.writeValueAsString(schedule);
            String eventMessage = eventIdJson + "###" + scheduleJson;
            return eventMessage;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


    @Override
    public EventResponse addMembersToEvent(Long eventId, List<Member> members) {
        Optional<Event> optionalEvent = eventRepository.findById(eventId);
        if (optionalEvent.isPresent()) {
            Event event = optionalEvent.get();
            Set<Member> existingMembers = event.getMembers();
            existingMembers.addAll(members);
            event.setMembers(existingMembers);
            event = eventRepository.save(event);
            EventResponse eventResponse = revertRequestMapper.map(event);
            return eventResponse;
        } else {
            throw new EntityNotFoundException("Event not found with id: " + eventId);
        }


    }

    @Override
    public List<ScanRecordResponse> calculateAttendanceOfEvent(Long eventId) {
        Optional<Event> optionalEvent = eventRepository.findById(eventId);
        if (optionalEvent.isPresent()) {
            Event event = optionalEvent.get();
            List<Scanner> scanners = scannerService.getEventScanners(event);
            List<ScanRecord> eventAttendance = new ArrayList<>();
            List<ScanRecordResponse> eventAttendanceResponse = new ArrayList<>();
            if (!scanners.isEmpty()) {
                for (Scanner s : scanners) {
                    eventAttendance.addAll(scannerRecordRepository.findByScanner(s));
                }
            }
            if (!eventAttendance.isEmpty()) {
                for (ScanRecord s : eventAttendance) {
                    eventAttendanceResponse.add(revertScanRecordMapper.map(s));
                }
            }

            return eventAttendanceResponse;


        } else {
            throw new EntityNotFoundException("Event not found with id: " + eventId);
        }


    }
}
