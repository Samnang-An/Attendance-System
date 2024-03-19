package com.ea.group.four.attendancesystem.service.mapper;

import com.ea.group.four.attendancesystem.domain.ScanRecord;
import com.ea.group.four.attendancesystem.domain.Scanner;
import com.ea.group.four.attendancesystem.service.response.ScanRecordResponse;
import edu.miu.common.service.mapper.BaseMapper;
import ma.glasnost.orika.MapperFactory;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalTime;

@Component
public class ScannerRecordResponseToScannerRecordMapper extends
        BaseMapper<ScanRecordResponse, ScanRecord> {

  public ScannerRecordResponseToScannerRecordMapper(MapperFactory mapperFactory) {
    super(mapperFactory, ScanRecordResponse.class, ScanRecord.class);
  }

  public ScanRecord customMap(ScanRecordResponse scanRecordResponse) {
    ScanRecord scanRecord = map(scanRecordResponse);
    scanRecord.setScannedDate(LocalDate.now());
    scanRecord.setScannedTime(LocalTime.now());
    scanRecord.setDeprecated(false);
    scanRecord.setStatus("Check In");
    return scanRecord;
  }
}