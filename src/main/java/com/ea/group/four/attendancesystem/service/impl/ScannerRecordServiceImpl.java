package com.ea.group.four.attendancesystem.service.impl;

import com.ea.group.four.attendancesystem.domain.*;
import com.ea.group.four.attendancesystem.repository.ScannerRecordRepository;
import com.ea.group.four.attendancesystem.service.ScannerRecordService;
import com.ea.group.four.attendancesystem.service.mapper.ScannerRecordResponseToScannerRecordMapper;
import com.ea.group.four.attendancesystem.service.mapper.ScannerRecordToScannerRecordResponseMapper;
import com.ea.group.four.attendancesystem.service.response.ScanRecordResponse;
import edu.miu.common.exception.ResourceNotFoundException;
import edu.miu.common.service.BaseReadWriteServiceImpl;
import java.util.List;
import java.util.Optional;

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

  @Autowired
  ScannerRecordResponseToScannerRecordMapper requestMapper;

  @Autowired
  ScannerRecordToScannerRecordResponseMapper revertRequestMapper;

  @Override
  public List<ScanRecordResponse> findByAccountBetweenDates(String accountName,
      String fromDateString,
      String toDateString) {
    return convert(scannerRecordRepository.findByScannerAndScannedDateTimeBetween(
        accountName,
        fromDateString, toDateString));
  }

  public ScanRecordResponse create(ScanRecordResponse request) {
    ScanRecord scanRecord = requestMapper.customMap(request);
    Scanner scanner = scanRecord.getScanner();
    Event currentEvent = scanner.getEvent();
    Session session = currentEvent.getSessions().stream()
            .filter(s -> s.getSessionDate().isEqual(scanRecord.getScannedDate()))
            .filter(s -> s.getStartTime().compareTo(scanRecord.getScannedTime()) <= 0)
            .filter(s -> s.getEndTime().compareTo(scanRecord.getScannedTime()) >= 0)
            .findFirst().orElse(null);
    Optional<ScanRecord> existingRecord = scannerRecordRepository.findExistingRecord(
            scanRecord.getMember(), session.getSessionDate(),
            session.getStartTime(), session.getEndTime());

    // When a record is existed in the session of the event
    if(!existingRecord.isPresent()) {
      // If already checked out -> return existingRecord
      if(existingRecord.get().getStatus().equals("Check Out")) {
        return revertRequestMapper.map(existingRecord.get());
      }
      // If status is check in -> update status of existing record to checkout
      existingRecord.get().setStatus("Check Out");
      existingRecord.get().setScannedTime(scanRecord.getScannedTime());
      return convert(baseRepository.save(existingRecord.get()));
    }
    return convert(baseRepository.save(scanRecord));
  }

  @Override
  public ScanRecordResponse customDelete(Long recordId){
    ScanRecord scanRecord = scannerRecordRepository.findById(recordId).orElseThrow(ResourceNotFoundException::new);
    scanRecord.setDeprecated(true);

    return convert(baseRepository.save(scanRecord));
  }


}