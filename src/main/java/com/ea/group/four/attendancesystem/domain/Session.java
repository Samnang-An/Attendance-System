package com.ea.group.four.attendancesystem.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Session {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long sessionId;

    private LocalDate sessionDate;
    private LocalTime startTime;
    private LocalTime endTime;
}
