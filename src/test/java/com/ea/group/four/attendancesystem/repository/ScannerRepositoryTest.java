package com.ea.group.four.attendancesystem.repository;

import com.ea.group.four.attendancesystem.domain.AccountType;
import com.ea.group.four.attendancesystem.domain.Event;
import com.ea.group.four.attendancesystem.domain.Location;
import com.ea.group.four.attendancesystem.domain.LocationType;
import com.ea.group.four.attendancesystem.domain.Scanner;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.HashSet;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ContextConfiguration;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ContextConfiguration(classes = {ScannerRepository.class})
@EnableAutoConfiguration
@EntityScan(basePackages = {"com.ea.group.four.attendancesystem.domain"})
@DataJpaTest
class ScannerRepositoryTest {
    @Autowired
    private ScannerRepository scannerRepository;
    

    @Autowired
    private TestEntityManager testEntityManager;

    @Test
    void testFindByEventAndLocationAndAccountType() {

        AccountType accountType = new AccountType();
        accountType.setDefaultBalance(1);
        accountType.setName("Name");

        Event event = new Event();
        event.setDescription("The characteristics of someone or something");
        event.setEndDate(LocalDate.of(1970, 1, 1));
        event.setMembers(new HashSet<>());
        event.setName("Name");
        event.setSchedule(new HashMap<>());
        event.setStartDate(LocalDate.of(1970, 1, 1));

        Location location = new Location();
        location.setName("Name");
        location.setType(LocationType.Dining);

        Scanner scanner = new Scanner();
        scanner.setAccountType(accountType);
        scanner.setEvent(event);
        scanner.setLocation(location);


        testEntityManager.persistAndFlush(accountType);
        testEntityManager.persistAndFlush(location);
        testEntityManager.persistAndFlush(event);
        testEntityManager.persistAndFlush(scanner);

        Scanner testScanner = scannerRepository.findByEventAndLocationAndAccountType(event, location, accountType).orElse(null);

        assertEquals(scanner, testScanner);
    }
}
