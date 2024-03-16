package com.ea.group.four.attendancesystem.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Scanner {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private  long scannerId;
    @ManyToOne
    private AccountType accountType;
    @ManyToOne
    private  Location location;
    @OneToOne
    private Event event;
}
