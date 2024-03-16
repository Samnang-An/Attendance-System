package com.ea.group.four.attendancesystem.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long eventId;
    private String name;
    private String description;
    private LocalDate startDate;
    private LocalDate endDate;
    private String occurEvent;

    @OneToMany
    private List<Session> sessions = new ArrayList<>();

    @OneToMany
    private List<Member> members = new ArrayList<>();

}


