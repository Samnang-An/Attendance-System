package com.ea.group.four.attendancesystem.service;

import com.ea.group.four.attendancesystem.domain.Scanner;
import com.ea.group.four.attendancesystem.service.response.ScannerResponse;
import edu.miu.common.service.BaseReadWriteService;

public interface ScannerService extends
    BaseReadWriteService<ScannerResponse, Scanner, Long> {

}
