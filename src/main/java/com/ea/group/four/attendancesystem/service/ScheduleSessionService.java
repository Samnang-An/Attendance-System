package com.ea.group.four.attendancesystem.service;

import com.ea.group.four.attendancesystem.domain.ScheduleSession;
import com.ea.group.four.attendancesystem.service.response.response.ScheduleSessionResponse;
import edu.miu.common.service.BaseReadWriteService;

public interface ScheduleSessionService extends
    BaseReadWriteService<ScheduleSessionResponse, ScheduleSession, Long> {

}
