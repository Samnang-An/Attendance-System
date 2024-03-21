package com.ea.group.four.attendancesystem.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.ea.group.four.attendancesystem.service.ScannerRecordService;
import com.ea.group.four.attendancesystem.service.response.AccountTypeResponse;
import com.ea.group.four.attendancesystem.service.response.EventResponse;
import com.ea.group.four.attendancesystem.service.response.LocationResponse;
import com.ea.group.four.attendancesystem.service.response.MemberResponse;
import com.ea.group.four.attendancesystem.service.response.RoleResponse;
import com.ea.group.four.attendancesystem.service.response.ScanRecordResponse;
import com.ea.group.four.attendancesystem.service.response.ScannerResponse;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

class AccountControllerTest {

  private MockMvc mockMvc;

  @Mock
  private ScannerRecordService scannerRecordService;

  @InjectMocks
  private AccountController accountController;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
    mockMvc = MockMvcBuilders.standaloneSetup(accountController).build();
    accountController = new AccountController(scannerRecordService);
  }

  @Test
  void testCreateScanner() throws Exception {
    List<ScanRecordResponse> scannerRecords = new ArrayList<>();
    ScanRecordResponse scannerRecord = new ScanRecordResponse();
    //Scanner
    ScannerResponse scanner = new ScannerResponse();
    scanner.setScannerId(1);
    LocationResponse location = new LocationResponse();
    location.setType("Dining");
    location.setName("Argiro");
    scanner.setLocation(location);
    scanner.setAccountType(new AccountTypeResponse());
    scanner.setEvent(new EventResponse());
    //Member
    MemberResponse member = new MemberResponse();
    member.setMemberId(1);
    member.setBarcode("123456");
    member.setRoles(List.of(new RoleResponse()));
    member.setFirstName("Samnang");
    member.setLastName("An");
    scannerRecord.setScanner(scanner);
    scannerRecord.setMember(member);
    scannerRecord.setRecordId(1);
    scannerRecord.setScannedDate(LocalDate.of(2024, 3, 10));
    scannerRecord.setScannedTime(LocalTime.of(10, 10, 10));
    scannerRecord.setStatus("Checkin");
    scannerRecords.add(scannerRecord);
    when(scannerRecordService.findByAccountBetweenDates(1, "2024-03-10", "2024-03-30")).thenReturn(
        scannerRecords);
    mockMvc.perform(MockMvcRequestBuilders.get("/accounts/1/attendance/2024-03-10/2024-03-30")
            .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk());
  }
}
