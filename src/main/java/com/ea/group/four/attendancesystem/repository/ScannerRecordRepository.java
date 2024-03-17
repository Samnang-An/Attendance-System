package com.ea.group.four.attendancesystem.repository;

import com.ea.group.four.attendancesystem.domain.ScanRecord;
import com.ea.group.four.attendancesystem.domain.Scanner;
import edu.miu.common.repository.BaseRepository;

import java.util.List;

public interface ScannerRecordRepository extends BaseRepository<ScanRecord, Long> {
    public List<ScanRecord> findByScanner(Scanner scanner);
}
