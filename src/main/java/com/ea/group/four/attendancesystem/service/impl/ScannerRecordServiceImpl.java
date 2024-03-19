package com.ea.group.four.attendancesystem.service.impl;

import com.ea.group.four.attendancesystem.domain.ScanRecord;
import com.ea.group.four.attendancesystem.repository.ScannerRecordRepository;
import com.ea.group.four.attendancesystem.service.ScannerRecordService;
import com.ea.group.four.attendancesystem.service.response.ScanRecordResponse;
import edu.miu.common.service.BaseReadWriteServiceImpl;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import ma.glasnost.orika.MapperFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ScannerRecordServiceImpl extends
    BaseReadWriteServiceImpl<ScanRecordResponse, ScanRecord, Long> implements
    ScannerRecordService {

  @Autowired
  ScannerRecordRepository scannerRecordRepository;

  @Autowired
  MapperFactory mapperFactory;


  @Override
  public List<ScanRecordResponse> findByAccountBetweenDates(String accountName,
      String fromDateString,
      String toDateString) {
    return convert(scannerRecordRepository.findByScannerAndScannedDateTimeBetween(
        accountName,
        fromDateString,
        toDateString));
  }
}