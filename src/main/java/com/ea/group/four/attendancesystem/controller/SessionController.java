package com.ea.group.four.attendancesystem.controller;

import com.ea.group.four.attendancesystem.domain.Session;
import com.ea.group.four.attendancesystem.service.response.SessionResponse;
import edu.miu.common.controller.BaseReadWriteController;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/events/{eventId}/sessions")
public class SessionController extends BaseReadWriteController<SessionResponse, Session,Long> {

}
