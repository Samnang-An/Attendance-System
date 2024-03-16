package com.ea.group.four.attendancesystem.response;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
public class SessionResponse {
    private long sessionId;
    private LocalDate sessionDate;
    private LocalTime startTime;
    private LocalTime endTime;
}
