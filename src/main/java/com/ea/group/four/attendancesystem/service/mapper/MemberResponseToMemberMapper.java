package com.ea.group.four.attendancesystem.service.mapper;

import com.ea.group.four.attendancesystem.domain.Member;
import com.ea.group.four.attendancesystem.service.response.MemberResponse;
import edu.miu.common.service.mapper.BaseMapper;
import ma.glasnost.orika.MapperFactory;
import org.springframework.stereotype.Component;

@Component
public class MemberResponseToMemberMapper extends BaseMapper<MemberResponse, Member> {

  public MemberResponseToMemberMapper(MapperFactory mapperFactory) {
    super(mapperFactory, MemberResponse.class, Member.class);
  }

}
