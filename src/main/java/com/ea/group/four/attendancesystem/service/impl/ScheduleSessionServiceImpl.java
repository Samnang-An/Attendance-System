package com.ea.group.four.attendancesystem.service.impl;

import com.ea.group.four.attendancesystem.domain.ScheduleSession;
import com.ea.group.four.attendancesystem.service.ScheduleSessionService;
import com.ea.group.four.attendancesystem.service.response.response.ScheduleSessionResponse;
import edu.miu.common.service.BaseReadWriteServiceImpl;
import org.springframework.stereotype.Service;

//@Service
public class ScheduleSessionServiceImpl extends
    BaseReadWriteServiceImpl<ScheduleSessionResponse, ScheduleSession, Long> implements
    ScheduleSessionService {

}
