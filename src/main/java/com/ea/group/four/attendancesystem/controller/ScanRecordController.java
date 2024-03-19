package com.ea.group.four.attendancesystem.controller;

import com.ea.group.four.attendancesystem.domain.Location;
import com.ea.group.four.attendancesystem.domain.Member;
import com.ea.group.four.attendancesystem.domain.ScanRecord;
import com.ea.group.four.attendancesystem.domain.Scanner;
import com.ea.group.four.attendancesystem.repository.MemberRepository;
import com.ea.group.four.attendancesystem.repository.ScannerRecordRepository;
import com.ea.group.four.attendancesystem.repository.ScannerRepository;
import com.ea.group.four.attendancesystem.service.ScannerRecordService;
import com.ea.group.four.attendancesystem.service.ScannerService;
import com.ea.group.four.attendancesystem.service.mapper.ScannerResponseToScannerMapper;
import com.ea.group.four.attendancesystem.service.response.LocationResponse;
import com.ea.group.four.attendancesystem.service.response.ScanRecordResponse;
import com.ea.group.four.attendancesystem.service.response.ScannerResponse;
import edu.miu.common.controller.BaseReadWriteController;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/scanners")
public class ScanRecordController extends BaseReadWriteController<ScanRecordResponse, ScanRecord, Long> {





}
