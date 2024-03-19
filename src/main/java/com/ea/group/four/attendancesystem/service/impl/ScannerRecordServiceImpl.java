package com.ea.group.four.attendancesystem.service.impl;

import com.ea.group.four.attendancesystem.domain.ScanRecord;
import com.ea.group.four.attendancesystem.exception.InvalidSessionException;
import com.ea.group.four.attendancesystem.repository.ScannerRecordRepository;
import com.ea.group.four.attendancesystem.service.MemberAccountService;
import com.ea.group.four.attendancesystem.service.ScannerRecordService;
import com.ea.group.four.attendancesystem.service.SessionService;
import com.ea.group.four.attendancesystem.service.mapper.ScannerRecordResponseToScannerRecordMapper;
import com.ea.group.four.attendancesystem.service.mapper.ScannerRecordToScannerRecordResponseMapper;
import com.ea.group.four.attendancesystem.service.response.EventResponse;
import com.ea.group.four.attendancesystem.service.response.MemberAccountResponse;
import com.ea.group.four.attendancesystem.service.response.ScanRecordResponse;
import com.ea.group.four.attendancesystem.service.response.SessionResponse;
import edu.miu.common.exception.ResourceNotFoundException;
import edu.miu.common.service.BaseReadWriteServiceImpl;
import java.util.List;
import java.util.Objects;
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

  @Autowired
  SessionService sessionService;

  @Autowired
  MemberAccountService memberAccountService;

  @Override
  public List<ScanRecordResponse> findByAccountBetweenDates(String accountName,
                                                            String fromDateString,
                                                            String toDateString) {
    return convert(scannerRecordRepository.findByScannerAndScannedDateTimeBetween(
            accountName,
            fromDateString,
            toDateString));
  }

  public ScanRecordResponse create(ScanRecordResponse scanRecord) {
    EventResponse currentEvent = scanRecord.getScanner().getEvent();
    //check valid member
    MemberAccountResponse memberAccount = memberAccountService.findByMemberIdAndAccountTypeId(
            scanRecord.getMember().getMemberId(),
            scanRecord.getScanner().getAccountType().getAccountTypeId()
    );
    if (Objects.isNull(memberAccount)) {
      throw new InvalidSessionException("Member is not valid for this event");
    }
    //check valid session
    SessionResponse session = sessionService.findByValidSession(currentEvent.getEventId(),
            scanRecord.getScannedDate(),
            scanRecord.getScannedTime(),
            scanRecord.getScannedTime());

    if (Objects.isNull(session)) {
      throw new InvalidSessionException("Session not found for the event or the time is invalid");
    }

    List<ScanRecord> records = scannerRecordRepository.findExistingRecord(
            scanRecord.getMember().getMemberId(), session.getSessionDate(),
            session.getStartTime(), session.getEndTime());
    if (records.isEmpty()) {
      scanRecord.setStatus("Check-In");
      //take out one balance from member
      memberAccount.setDefaultBalance(memberAccount.getDefaultBalance() - 1);
      memberAccountService.update(memberAccount.getMemberAccountId(), memberAccount);
    } else {
      scanRecord.setStatus("Check-Out");
    }
    return create(scanRecord);

  }

  @Override
  public ScanRecordResponse customDelete(Long recordId) {
    ScanRecord scanRecord = scannerRecordRepository.findById(recordId)
            .orElseThrow(ResourceNotFoundException::new);
    scanRecord.setDeprecated(true);

    return convert(baseRepository.save(scanRecord));
  }


}