package com.ea.group.four.attendancesystem.service.impl;

import com.ea.group.four.attendancesystem.domain.*;
import com.ea.group.four.attendancesystem.exception.InvalidSessionException;
import com.ea.group.four.attendancesystem.repository.MemberRepository;
import com.ea.group.four.attendancesystem.repository.ScannerRecordRepository;
import com.ea.group.four.attendancesystem.repository.ScannerRepository;
import com.ea.group.four.attendancesystem.service.MemberAccountService;
import com.ea.group.four.attendancesystem.service.ScannerRecordService;
import com.ea.group.four.attendancesystem.service.SessionService;
import com.ea.group.four.attendancesystem.service.mapper.MemberAccountResponseToMemberAccountMapper;
import com.ea.group.four.attendancesystem.service.mapper.MemberResponseToMemberMapper;
import com.ea.group.four.attendancesystem.service.mapper.ScannerRecordResponseToScannerRecordMapper;
import com.ea.group.four.attendancesystem.service.mapper.ScannerRecordToScannerRecordResponseMapper;
import com.ea.group.four.attendancesystem.service.request.ScanRecordRequest;
import com.ea.group.four.attendancesystem.service.response.MemberAccountResponse;
import com.ea.group.four.attendancesystem.service.response.ScanRecordResponse;
import com.ea.group.four.attendancesystem.service.response.SessionResponse;
import edu.miu.common.exception.ResourceNotFoundException;
import edu.miu.common.service.BaseReadWriteServiceImpl;
import lombok.extern.slf4j.Slf4j;
import ma.glasnost.orika.MapperFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

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
    MemberRepository memberRepository;

    @Autowired
    ScannerRepository scannerRepository;

    @Autowired
    MemberAccountResponseToMemberAccountMapper memberMapper;

    @Autowired
    ScannerRecordResponseToScannerRecordMapper requestMapper;

    @Autowired
    ScannerRecordToScannerRecordResponseMapper revertRequestMapper;

    @Autowired
    SessionService sessionService;

    @Autowired
    MemberAccountService memberAccountService;

    @Override
    public List<ScanRecordResponse> findByAccountBetweenDates(long accountTypeId,
                                                              String fromDateString,
                                                              String toDateString) {

        return convert(
                scannerRecordRepository.findScanRecordByScanner_AccountType_AccountTypeIdAndScannedDateBetween(
                        accountTypeId,
                        LocalDate.parse(fromDateString),
                        LocalDate.parse(toDateString)));
    }

    public ScanRecordResponse customCreate(Long scannerCode, ScanRecordRequest scanRecordRequest) {
        Optional<Scanner> optionalScanner = scannerRepository.findById(scannerCode);

        if (optionalScanner.isPresent()) {
            Scanner scanner = optionalScanner.get();
            AccountType accountType = scanner.getAccountType();
            Event event = scanner.getEvent();

            Optional<Member> optionalMember = memberRepository.findById(scanRecordRequest.getMemberId());
            if(!optionalMember.isPresent()){
                throw new RuntimeException("Member not found");
            }


            MemberAccountResponse memberAccountResponse = memberAccountService.findByMemberIdAndAccountTypeId(scanRecordRequest.getMemberId(), accountType.getAccountTypeId());
            if (Objects.isNull(memberAccountResponse)) {
                throw new InvalidSessionException("Member is not valid for this event");
            }

            SessionResponse session = sessionService.findByValidSession(event.getEventId(),
                    scanRecordRequest.getScannedDate(),
                    scanRecordRequest.getScannedTime(), scanRecordRequest.getScannedTime());

            if (Objects.isNull(session)) {
                throw new InvalidSessionException("Session not found for the event or the time is invalid");
            }

            ScanRecord record = scannerRecordRepository.findExistingRecord(
                    scanRecordRequest.getMemberId(), session.getSessionDate(),
                    session.getStartTime(), session.getEndTime());
            ScanRecord tempRecord;
            if (record == null) {
                ScanRecord newScanRecord = new ScanRecord();
                newScanRecord.setScanner(scanner);
                newScanRecord.setScannedDate(scanRecordRequest.getScannedDate());
                newScanRecord.setStatus("Check-In");
                newScanRecord.setScannedTime(scanRecordRequest.getScannedTime());
                newScanRecord.setMember(optionalMember.get());
                //take out one balance from member

                memberAccountResponse.setDefaultBalance(memberAccountResponse.getDefaultBalance() - 1);
                memberAccountService.update(memberAccountResponse.getMemberAccountId(), memberAccountResponse);
                scannerRecordRepository.save(newScanRecord);
                tempRecord = newScanRecord;
            } else {
                record.setStatus("Check-Out");
                scannerRecordRepository.save(record);
                tempRecord = record;
            }

            return convert(tempRecord);
        }else{
            throw  new RuntimeException("Scanner Not found");
        }

////    EventResponse currentEvent = scanRecordRequest.getScanner().getEvent();
//    Optional<Scanner> optionalScanner = scannerRepository.findById(scannerCode);
//    Scanner foundScanner = null;
//    if (optionalScanner.isPresent()){
//      foundScanner = optionalScanner.get();
//    }else{
//      throw new RuntimeException("Scanner not found");
//    }
//    Event event = foundScanner.getEvent();
//    //check valid member
//    MemberAccountResponse memberAccount = memberAccountService.findByMemberIdAndAccountTypeId(
//        scanRecordRequest.getMemberId(),
//            foundScanner.getAccountType().getAccountTypeId()
//    );
//    if (Objects.isNull(memberAccount)) {
//      throw new InvalidSessionException("Member is not valid for this event");
//    }
//    //check valid session
//    SessionResponse session = sessionService.findByValidSession(event.getEventId(),
//        scanRecordRequest.getScannedDate(),
//        scanRecordRequest.getScannedTime(), scanRecordRequest.getScannedTime());
//
//    if (Objects.isNull(session)) {
//      throw new InvalidSessionException("Session not found for the event or the time is invalid");
//    }
//
//    List<ScanRecord> records = scannerRecordRepository.findExistingRecord(
//        scanRecordRequest.getMemberId(), session.getSessionDate(),
//        session.getStartTime(), session.getEndTime());
//    if (records.isEmpty()) {
//      scanRecord.setStatus("Check-In");
//      //take out one balance from member
//      memberAccount.setDefaultBalance(memberAccount.getDefaultBalance() - 1);
//      memberAccountService.update(memberAccount.getMemberAccountId(), memberAccount);
//    } else {
//      scanRecord.setStatus("Check-Out");
//    }
//    return create(scanRecord);

    }

    @Override
    public ScanRecordResponse customDelete(Long recordId) {
        ScanRecord scanRecord = scannerRecordRepository.findById(recordId)
                .orElseThrow(ResourceNotFoundException::new);
        scanRecord.setDeprecated(true);

        return convert(baseRepository.save(scanRecord));
    }


}