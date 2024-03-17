package com.ea.group.four.attendancesystem.service;

import com.ea.group.four.attendancesystem.domain.Session;
import com.ea.group.four.attendancesystem.service.response.SessionResponse;
import edu.miu.common.service.BaseReadWriteService;

public interface SessionService extends BaseReadWriteService<SessionResponse, Session, Long> {

}
