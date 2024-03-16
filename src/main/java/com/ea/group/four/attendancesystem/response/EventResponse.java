package com.ea.group.four.attendancesystem.response;

import com.ea.group.four.attendancesystem.domain.Member;
import com.ea.group.four.attendancesystem.domain.Session;
import jakarta.persistence.OneToMany;
import lombok.Data;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
public class EventResponse {
    private long eventId;
    private String name;
    private String description;
    private LocalDate startDate;
    private LocalDate endDate;
    private String occurEvent;

    private List<Session> sessions = new ArrayList<>();
    private List<Member> members = new ArrayList<>();
}
