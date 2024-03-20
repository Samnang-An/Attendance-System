package com.ea.group.four.attendancesystem.service.component;

import com.ea.group.four.attendancesystem.domain.Event;
import com.ea.group.four.attendancesystem.domain.Session;
import com.ea.group.four.attendancesystem.integration.jms.JMSSender;
import com.ea.group.four.attendancesystem.repository.EventRepository;
import com.ea.group.four.attendancesystem.repository.SessionRepository;
import com.ea.group.four.attendancesystem.service.EventService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
@Transactional
public class SessionCreateReceiverService {

    @Autowired
    private EventService eventService;

    @Autowired
    private SessionRepository sessionRepository;

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private JmsTemplate jmsTemplate;

    @JmsListener(destination = "event.schedule.queue")
    public void createSessionsFromSchedule(String payload) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        String[] parts = payload.split("###");
        Long eventId = Long.parseLong(parts[0]);
        String scheduleJson = parts[1];

        Event event = eventRepository.findByEventId(eventId);


            Map<String, List<String>> schedule = objectMapper.readValue(
                    scheduleJson,
                    new TypeReference<Map<String, List<String>>>() {});
            createSessionFromSchedule(event,schedule);

    }

    public void createSessionFromSchedule(Event event, Map<String, List<String>> schedule){
        LocalDate currentDate = event.getStartDate();
        List<Session> sessions = new ArrayList<>();
        while(!currentDate.isAfter(event.getEndDate())){

            String dayOfWeek = currentDate.getDayOfWeek().toString();
            if(schedule.containsKey(dayOfWeek.toUpperCase())){
                List<String> timeSlots = schedule.get(dayOfWeek);
                for(String timeSlot : timeSlots){
                    String[] times = timeSlot.split("-");
                    LocalTime startTime = LocalTime.parse(times[0]);
                    LocalTime endTime = LocalTime.parse(times[1]);
                    Session session = new Session();
                    session.setSessionDate(currentDate);
                    session.setStartTime(startTime);
                    session.setEndTime(endTime);
                    session.setEvent(event);
                    sessions.add(session);
                }
            }
            currentDate = currentDate.plusDays(1);
        }
        sessionRepository.saveAll(sessions);

    }

}
