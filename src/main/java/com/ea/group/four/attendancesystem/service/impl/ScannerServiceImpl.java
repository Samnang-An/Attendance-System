package com.ea.group.four.attendancesystem.service.impl;

import com.ea.group.four.attendancesystem.domain.Scanner;
import com.ea.group.four.attendancesystem.service.ScannerService;
import com.ea.group.four.attendancesystem.service.response.ScannerResponse;
import edu.miu.common.service.BaseReadWriteServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class ScannerServiceImpl extends
    BaseReadWriteServiceImpl<ScannerResponse, Scanner, Long> implements
    ScannerService {

}
