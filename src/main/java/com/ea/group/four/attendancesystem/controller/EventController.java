package com.ea.group.four.attendancesystem.controller;

import com.ea.group.four.attendancesystem.domain.Event;
import com.ea.group.four.attendancesystem.domain.Member;
import com.ea.group.four.attendancesystem.exception.InvalidMemberException;
import com.ea.group.four.attendancesystem.exception.InvalidScheduleException;
import com.ea.group.four.attendancesystem.exception.InvalidSessionException;
import com.ea.group.four.attendancesystem.service.EventService;
import com.ea.group.four.attendancesystem.service.response.EventResponse;
import com.ea.group.four.attendancesystem.service.response.ScanRecordResponse;
import com.ea.group.four.attendancesystem.service.response.SessionResponse;
import edu.miu.common.controller.BaseReadWriteController;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/events")
public class EventController extends BaseReadWriteController<EventResponse, Event, Long> {

    @Autowired
    private EventService eventService;


    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody EventResponse request){
        try{
            EventResponse eventResponse = eventService.create(request);
            return ResponseEntity.ok(eventResponse);
        }catch (InvalidScheduleException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error while creating event: " + e.getMessage());
        }


    }

    @PostMapping("{eventId}/members")
    public ResponseEntity<?> addMembersToEvent(@PathVariable Long eventId, @RequestBody List<Member> members) {
        try {
            EventResponse event = eventService.addMembersToEvent(eventId, members);
            return ResponseEntity.ok(event);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error while adding members to event: " + e.getMessage());
        }

    }

    @PatchMapping("/{eventId}/schedule")
    public ResponseEntity<?> updateSchedule(@PathVariable Long eventId, @RequestBody Map<String,List<String>> schedule){
        try{
            EventResponse eventResponse = eventService.updateSchedule(eventId,schedule);
            return ResponseEntity.ok(eventResponse);
        }catch (InvalidScheduleException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error while adding members to event: " + e.getMessage());
        }
    }

    @PutMapping("/{eventId}/session")
    public ResponseEntity<?> updateSession(@PathVariable Long eventId, @RequestBody SessionResponse sessionResponse){
        try{
            SessionResponse updatedSessionResponse = eventService.updateSession(eventId, sessionResponse);
            return ResponseEntity.ok(updatedSessionResponse);
        }catch (InvalidSessionException | EntityNotFoundException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error while updating the session for event: " + e.getMessage());
        }

    }



    @PutMapping("/members/{eventId}/remove/{memberId}")
    public ResponseEntity<?> removeMemberFromEvent(@PathVariable Long eventId, @PathVariable Long memberId){
        try{
            EventResponse event = eventService.removeMember(eventId,memberId);
            return ResponseEntity.ok(event);
        }
        catch (EntityNotFoundException | InvalidMemberException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error while removing member from event: " + e.getMessage());
        }
    }
    @GetMapping("{eventId}/attendance")
    public ResponseEntity<?> getAttendanceOfEvent(@PathVariable Long eventId){
        try {
            List<ScanRecordResponse> eventAttendance = eventService.calculateAttendanceOfEvent(eventId);
            return ResponseEntity.ok(eventAttendance);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error while getting attendance of event: " + e.getMessage());
        }

    }
}

