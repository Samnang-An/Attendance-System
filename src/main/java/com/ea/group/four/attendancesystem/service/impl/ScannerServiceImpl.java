package com.ea.group.four.attendancesystem.service.impl;

import com.ea.group.four.attendancesystem.domain.Event;
import com.ea.group.four.attendancesystem.domain.ScanRecord;
import com.ea.group.four.attendancesystem.domain.Scanner;
import com.ea.group.four.attendancesystem.repository.ScannerRecordRepository;
import com.ea.group.four.attendancesystem.repository.ScannerRepository;
import com.ea.group.four.attendancesystem.service.ScannerService;
import com.ea.group.four.attendancesystem.service.mapper.ScannerResponseToScannerMapper;
import com.ea.group.four.attendancesystem.service.mapper.ScannerToScannerResponseMapper;
import com.ea.group.four.attendancesystem.service.response.ScannerResponse;
import edu.miu.common.exception.ResourceNotFoundException;
import edu.miu.common.repository.BaseRepository;
import edu.miu.common.service.BaseReadWriteServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ScannerServiceImpl extends
    BaseReadWriteServiceImpl<ScannerResponse, Scanner, Long> implements
    ScannerService {

    public ScannerServiceImpl() {
    }

    public ScannerServiceImpl(ScannerRepository scannerRepository, ScannerRecordRepository scannerRecordRepository,
                              ScannerResponseToScannerMapper requestMapper, ScannerToScannerResponseMapper revertRequestMapper) {
        this.scannerRepository = scannerRepository;
        this.scannerRecordRepository = scannerRecordRepository;
        this.requestMapper = requestMapper;
        this.revertRequestMapper = revertRequestMapper;
    }

    @Autowired
    ScannerResponseToScannerMapper requestMapper;

    @Autowired
    ScannerToScannerResponseMapper revertRequestMapper;

    @Autowired
    ScannerRepository scannerRepository;

    @Autowired
    ScannerRecordRepository scannerRecordRepository;

    @Override
    public ScannerResponse create(ScannerResponse request) {
        Scanner scanner = requestMapper.map(request);
        Optional<Scanner> existingScanner = scannerRepository.findByEventAndLocationAndAccountType(scanner.getEvent(), scanner.getLocation(), scanner.getAccountType());

        if(existingScanner.isPresent()) {
            return revertRequestMapper.map(existingScanner.get());
        }

        return convert(scannerRepository.save(scanner));
    }

    @Override
    public void delete(Long id) {
        Scanner scanner = scannerRepository.findById(id).orElseThrow(ResourceNotFoundException::new);
        List<ScanRecord> scanRecords = scannerRecordRepository.findByScanner(scanner);

        if(!scanRecords.isEmpty()) {
            throw new RuntimeException("Cannot delete scanner with associated scan records.");
        }

        scannerRepository.delete(scanner);
    }

    @Override
    public ScannerResponse convert(Scanner entity) {
        if(null == entity) {
            return null;
        }
        else {
            return revertRequestMapper.map(entity);
        }
    }




    @Override
    public List<Scanner> getEventScanners(Event event) {
        return scannerRepository.findAllScannersByEvent(event);
    }
}
