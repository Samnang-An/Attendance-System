package com.ea.group.four.attendancesystem.response;

import com.ea.group.four.attendancesystem.domain.AccountType;
import com.ea.group.four.attendancesystem.domain.Event;
import com.ea.group.four.attendancesystem.domain.Location;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import lombok.Data;

@Data
public class ScannerResponse {
    private  long scannerId;
    private AccountType accountType;
    private Location location;
    private Event event;
}
