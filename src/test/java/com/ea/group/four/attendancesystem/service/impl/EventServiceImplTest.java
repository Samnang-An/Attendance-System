package com.ea.group.four.attendancesystem.service.impl;

import com.ea.group.four.attendancesystem.domain.Event;
import com.ea.group.four.attendancesystem.domain.Member;
import com.ea.group.four.attendancesystem.domain.ScanRecord;
import com.ea.group.four.attendancesystem.domain.Scanner;
import com.ea.group.four.attendancesystem.integration.jms.JMSSender;
import com.ea.group.four.attendancesystem.repository.EventRepository;
import com.ea.group.four.attendancesystem.repository.MemberRepository;
import com.ea.group.four.attendancesystem.repository.ScannerRecordRepository;
import com.ea.group.four.attendancesystem.service.EventService;
import com.ea.group.four.attendancesystem.service.ScannerService;
import com.ea.group.four.attendancesystem.service.SessionService;
import com.ea.group.four.attendancesystem.service.mapper.EventReponseToEventMapper;
import com.ea.group.four.attendancesystem.service.mapper.EventToEventReponseMapper;
import com.ea.group.four.attendancesystem.service.mapper.ScannerRecordToScannerRecordResponseMapper;
import com.ea.group.four.attendancesystem.service.response.EventResponse;
import com.ea.group.four.attendancesystem.service.response.MemberResponse;
import com.ea.group.four.attendancesystem.service.response.ScanRecordResponse;
import com.ea.group.four.attendancesystem.service.response.ScannerResponse;
import edu.miu.common.repository.BaseRepository;
import edu.miu.common.service.mapper.BaseMapper;
import org.junit.Ignore;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.aot.DisabledInAotMode;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
@DataJpaTest
class EventServiceImplTest {


    @Mock
    private EventReponseToEventMapper eventReponseToEventMapper;

    @Mock
    private EventToEventReponseMapper eventToEventReponseMapper;

    @Mock
    private EventRepository eventRepository;

    @InjectMocks
    private EventServiceImpl eventService;

    @Mock
    private JMSSender jMSSender;

    @Mock
    private MemberRepository memberRepository;

    @Mock
    private ScannerRecordRepository scannerRecordRepository;

    @Mock
    private ScannerRecordToScannerRecordResponseMapper scannerRecordToScannerRecordResponseMapper;

    @Mock
    private ScannerService scannerService;

    @Mock
    private JMSSender jmsSender;

    @Mock
    private SessionService sessionService;

    @Autowired
    private TestEntityManager testEntityManager;




    @BeforeEach
    void setUp() {
        eventService = new EventServiceImpl(eventRepository,eventToEventReponseMapper,eventReponseToEventMapper,scannerRecordToScannerRecordResponseMapper,jmsSender,scannerService,scannerRecordRepository,memberRepository,sessionService);
    }

    public static Event createMockEvent(Long eventId,Set<Member> members) {
        Event event = new Event();
        event.setEventId(eventId);
        event.setName("Demo Event");
        event.setDescription("Demo Event description");
        event.setStartDate(LocalDate.now().minusDays(1));
        event.setEndDate(LocalDate.now().plusDays(5));

        Member member1 = createMockMember(1L, "John","Doe");
        Member member2 = createMockMember(2L, "Karl","Smith");
//        Set<Member> members = new HashSet<>();
//        members.add(member1);
//        members.add(member2);
        event.setMembers(members);
        return  event;
    }


    public static Member createMockMember(Long id, String firstName,String lastName) {
        Member member = new Member();
        member.setMemberId(id);
        member.setFirstName(firstName);
        member.setLastName(lastName);

        return member;
    }

    public static Scanner createMockScanner(Long scannerCode,Event event) {
        Scanner scanner = new Scanner();
        scanner.setScannerId(scannerCode);
        scanner.setEvent(event);
        return scanner;
    }
    public static ScanRecord createMockScanRecord(Long scanRecordId,Member member,Scanner scanner) {
        ScanRecord scanRecord = new ScanRecord();
        scanRecord.setRecordId(scanRecordId);
        scanRecord.setScanner(scanner);
        scanRecord.setMember(member);
        scanRecord.setScannedDate(LocalDate.now());
        scanRecord.setScannedTime(LocalTime.now());
        scanRecord.setStatus("Present");
        scanRecord.setDeprecated(false);

        return scanRecord;
    }

    public static ScanRecordResponse createMockScanRecordResponse(Long scanRecordId, MemberResponse member, ScannerResponse scanner) {
        ScanRecordResponse scanRecordResponse = new ScanRecordResponse();
        scanRecordResponse.setRecordId(scanRecordId);
        scanRecordResponse.setScanner(scanner);
        scanRecordResponse.setMember(member);
        scanRecordResponse.setScannedDate(LocalDate.now());
        scanRecordResponse.setScannedTime(LocalTime.now());
        scanRecordResponse.setStatus("Present");
        scanRecordResponse.setDeprecated(false);

        return scanRecordResponse;
    }

    @Test
    void testCreateEvent(){

        EventResponse request = new EventResponse();
        Event event = new Event();

        Map<String,List<String>> eventSchdule = new HashMap<>();
        eventSchdule.put("MONDAY",Arrays.asList("10:00-12:00"));

        request.setName("Name");
        request.setDescription("Event Description");
        request.setEventId(1L);
        request.setEventSchedule("{'MONDAY':['10:00-12:00']}");
        request.setMembers(new HashSet<>());
        request.setSchedule(eventSchdule);
        request.setStartDate(LocalDate.of(2024, 4, 1));
        request.setEndDate(LocalDate.of(2024, 5, 10));

        event.setName("Name");
        event.setDescription("Event Description");
        event.setEventId(1L);
        event.setEventSchedule("{'MONDAY':['10:00-12:00']}");
        event.setMembers(new HashSet<>());
        event.setSchedule(eventSchdule);
        event.setStartDate(LocalDate.of(2024, 4, 1));
        event.setEndDate(LocalDate.of(2024, 5, 10));

        when(eventReponseToEventMapper.map(request)).thenReturn(event);
        when(eventToEventReponseMapper.map(event)).thenReturn(request);
        when(eventRepository.save(event)).thenReturn(event);
        EventResponse eventResponse = eventService.create(request);
        System.out.println(eventResponse);
        assertEquals(eventResponse,request);
        assertNotNull(eventResponse);
    }

    @Test
    @Ignore
    void testRemoveMember(){


        Event event = new Event();
        Member member = createMockMember(1L,"Ved","Patel");
        Member member1 = createMockMember(2L,"Milap","Prajapati");

        Map<String,List<String>> eventSchdule = new HashMap<>();
        eventSchdule.put("MONDAY",Arrays.asList("10:00-12:00"));
        event.setName("Name");
        event.setDescription("Event Description");
        event.setEventId(1L);
        event.setEventSchedule("{'MONDAY':['10:00-12:00']}");
        event.setMembers(new HashSet<>(Arrays.asList(member1,member)));
        event.setSchedule(eventSchdule);
        event.setStartDate(LocalDate.of(2024, 4, 1));
        event.setEndDate(LocalDate.of(2024, 5, 10));

        EventResponse eventResponse = new EventResponse();
        eventResponse.setName("Name");
        eventResponse.setDescription("Event Description");
        eventResponse.setEventId(1L);
        eventResponse.setEventSchedule("{'MONDAY':['10:00-12:00']}");
        eventResponse.setMembers(new HashSet<>(Arrays.asList(new MemberResponse())));
        eventResponse.setSchedule(eventSchdule);
        eventResponse.setStartDate(LocalDate.of(2024, 4, 1));
        eventResponse.setEndDate(LocalDate.of(2024, 5, 10));


        Member memberToRemove = member1;
        Optional<Event> optionalEvent = Optional.of(event);
        Optional<Member> optionalMember = Optional.of(memberToRemove);


        testEntityManager.persistAndFlush(event);
        testEntityManager.persistAndFlush(memberToRemove);
        testEntityManager.persistAndFlush(eventResponse);
//        when(eventRepository.findById(1L)).thenReturn(optionalEvent);
//        when(memberRepository.findById(2L)).thenReturn(optionalMember);
//        when(eventToEventReponseMapper.map(event)).thenReturn(eventResponse); // You can adjust the return value as needed
        EventResponse expectedEventResponse = eventService.removeMember(1L, 2L);


        assertNotNull(eventResponse);
//        verify(eventRepository, times(1)).save(event);

    }

    @Test
    @Ignore
    void testCalculateAttendanceOfEvent() {

        Member member1 = createMockMember(1L,"Vivek","Parmar");
        Member member2 =  createMockMember(2L,"Milap","Prajapati");
        Event event = createMockEvent(1L,new HashSet<>(Arrays.asList(member1,member2)));

        List<Scanner> scanners = Arrays.asList(createMockScanner(1L,event), createMockScanner(2L,event));
        List<ScanRecord> scanRecords = Arrays.asList(createMockScanRecord(1L,member1,createMockScanner(1L,event)),
                                                     createMockScanRecord(2L,member2,createMockScanner(1L,event)));

        List<ScanRecordResponse> expectedResponse = new ArrayList<>();
        expectedResponse.add(createMockScanRecordResponse(1L,new MemberResponse(),new ScannerResponse()));
        expectedResponse.add(createMockScanRecordResponse(2L,new MemberResponse(),new ScannerResponse()));



        when(eventRepository.findById(any())).thenReturn(Optional.of(event));
        when(scannerService.getEventScanners(any())).thenReturn(scanners);
        when(scannerRecordRepository.findByScanner(any())).thenReturn(scanRecords);
        when(scannerRecordToScannerRecordResponseMapper.map(Mockito.any())).thenReturn(expectedResponse.get(0));
        List<ScanRecordResponse> result =  eventService.calculateAttendanceOfEvent(event.getEventId());
        assertNotNull(result);
        assertEquals(expectedResponse.size(), result.size());
//        eventServiceImpl.calculateAttendanceOfEvent(1L);
    }

    @Test
    void testConvertEventAndScheduleToString() {

        Event event = new Event();
        event.setDescription("The characteristics of someone or something");
        event.setEndDate(LocalDate.of(1970, 1, 1));
        event.setEventId(1L);
        event.setEventSchedule("Event Schedule");
        event.setMembers(new HashSet<>());
        event.setName("Name");
        event.setSchedule(new HashMap<>());
        event.setStartDate(LocalDate.of(1970, 1, 1));
        assertEquals("1###{}", eventService.convertEventAndScheduleToString(event, new HashMap<>()));
    }



}
