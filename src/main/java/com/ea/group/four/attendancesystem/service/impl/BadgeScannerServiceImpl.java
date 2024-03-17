package com.ea.group.four.attendancesystem.service.impl;

import com.ea.group.four.attendancesystem.domain.BadgeScanner;
import com.ea.group.four.attendancesystem.service.BadgeScannerService;
import com.ea.group.four.attendancesystem.service.response.response.BadgeScannerResponse;
import edu.miu.common.service.BaseReadWriteServiceImpl;
import org.springframework.stereotype.Service;

//@Service
public class BadgeScannerServiceImpl extends
    BaseReadWriteServiceImpl<BadgeScannerResponse, BadgeScanner, Long> implements
    BadgeScannerService {

}
