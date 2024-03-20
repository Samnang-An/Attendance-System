package com.ea.group.four.attendancesystem.repository;

import com.ea.group.four.attendancesystem.domain.Session;
import com.ea.group.four.attendancesystem.service.response.SessionResponse;
import edu.miu.common.repository.BaseRepository;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public interface SessionRepository extends BaseRepository<Session, Long> {

    SessionResponse findSessionByEventEventIdAndSessionDateAndStartTimeLessThanEqualAndEndTimeGreaterThanEqual(
            Long eventId, LocalDate sessionDate, LocalTime startTime, LocalTime endTime);

    List<Session> findAllSessionsByEventEventIdAndSessionDateGreaterThanEqual(Long eventId,LocalDate currentDate);

}