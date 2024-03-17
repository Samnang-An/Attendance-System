package com.ea.group.four.attendancesystem.service.mapper;

import com.ea.group.four.attendancesystem.domain.BadgeScanner;
import com.ea.group.four.attendancesystem.service.response.response.BadgeScannerResponse;
import edu.miu.common.service.mapper.BaseMapper;
import ma.glasnost.orika.MapperFactory;
import org.springframework.stereotype.Component;

@Component
public class ScannerResponseToScannerMapper extends BaseMapper<BadgeScannerResponse, BadgeScanner> {

  public ScannerResponseToScannerMapper(MapperFactory mapperFactory) {
    super(mapperFactory, BadgeScannerResponse.class, BadgeScanner.class);
  }

}
