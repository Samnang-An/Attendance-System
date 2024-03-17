package com.ea.group.four.attendancesystem.service.mapper;

import com.ea.group.four.attendancesystem.domain.ScanRecord;
import com.ea.group.four.attendancesystem.service.response.ScanRecordResponse;
import edu.miu.common.service.mapper.BaseMapper;
import ma.glasnost.orika.MapperFactory;
import org.springframework.stereotype.Component;

@Component
public class ScannerRecordToScannerRecordResponseMapper extends
    BaseMapper<ScanRecord, ScanRecordResponse> {

  public ScannerRecordToScannerRecordResponseMapper(MapperFactory mapperFactory) {
    super(mapperFactory, ScanRecord.class, ScanRecordResponse.class);
  }

}
