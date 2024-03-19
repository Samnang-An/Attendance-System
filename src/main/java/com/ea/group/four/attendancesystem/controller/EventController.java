package com.ea.group.four.attendancesystem.controller;

import com.ea.group.four.attendancesystem.domain.Event;
import com.ea.group.four.attendancesystem.domain.Member;
import com.ea.group.four.attendancesystem.service.EventService;
import com.ea.group.four.attendancesystem.service.response.EventResponse;
import edu.miu.common.controller.BaseReadWriteController;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;


import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/events")
public class EventController extends BaseReadWriteController<EventResponse, Event, Long> {


    @Autowired
    private EventService eventService;


    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody EventResponse request){
        EventResponse eventResponse = eventService.create(request);
        return ResponseEntity.ok(eventResponse);

    }

    @PostMapping("{eventId}/members")
    public ResponseEntity<?> addMembersToEvent(@PathVariable Long eventId, @RequestBody List<Member> members) {
        try {
            EventResponse event = eventService.addMembersToEvent(eventId, members);
            return ResponseEntity.ok(event);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error adding members to event: " + e.getMessage());
        }

    }


}
