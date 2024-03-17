package com.ea.group.four.attendancesystem.service.impl;

import com.ea.group.four.attendancesystem.domain.ScanRecord;
import com.ea.group.four.attendancesystem.service.ScannerRecordService;
import com.ea.group.four.attendancesystem.service.response.response.ScanRecordResponse;
import edu.miu.common.service.BaseReadWriteServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class ScannerRecordServiceImpl extends
    BaseReadWriteServiceImpl<ScanRecordResponse, ScanRecord, Long> implements
    ScannerRecordService {

}
