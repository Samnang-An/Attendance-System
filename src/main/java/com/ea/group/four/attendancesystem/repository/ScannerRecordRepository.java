package com.ea.group.four.attendancesystem.repository;

import com.ea.group.four.attendancesystem.domain.Member;
import com.ea.group.four.attendancesystem.domain.ScanRecord;
import com.ea.group.four.attendancesystem.domain.Scanner;
import edu.miu.common.repository.BaseRepository;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
@Repository
public interface ScannerRecordRepository extends BaseRepository<ScanRecord, Long> {

  List<ScanRecord> findByScanner(Scanner scanner);

  List<ScanRecord> findByMember(Member member);

  List<ScanRecord> findScanRecordByScanner_AccountType_AccountTypeIdAndScannedDateBetween(
      long accountTypeId, LocalDate fromDate, LocalDate toDate);

  @Query(
          "SELECT s FROM ScanRecord s WHERE s.scannedDate = :date " +
                  "AND s.scannedTime BETWEEN :start_time AND :end_time " +
                  "AND s.member.memberId = :memberId"
  )
  ScanRecord findExistingRecord(@Param("memberId") long memberId,
                                                 @Param("date") LocalDate date,
                                                 @Param("start_time") LocalTime start_time,
                                                 @Param("end_time") LocalTime end_time);

  @Query("select s from ScanRecord s join fetch s.member join fetch s.member.roles where s.member.memberId = :memberId and s.scanner.event.eventId = :eventId")
  @Transactional(propagation = Propagation.NOT_SUPPORTED)
  List<ScanRecord> findByMemberAndEvent(@Param("memberId") Long memberId, @Param("eventId") Long eventId);

}
