package com.ea.group.four.attendancesystem.service.impl;

import com.ea.group.four.attendancesystem.domain.ScanRecord;
import com.ea.group.four.attendancesystem.domain.Scanner;
import com.ea.group.four.attendancesystem.repository.ScannerRecordRepository;
import com.ea.group.four.attendancesystem.repository.ScannerRepository;
import com.ea.group.four.attendancesystem.service.ScannerService;
import com.ea.group.four.attendancesystem.service.mapper.ScannerResponseToScannerMapper;
import com.ea.group.four.attendancesystem.service.response.ScannerResponse;
import edu.miu.common.exception.ResourceNotFoundException;
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

    @Autowired
    ScannerResponseToScannerMapper requestMapper;

    @Autowired
    ScannerRepository scannerRepository;

    @Autowired
    ScannerRecordRepository scannerRecordRepository;

    public ScannerResponse create(ScannerResponse request) {
        Scanner scanner = requestMapper.map(request);
        Optional<Scanner> existingScanner = scannerRepository.findByEventAndLocationAndAccountType(scanner.getEvent(), scanner.getLocation(), scanner.getAccountType());

        if(existingScanner.isPresent()) {
            throw new RuntimeException("A scanner with the same Event, Location, and Account Type already exists.");
        }

        return convert(baseRepository.save(scanner));
    }

    public void delete(Long id) {
        Scanner scanner = baseRepository.findById(id).orElseThrow(ResourceNotFoundException::new);
        List<ScanRecord> scanRecords = scannerRecordRepository.findByScanner(scanner);

        if(!scanRecords.isEmpty()) {
            throw new RuntimeException("Cannot delete scanner with associated scan records.");
        }

        baseRepository.delete(scanner);
    }
}
