package com.ea.group.four.attendancesystem.service;

import com.ea.group.four.attendancesystem.domain.BadgeScanner;
import com.ea.group.four.attendancesystem.service.response.response.BadgeScannerResponse;
import edu.miu.common.service.BaseReadWriteService;

public interface BadgeScannerService extends
    BaseReadWriteService<BadgeScannerResponse, BadgeScanner, Long> {

}
