package com.ea.group.four.attendancesystem.service.mapper;

import com.ea.group.four.attendancesystem.domain.Scanner;
import com.ea.group.four.attendancesystem.service.response.ScannerResponse;
import edu.miu.common.service.mapper.BaseMapper;
import ma.glasnost.orika.MapperFactory;
import org.springframework.stereotype.Component;

@Component
public class ScannerToScannerResponseMapper extends BaseMapper<Scanner, ScannerResponse> {

  public ScannerToScannerResponseMapper(MapperFactory mapperFactory) {
    super(mapperFactory, Scanner.class, ScannerResponse.class);
  }

}
