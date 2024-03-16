package com.ea.group.four.attendancesystem.service.mapper;

import com.ea.group.four.attendancesystem.domain.Scanner;
import com.ea.group.four.attendancesystem.service.response.response.ScannerResponse;
import edu.miu.common.service.mapper.BaseMapper;
import ma.glasnost.orika.MapperFactory;
import org.springframework.stereotype.Component;

@Component
public class ScannerToScannerResponseMapper extends BaseMapper<ScannerResponse, Scanner> {

  public ScannerToScannerResponseMapper(MapperFactory mapperFactory) {
    super(mapperFactory, ScannerResponse.class, Scanner.class);
  }

}
