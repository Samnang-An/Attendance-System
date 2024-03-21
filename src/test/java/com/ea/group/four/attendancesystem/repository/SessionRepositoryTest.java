package com.ea.group.four.attendancesystem.repository;

import com.ea.group.four.attendancesystem.domain.Event;
import com.ea.group.four.attendancesystem.domain.Session;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ContextConfiguration;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ContextConfiguration(classes = {SessionRepository.class})
@EnableAutoConfiguration
@EntityScan(basePackages = {"com.ea.group.four.attendancesystem.domain"})
@DataJpaTest
public class SessionRepositoryTest {
    @Autowired
    private SessionRepository sessionRepository;

    @Autowired
    private TestEntityManager testEntityManager;

    @Test
    public void findSessionByEventIdAndSessionIDAndTimeTest() {
        Event event = new Event();
        event.setName("Enterprise Architecture");
        event.setDescription(" EA taught by Prof Payman Salek");
        event.setStartDate(LocalDate.of(2024, 5, 1));
        event.setEndDate(LocalDate.of(2024, 6, 1));
        event.setSchedule(new HashMap<>());

        Session sessionExpected = new Session();
        sessionExpected.setSessionDate(LocalDate.now());
        sessionExpected.setStartTime(LocalTime.of(10, 0));
        sessionExpected.setEndTime(LocalTime.of(12, 30));
        sessionExpected.setEvent(event);

        testEntityManager.persistAndFlush(event);
        testEntityManager.persistAndFlush(sessionExpected);

        Session sessionFound = sessionRepository.findSessionByEventEventIdAndSessionDateAndStartTimeLessThanEqualAndEndTimeGreaterThanEqual(
                event.getEventId(),
                LocalDate.now(),
                LocalTime.of(10, 0),
                LocalTime.of(12, 30)
        );
        assertEquals(sessionExpected, sessionFound);

    }

    @Test
    public void findAllSessionsByEventEventIdAndSessionDateGreaterThanEqualTest() {
        Event event = new Event();
        event.setName("Enterprise Architecture");
        event.setDescription(" EA taught by Prof Payman Salek");
        event.setStartDate(LocalDate.of(2024, 5, 1));
        event.setEndDate(LocalDate.of(2024, 6, 1));
        event.setSchedule(new HashMap<>());

        LocalDate currentDate = LocalDate.now();

        Session session1 = new Session();
        session1.setSessionDate(currentDate.plusDays(1));
        session1.setStartTime(LocalTime.of(10, 0));
        session1.setEndTime(LocalTime.of(12, 0));
        session1.setEvent(event);

        Session session2 = new Session();
        session2.setSessionDate(currentDate.plusDays(2));
        session2.setStartTime(LocalTime.of(13, 0));
        session2.setEndTime(LocalTime.of(15, 0));
        session2.setEvent(event);

        testEntityManager.persistAndFlush(event);
        testEntityManager.persistAndFlush(session1);
        testEntityManager.persistAndFlush(session2);

        List<Session> sessions = List.of(session1, session2);

        List<Session> foundSessions = sessionRepository.findAllSessionsByEventEventIdAndSessionDateGreaterThanEqual(event.getEventId(), currentDate);

        assertEquals(sessions.size(), foundSessions.size());

    }

    @Test
    public void findByEvent_EventIdAndSessionIdTest() {
        Event event = new Event();
        event.setName("Enterprise Architecture");
        event.setDescription(" EA taught by Prof Payman Salek");
        event.setStartDate(LocalDate.of(2024, 5, 1));
        event.setEndDate(LocalDate.of(2024, 6, 1));
        event.setSchedule(new HashMap<>());

        Session sessionExpected = new Session();
        sessionExpected.setSessionDate(LocalDate.now());
        sessionExpected.setStartTime(LocalTime.of(10, 0));
        sessionExpected.setEndTime(LocalTime.of(12, 30));
        sessionExpected.setEvent(event);

        testEntityManager.persistAndFlush(event);
        testEntityManager.persistAndFlush(sessionExpected);

        Session sessionFound = sessionRepository.findSessionByEventEventIdAndSessionId(event.getEventId(), sessionExpected.getSessionId());
        assertEquals(sessionExpected, sessionFound);
    }

}
