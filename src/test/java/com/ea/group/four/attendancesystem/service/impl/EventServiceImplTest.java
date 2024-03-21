package com.ea.group.four.attendancesystem.service.impl;


import com.ea.group.four.attendancesystem.domain.Event;
import com.ea.group.four.attendancesystem.domain.Member;
import com.ea.group.four.attendancesystem.service.response.EventResponse;
import edu.miu.common.repository.BaseRepository;
import edu.miu.common.service.mapper.BaseMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;
import  java.util.*;

@ContextConfiguration(classes = {EventServiceImpl.class})
@ExtendWith(SpringExtension.class)
public class EventServiceImplTest {


    @MockBean
    private BaseMapper<Event, EventResponse> eventMapper;

    @MockBean
    private  BaseMapper<EventResponse,Event> eventMapper2;

    @MockBean
    private BaseRepository<Event,Long> eventRepository;

    @MockBean
    private  BaseRepository<Member,Long> memberRepository;



    @Test
    void testRemoveMember_SuccessfulRemoval(){

        Long eventId = 1L;
        Long memberId = 2L;

        Event event = new Event();
        event.setEventId(eventId);
        Set<Member> eventMembers = new HashSet<>();
        Member memberToRemove = new Member();
        memberToRemove.setMemberId(memberId);
        eventMembers.add(memberToRemove);
        event.setMembers(eventMembers);

        Mockito.when(eventRepository.findById(eventId)).thenReturn(Optional.of)


    }


}
