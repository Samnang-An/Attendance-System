package com.ea.group.four.attendancesystem.repository;

import com.ea.group.four.attendancesystem.domain.AccountType;
import com.ea.group.four.attendancesystem.domain.Event;
import com.ea.group.four.attendancesystem.domain.Location;
import com.ea.group.four.attendancesystem.domain.Scanner;
import edu.miu.common.repository.BaseRepository;
import feign.Param;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ScannerRepository extends BaseRepository<Scanner, Long> {
    @Query("SELECT s FROM Scanner s WHERE s.event = :event AND s.location = :location AND s.accountType = :accountType")
    Optional<Scanner> findByEventAndLocationAndAccountType(
            @Param("event") Event event,
            @Param("location") Location location,
            @Param("accountType") AccountType accountType
    );


    List<Scanner> findAllScannersByEvent(Event event);

}
