package com.ea.group.four.attendancesystem.service.mapper;

import com.ea.group.four.attendancesystem.domain.Member;
import com.ea.group.four.attendancesystem.service.response.MemberResponse;
import edu.miu.common.service.mapper.BaseMapper;
import ma.glasnost.orika.MapperFactory;
import org.springframework.stereotype.Component;

@Component
public class MemberToMemberResponseMapper extends BaseMapper<Member, MemberResponse> {

  public MemberToMemberResponseMapper(MapperFactory mapperFactory) {
    super(mapperFactory, Member.class, MemberResponse.class);
  }

}
