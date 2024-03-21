package com.ea.group.four.attendancesystem.service.impl;

import com.ea.group.four.attendancesystem.domain.Location;
import com.ea.group.four.attendancesystem.domain.LocationType;
import com.ea.group.four.attendancesystem.domain.Scanner;
import com.ea.group.four.attendancesystem.domain.ScanRecord;
import com.ea.group.four.attendancesystem.repository.ScannerRecordRepository;
import com.ea.group.four.attendancesystem.repository.ScannerRepository;
import com.ea.group.four.attendancesystem.service.mapper.ScannerResponseToScannerMapper;
import com.ea.group.four.attendancesystem.service.mapper.ScannerToScannerResponseMapper;
import com.ea.group.four.attendancesystem.service.response.LocationResponse;
import com.ea.group.four.attendancesystem.service.response.ScannerResponse;
import edu.miu.common.exception.ResourceNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ScannerServiceImplTest {

    @Mock
    ScannerRepository scannerRepository;

    @Mock
    ScannerRecordRepository scannerRecordRepository;

    @Mock
    ScannerResponseToScannerMapper requestMapper;

    @Mock
    ScannerToScannerResponseMapper revertRequestMapper;

    @InjectMocks
    ScannerServiceImpl scannerService;

    @BeforeEach
    void setUp() {
        scannerService = new ScannerServiceImpl(scannerRepository, scannerRecordRepository, requestMapper, revertRequestMapper);
    }

    @Test
    void createExistingScanner_Success() {
        ScannerResponse scannerResponse = new ScannerResponse();
        scannerResponse.setScannerId(1L);
        LocationResponse locationResponse = new LocationResponse();
        locationResponse.setType("Dining");
        locationResponse.setName("Argiro");
        locationResponse.setLocationId(1L);
        scannerResponse.setLocation(locationResponse);


        Scanner scanner = new Scanner();
        scanner.setScannerId(1L);
        scanner.setLocation(new Location(1L, "Argiro", LocationType.Dining));

        when(requestMapper.map(any())).thenReturn(scanner);
        when(scannerRepository.findByEventAndLocationAndAccountType(any(), any(), any())).thenReturn(Optional.empty());
        when(scannerRepository.save(any())).thenReturn(scanner);
        when(revertRequestMapper.map(any())).thenReturn(scannerResponse);

        ScannerResponse createdScanner = scannerService.create(scannerResponse);

        assertNotNull(createdScanner);
        assertEquals(scannerResponse, createdScanner);

        verify(requestMapper, times(1)).map(any());
        verify(scannerRepository, times(1)).findByEventAndLocationAndAccountType(any(), any(), any());
        verify(scannerRepository, times(1)).save(any());
        verify(revertRequestMapper, times(1)).map(any());
    }

    @Test
    void createExistingScanner_ReturnExistingScanner() {
        ScannerResponse scannerResponse = new ScannerResponse();
        scannerResponse.setScannerId(1L);
        LocationResponse locationResponse = new LocationResponse();
        locationResponse.setType("Dining");
        locationResponse.setName("Argiro");
        locationResponse.setLocationId(1L);
        scannerResponse.setLocation(locationResponse);


        Scanner scanner = new Scanner();
        scanner.setScannerId(1L);
        scanner.setLocation(new Location(1L, "Argiro", LocationType.Dining));

        when(requestMapper.map(any())).thenReturn(scanner);
        when(scannerRepository.findByEventAndLocationAndAccountType(any(), any(), any())).thenReturn(Optional.of(scanner));
        when(revertRequestMapper.map(any())).thenReturn(scannerResponse);

        ScannerResponse createdScanner = scannerService.create(scannerResponse);

        assertNotNull(createdScanner);
        assertEquals(scannerResponse, createdScanner);
        verify(requestMapper, times(1)).map(scannerResponse);
        verify(scannerRepository, times(1)).findByEventAndLocationAndAccountType(any(), any(), any());
        verify(revertRequestMapper, times(1)).map(any());
        verify(scannerRepository, never()).save(any());
    }

    @Test
    void deleteScanner_Success() {
        Scanner scanner = new Scanner();
        scanner.setScannerId(1L);

        List<ScanRecord> scanRecords = new ArrayList<>();
        when(scannerRecordRepository.findByScanner(any())).thenReturn(scanRecords);
        when(scannerRepository.findById(any())).thenReturn(Optional.of(scanner));

        assertDoesNotThrow(() -> scannerService.delete(1L));

        verify(scannerRecordRepository, times(1)).findByScanner(any());
        verify(scannerRepository, times(1)).findById(any());
        verify(scannerRepository, times(1)).delete(scanner);
    }

    @Test
    void deleteScanner_WithAssociatedRecords_ThrowsException() {
        Scanner scanner = new Scanner();
        scanner.setScannerId(1L);

        List<ScanRecord> scanRecords = new ArrayList<>();
        ScanRecord scanRecord = new ScanRecord();
        scanRecord.setRecordId(1L);
        scanRecord.setScanner(scanner);
        scanRecords.add(scanRecord);
        when(scannerRecordRepository.findByScanner(any())).thenReturn(scanRecords);
        when(scannerRepository.findById(any())).thenReturn(Optional.of(scanner));

        assertThrows(RuntimeException.class, () -> scannerService.delete(1L));

        verify(scannerRecordRepository, times(1)).findByScanner(any());
        verify(scannerRepository, times(1)).findById(any());
        verify(scannerRepository, never()).delete(scanner);
    }

    @Test
    void deleteScanner_NonExistingScanner_ThrowsException() {
        when(scannerRepository.findById(any())).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> scannerService.delete(1L));

        verify(scannerRecordRepository, never()).findByScanner(any());
        verify(scannerRepository, times(1)).findById(any());
        verify(scannerRepository, never()).delete((Scanner) any());
    }

    @Test
    void testConvert() {
        Scanner scanner = new Scanner();
        ScannerResponse expectedResponse = new ScannerResponse();
        when(revertRequestMapper.map(scanner)).thenReturn(expectedResponse);

        ScannerResponse actualResponse = scannerService.convert(scanner);

        assertEquals(expectedResponse, actualResponse);
    }
}
