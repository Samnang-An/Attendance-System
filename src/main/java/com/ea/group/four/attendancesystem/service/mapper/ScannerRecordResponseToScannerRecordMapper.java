package com.ea.group.four.attendancesystem.service.mapper;

import com.ea.group.four.attendancesystem.domain.ScanRecord;
import com.ea.group.four.attendancesystem.service.response.response.ScanRecordResponse;
import edu.miu.common.service.mapper.BaseMapper;
import ma.glasnost.orika.MapperFactory;
import org.springframework.stereotype.Component;

@Component
public class ScannerRecordResponseToScannerRecordMapper extends
    BaseMapper<ScanRecordResponse, ScanRecord> {

  public ScannerRecordResponseToScannerRecordMapper(MapperFactory mapperFactory) {
    super(mapperFactory, ScanRecordResponse.class, ScanRecord.class);
  }

}
