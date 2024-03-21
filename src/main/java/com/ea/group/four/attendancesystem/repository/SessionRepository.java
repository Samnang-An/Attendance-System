package com.ea.group.four.attendancesystem.repository;

import com.ea.group.four.attendancesystem.domain.Session;
import edu.miu.common.repository.BaseRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
@Repository
public interface SessionRepository extends BaseRepository<Session, Long> {

    Session findSessionByEventEventIdAndSessionDateAndStartTimeLessThanEqualAndEndTimeGreaterThanEqual(
            Long eventId, LocalDate sessionDate, LocalTime startTime, LocalTime endTime);

    List<Session> findAllSessionsByEventEventIdAndSessionDateGreaterThanEqual(Long eventId,LocalDate currentDate);

    Session findSessionByEventEventIdAndSessionId(Long eventId,Long sessionId);

    List<Session> findAllByEventEventId(Long eventId);

}