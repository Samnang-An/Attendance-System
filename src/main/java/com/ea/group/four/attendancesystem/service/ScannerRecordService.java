package com.ea.group.four.attendancesystem.service;

import com.ea.group.four.attendancesystem.domain.ScanRecord;
import com.ea.group.four.attendancesystem.service.response.ScanRecordResponse;
import edu.miu.common.service.BaseReadWriteService;

public interface ScannerRecordService extends
    BaseReadWriteService<ScanRecordResponse, ScanRecord, Long> {

}
