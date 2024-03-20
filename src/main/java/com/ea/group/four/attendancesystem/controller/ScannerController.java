package com.ea.group.four.attendancesystem.controller;

import com.ea.group.four.attendancesystem.domain.Scanner;
import com.ea.group.four.attendancesystem.service.ScannerRecordService;
import com.ea.group.four.attendancesystem.service.ScannerService;
import com.ea.group.four.attendancesystem.service.response.ScanRecordResponse;
import com.ea.group.four.attendancesystem.service.response.ScannerResponse;
import edu.miu.common.controller.BaseReadWriteController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
@RestController
@RequestMapping("/scanners")
public class ScannerController extends BaseReadWriteController<ScannerResponse, Scanner, Long> {
    @Autowired
    ScannerService scannerService;

    @Autowired
    private ScannerRecordService scannerRecordService;

    public ScannerController() {

    }

    public ScannerController(ScannerService scannerService) {
        this.scannerService = scannerService;
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody ScannerResponse request) {
        return response(() -> scannerService.create(request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        try {
            scannerService.delete(id);
            return ResponseEntity.ok(null);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Error: " + e.getMessage());
        }
    }

    @PostMapping("/{scannerCode}/records")
    public ResponseEntity<?> createScannerRecord(@PathVariable Long scannerCode,
                                    @RequestBody ScanRecordResponse request) {
        request.getScanner().setScannerId(scannerCode);
        return ResponseEntity.ok(scannerRecordService.create(request));
    }

    @DeleteMapping("/{id}/records")
    public ResponseEntity<?> customDelete(@PathVariable Long id){
        return ResponseEntity.ok(scannerRecordService.customDelete(id));
    }

}
