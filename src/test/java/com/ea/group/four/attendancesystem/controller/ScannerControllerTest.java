package com.ea.group.four.attendancesystem.controller;

import com.ea.group.four.attendancesystem.service.ScannerService;
import com.ea.group.four.attendancesystem.service.response.LocationResponse;
import com.ea.group.four.attendancesystem.service.response.ScannerResponse;
import edu.miu.common.exception.ResourceNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class ScannerControllerTest {
    private MockMvc mockMvc;

    @Mock
    private ScannerService scannerService;

    @InjectMocks
    private ScannerController scannerController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(scannerController).build();
        scannerController = new ScannerController(scannerService);

    }

    @Test
    void testCreateScanner() throws Exception {
        ScannerResponse scannerRequest = new ScannerResponse();
        scannerRequest.setScannerId(1L);
        LocationResponse locationResponse = new LocationResponse();
        locationResponse.setType("Dining");
        locationResponse.setName("Argiro");
        locationResponse.setLocationId(1L);
        scannerRequest.setLocation(locationResponse);
        ScannerResponse expectedResponse = new ScannerResponse();
        expectedResponse.setScannerId(1L);
        LocationResponse locationResponse1 = new LocationResponse();
        locationResponse1.setType("Dining");
        locationResponse1.setName("Argiro");
        locationResponse1.setLocationId(1L);
        expectedResponse.setLocation(locationResponse1);
        when(scannerService.create(scannerRequest)).thenReturn(expectedResponse);
        mockMvc.perform(MockMvcRequestBuilders.post("/scanners")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"scannerId\":1,\"location\":{\"locationId\":1,\"name\":\"Argiro\",\"type\":\"Dining\"}}"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.scannerId").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$.location.name").value("Argiro"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.location.type").value("Dining"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.location.locationId").value(1));
    }

    @Test
    void testDeleteScanner_Success() throws Exception {
        Long id = 1L;
        mockMvc.perform(MockMvcRequestBuilders.delete("/scanners/{id}", id))
                .andExpect(status().isOk());
    }

    @Test
    void testDeleteScanner_WithAssociatedRecords_ThrowsException() throws Exception {
        Long id = 1L;
        RuntimeException exception = new RuntimeException("Cannot delete scanner with associated scan records");
        doThrow(exception).when(scannerService).delete(id);
        mockMvc.perform(MockMvcRequestBuilders.delete("/scanners/{id}", id))
                .andExpect(status().isBadRequest());
    }

    @Test
    void testDeleteScanner_NonExistingScanner_ThrowsException() throws Exception {
        Long id = 1L;
        ResourceNotFoundException exception = new ResourceNotFoundException();
        doThrow(exception).when(scannerService).delete(id);
        mockMvc.perform(MockMvcRequestBuilders.delete("/scanners/{id}", id))
                .andExpect(status().isBadRequest());
    }

}