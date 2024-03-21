package com.ea.group.four.attendancesystem.repository;

import static org.assertj.core.api.Assertions.assertThat;

import com.ea.group.four.attendancesystem.domain.Account;
import com.ea.group.four.attendancesystem.domain.AccountType;
import com.ea.group.four.attendancesystem.domain.Event;
import com.ea.group.four.attendancesystem.domain.Location;
import com.ea.group.four.attendancesystem.domain.LocationType;
import com.ea.group.four.attendancesystem.domain.Member;
import com.ea.group.four.attendancesystem.domain.MemberAccount;
import com.ea.group.four.attendancesystem.domain.Role;
import com.ea.group.four.attendancesystem.domain.ScanRecord;
import com.ea.group.four.attendancesystem.domain.Scanner;
import com.ea.group.four.attendancesystem.repository.ScannerRecordRepository;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@DataJpaTest
public class ScanRecordRepositoryTests {

  @Autowired
  private TestEntityManager entityManager;
  @Autowired
  private ScannerRecordRepository scannerRecordRepository;

  @Test
  public void whenFindByMemberReturnAllScannerRecordsRelateToMember() {
    // given
    AccountType accountType = new AccountType(0, "Student", 100);
    entityManager.persistAndFlush(accountType);
    Account account = new Account(0, "Student", "Student Account", accountType);
    entityManager.persistAndFlush(account);
    Role studentRole = new Role(0, "Student", List.of(account));
    entityManager.persistAndFlush(studentRole);
    List<Role> roles = List.of(studentRole);
    Member member = new Member(0, "Samnang", "An", "12345", "sam@email.com", roles,
        Collections.EMPTY_LIST);
    entityManager.persistAndFlush(member);
    MemberAccount accMember = new MemberAccount(0, member, account, 100, true);
    entityManager.persistAndFlush(accMember);
    //Location
    Location location = new Location(0, "MUM", LocationType.Classroom);
    entityManager.persistAndFlush(location);
    //Event
    Event event = new Event(0, "EA", "Class EA", LocalDate.now().minusMonths(1),
        LocalDate.now().plusMonths(1), new HashMap<String, List<String>>(), "", Set.of(member));
    entityManager.persistAndFlush(event);
    //scanner
    Scanner scanner = new Scanner(0, accountType, location, event);
    entityManager.persistAndFlush(scanner);

    ScanRecord scanRecord = new ScanRecord(0, scanner, member, LocalDate.now(), LocalTime.now(),
        "Checkin", false);
    entityManager.persistAndFlush(scanRecord);
    ScanRecord scanRecord1 = new ScanRecord(0, scanner, member, LocalDate.now().minusDays(2),
        LocalTime.now(),
        "Checkin", false);
    entityManager.persistAndFlush(scanRecord1);
    ScanRecord scanRecord2 = new ScanRecord(0, scanner, member, LocalDate.now().minusDays(1),
        LocalTime.now(),
        "Checkin", false);
    entityManager.persistAndFlush(scanRecord2);
    // when
    List<ScanRecord> found = scannerRecordRepository.findByMember(member);
    // then
    assertThat(found.size()).isEqualTo(3);
    assertThat(found.get(0).getMember().getMemberId())
        .isEqualTo(member.getMemberId());
    assertThat(found.get(1).getMember().getMemberId())
        .isEqualTo(member.getMemberId());
    assertThat(found.get(2).getMember().getMemberId())
        .isEqualTo(member.getMemberId());
  }

  @Test
  public void whenFindByScannerReturnAllScannerRecords() {
    // given
    AccountType accountType = new AccountType(0, "Student", 100);
    entityManager.persistAndFlush(accountType);
    Account account = new Account(0, "Student", "Student Account", accountType);
    entityManager.persistAndFlush(account);
    Role studentRole = new Role(0, "Student", List.of(account));
    entityManager.persistAndFlush(studentRole);
    List<Role> roles = List.of(studentRole);
    Member member = new Member(0, "Samnang", "An", "12345", "sam@email.com", roles,
        Collections.EMPTY_LIST);
    entityManager.persistAndFlush(member);
    MemberAccount accMember = new MemberAccount(0, member, account, 100, true);
    entityManager.persistAndFlush(accMember);
    //Location
    Location location = new Location(0, "MUM", LocationType.Classroom);
    entityManager.persistAndFlush(location);
    //Event
    Event event = new Event(0, "EA", "Class EA", LocalDate.now().minusMonths(1),
        LocalDate.now().plusMonths(1), new HashMap<String, List<String>>(), "", Set.of(member));
    entityManager.persistAndFlush(event);
    //scanner
    Scanner scanner = new Scanner(0, accountType, location, event);
    entityManager.persistAndFlush(scanner);

    ScanRecord scanRecord = new ScanRecord(0, scanner, member, LocalDate.now(), LocalTime.now(),
        "Checkin", false);
    entityManager.persistAndFlush(scanRecord);
    ScanRecord scanRecord1 = new ScanRecord(0, scanner, member, LocalDate.now().minusDays(2),
        LocalTime.now(),
        "Checkin", false);
    entityManager.persistAndFlush(scanRecord1);
    ScanRecord scanRecord2 = new ScanRecord(0, scanner, member, LocalDate.now().minusDays(1),
        LocalTime.now(),
        "Checkin", false);
    entityManager.persistAndFlush(scanRecord2);
    // when
    List<ScanRecord> found = scannerRecordRepository.findByScanner(scanner);
    // then
    assertThat(found.size()).isEqualTo(3);
    assertThat(found.get(0).getScanner().getScannerId())
        .isEqualTo(scanner.getScannerId());
    assertThat(found.get(1).getScanner().getScannerId())
        .isEqualTo(scanner.getScannerId());
    assertThat(found.get(2).getScanner().getScannerId())
        .isEqualTo(scanner.getScannerId());
  }

  @Test
  public void whenFindScanRecordByScanner_AccountType_AccountTypeIdAndScannedDateBetweenReturnListOfScanner() {
    // given
    AccountType accountType = new AccountType(0, "Student", 100);
    entityManager.persistAndFlush(accountType);
    Account account = new Account(0, "Student", "Student Account", accountType);
    entityManager.persistAndFlush(account);
    Role studentRole = new Role(0, "Student", List.of(account));
    entityManager.persistAndFlush(studentRole);
    List<Role> roles = List.of(studentRole);
    Member member = new Member(0, "Samnang", "An", "12345", "sam@email.com", roles,
        Collections.EMPTY_LIST);
    entityManager.persistAndFlush(member);
    MemberAccount accMember = new MemberAccount(0, member, account, 100, true);
    entityManager.persistAndFlush(accMember);
    //Location
    Location location = new Location(0, "MUM", LocationType.Classroom);
    entityManager.persistAndFlush(location);
    //Event
    Event event = new Event(0, "EA", "Class EA", LocalDate.now().minusMonths(1),
        LocalDate.now().plusMonths(1), new HashMap<String, List<String>>(), "", Set.of(member));
    entityManager.persistAndFlush(event);
    //scanner
    Scanner scanner = new Scanner(0, accountType, location, event);
    entityManager.persistAndFlush(scanner);

    ScanRecord scanRecord = new ScanRecord(0, scanner, member, LocalDate.now(), LocalTime.now(),
        "Checkin", false);
    entityManager.persistAndFlush(scanRecord);
    ScanRecord scanRecord1 = new ScanRecord(0, scanner, member, LocalDate.now().minusDays(2),
        LocalTime.now(),
        "Checkin", false);
    entityManager.persistAndFlush(scanRecord1);
    ScanRecord scanRecord2 = new ScanRecord(0, scanner, member, LocalDate.now().minusDays(1),
        LocalTime.now(),
        "Checkin", false);
    entityManager.persistAndFlush(scanRecord2);
    // when
    List<ScanRecord> found = scannerRecordRepository.findScanRecordByScanner_AccountType_AccountTypeIdAndScannedDateBetween(
        1, LocalDate.now().minusDays(3), LocalDate.now());
    // then
    assertThat(found.size()).isEqualTo(3);
    assertThat(found.get(0).getMember().getMemberId())
        .isEqualTo(member.getMemberId());
    assertThat(found.get(1).getMember().getMemberId())
        .isEqualTo(member.getMemberId());
    assertThat(found.get(2).getMember().getMemberId())
        .isEqualTo(member.getMemberId());
  }

}
