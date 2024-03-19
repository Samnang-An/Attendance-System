package com.ea.group.four.attendancesystem.service;

import com.ea.group.four.attendancesystem.domain.ScanRecord;
import com.ea.group.four.attendancesystem.service.response.ScanRecordResponse;
import edu.miu.common.service.BaseReadWriteService;
import java.util.List;

public interface ScannerRecordService extends
    BaseReadWriteService<ScanRecordResponse, ScanRecord, Long> {

  List<ScanRecordResponse> findByAccountBetweenDates(String accountName, String fromDate, String toDate);

}
