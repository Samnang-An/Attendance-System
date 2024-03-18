package com.ea.group.four.attendancesystem.repository;

import com.ea.group.four.attendancesystem.domain.Member;
import com.ea.group.four.attendancesystem.domain.ScanRecord;
import com.ea.group.four.attendancesystem.domain.Scanner;
import edu.miu.common.repository.BaseRepository;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ScannerRecordRepository extends BaseRepository<ScanRecord, Long> {

  List<ScanRecord> findByScanner(Scanner scanner);

  List<ScanRecord> findByMember(Member member);

  @Query("select s from ScanRecord s where s.scanner.accountType.name like :accountType and s.scannedDateTime between :fromDate and :toDate")
  List<ScanRecord> findByScannerAndScannedDateTimeBetween(
      @Param("accountType") String accountType,
      @Param("fromDate") String fromDate,
      @Param("toDate") String toDate);

}
