package com.ea.group.four.attendancesystem.service.impl;

import com.ea.group.four.attendancesystem.domain.Event;
import com.ea.group.four.attendancesystem.domain.Session;
import com.ea.group.four.attendancesystem.exception.InvalidSessionException;
import com.ea.group.four.attendancesystem.repository.SessionRepository;
import com.ea.group.four.attendancesystem.service.mapper.SessionResponseToSessionMapper;
import com.ea.group.four.attendancesystem.service.mapper.SessionToSessionResponseMapper;
import com.ea.group.four.attendancesystem.service.response.SessionResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
public class SessionServiceImplTest {
    @Mock
    SessionToSessionResponseMapper sessionToSessionResponseMapper;

    @Mock
    SessionResponseToSessionMapper sessionResponseToSessionMapper;

    @Mock
    private SessionRepository sessionRepository;

    @InjectMocks
    private SessionServiceImpl sessionService;

    private Event event;
    private SessionResponse sessionResponse;
    private Session session;

    @BeforeEach
    public void setup() {
        sessionService = new SessionServiceImpl(sessionToSessionResponseMapper, sessionResponseToSessionMapper, sessionRepository);

        event = new Event();
        event.setEventId(1L);
        event.setName("Enterprise Architecture");
        event.setDescription(" EA taught by Prof Payman Salek");
        event.setStartDate(LocalDate.of(2024, 3, 1));
        event.setEndDate(LocalDate.of(2024, 6, 1));
        event.setSchedule(new HashMap<>());

        sessionResponse = new SessionResponse();
        sessionResponse.setSessionId(1L);
        sessionResponse.setSessionDate(LocalDate.now());
        sessionResponse.setStartTime(LocalTime.of(10, 0));
        sessionResponse.setEndTime(LocalTime.of(12, 30));
        sessionResponse.setEventId(event.getEventId());

        session = new Session();
        session.setSessionId(1L);
        session.setSessionDate(LocalDate.now());
        session.setStartTime(LocalTime.of(10, 0));
        session.setEndTime(LocalTime.of(12, 30));
        session.setEvent(event);

    }

    @Test
    public void addSession_ValidSessionTest() {

        when(sessionRepository.save(any(Session.class)))
                .thenReturn(session);
        when(sessionToSessionResponseMapper.map(any(Session.class)))
                .thenReturn(sessionResponse);
        when(sessionResponseToSessionMapper.map(any(SessionResponse.class)))
                .thenReturn(session);

        SessionResponse sessionResponseResult = sessionService.addSession(event, sessionResponse);

        assertNotNull(sessionResponseResult);
        assertEquals(sessionResponse, sessionResponseResult);

    }

    @Test
    public void addSessionInvalidSessionTest() {
        event.setStartDate(LocalDate.of(2024, 5, 1));
        event.setEndDate(LocalDate.of(2024, 5, 1));

        sessionResponse.setSessionDate(LocalDate.of(2023, 5, 15));

        assertThrows(InvalidSessionException.class, () -> sessionService.addSession(event, sessionResponse));


    }

    @Test
    public void findByValidSession_SessionExistTest() {
        when(sessionRepository
                .findSessionByEventEventIdAndSessionDateAndStartTimeLessThanEqualAndEndTimeGreaterThanEqual(
                        any(), any(), any(), any()))
                .thenReturn(session);
        when(sessionToSessionResponseMapper.map(any(Session.class)))
                .thenReturn(sessionResponse);

        SessionResponse sessionResponseResult = sessionService.findByValidSession(
                1L, LocalDate.now(), LocalTime.of(10, 0), LocalTime.of(12, 30));

        assertNotNull(sessionResponseResult);
        assertEquals(sessionResponse, sessionResponseResult);

    }

    @Test
    public void testUpdateSession_ValidSessionTest() {
        when(sessionRepository.findSessionByEventEventIdAndSessionId(any(), any())).thenReturn(session);
        session.setEvent(event);
        when(sessionRepository.save(any(Session.class))).thenReturn(session);
        when(sessionResponseToSessionMapper.map(any(SessionResponse.class))).thenReturn(session);
        when(sessionToSessionResponseMapper.map(any(Session.class))).thenReturn(sessionResponse);

        SessionResponse updatedSessionResponse = sessionService.updateSession(event, sessionResponse);

        verify(sessionRepository, times(1)).findSessionByEventEventIdAndSessionId(eq(event.getEventId()), eq(sessionResponse.getSessionId()));
        verify(sessionRepository, times(1)).save(any(Session.class));
        verify(sessionToSessionResponseMapper, times(1)).map(any(Session.class));
        assertNotNull(updatedSessionResponse);
        assertEquals(event.getEventId(), updatedSessionResponse.getEventId());
    }

    @Test
    public void testUpdateSession_InvalidSessionTest() {
        when(sessionRepository.findSessionByEventEventIdAndSessionId(anyLong(), anyLong())).thenReturn(null);
        assertThrows(InvalidSessionException.class, () -> {
            sessionService.updateSession(event, sessionResponse);
        });
    }

    @Test
    public void deleteSessionTest() {
        when(sessionResponseToSessionMapper.map(sessionResponse))
                .thenReturn(session);
        sessionService.deleteSession(sessionResponse);
        verify(sessionRepository).delete(session);
    }

}
