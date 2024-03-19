package com.ea.group.four.attendancesystem.repository;

import com.ea.group.four.attendancesystem.domain.Member;
import com.ea.group.four.attendancesystem.domain.ScanRecord;
import com.ea.group.four.attendancesystem.domain.Scanner;
import edu.miu.common.repository.BaseRepository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

public interface ScannerRecordRepository extends BaseRepository<ScanRecord, Long> {

  List<ScanRecord> findByScanner(Scanner scanner);

  List<ScanRecord> findByMember(Member member);

  @Query("select s from ScanRecord s where s.scanner.accountType.name like :accountType and s.scannedDate between :fromDate and :toDate")
  List<ScanRecord> findByScannerAndScannedDateTimeBetween(
      @Param("accountType") String accountType,
      @Param("fromDate") String fromDate,
      @Param("toDate") String toDate);



  @Query(
          "SELECT s FROM ScanRecord s WHERE s.scannedDate = :date " +
                  "AND s.scannedTime BETWEEN :start_time AND :end_time " +
                  "AND s.member.memberId = :memberId"
  )
  public List<ScanRecord> findExistingRecord(@Param("memberId") long memberId,
                                                 @Param("date") LocalDate date,
                                                 @Param("start_time") LocalTime start_time,
                                                 @Param("end_time") LocalTime end_time);

  @Query("select s from ScanRecord s join fetch s.member join fetch s.member.roles where s.member.memberId = :memberId and s.scanner.event.eventId = :eventId")
  @Transactional(propagation = Propagation.NOT_SUPPORTED)
  List<ScanRecord> findByMemberAndEvent(@Param("memberId") Long memberId, @Param("eventId") Long eventId);



}
