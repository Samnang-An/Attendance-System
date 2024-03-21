package com.ea.group.four.attendancesystem.controller;

import com.ea.group.four.attendancesystem.service.response.RoleResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ea.group.four.attendancesystem.service.MemberService;
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

import java.util.Collections;

import static org.mockito.Mockito.when;

public class MemberControllerTest {

    private MockMvc mockMvc;

    @Mock
    private MemberService memberService;

    @InjectMocks
    private MemberController memberController;

    private ObjectMapper objectMapper;

    @BeforeEach
    public void setup() {
        objectMapper = new ObjectMapper();
        MockitoAnnotations.openMocks(this);
        memberController = new MemberController(memberService);
        mockMvc = MockMvcBuilders.standaloneSetup(memberController).build();
    }

    @Test
    public void testGetRolesByMemberId() throws Exception {
        // dummy RoleResponse
        RoleResponse roleResponse = new RoleResponse();
        roleResponse.setName("Student");

        // Mocking behavior of memberService.getRolesByMemberId
        when(memberService.getRolesByMemberId(1L)).thenReturn(Collections.singletonList(roleResponse));

        mockMvc.perform(MockMvcRequestBuilders.get("/members/1/roles")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$").isArray())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].name").value("Student"));
    }


    @Test
    public void testAddRoleByMemberId() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/members/1/roles/2")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void testDeleteRoleByMemberIdAndRoleId() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/members/1/roles/2")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void testUpdateRoleByMemberIdAndRoleId() throws Exception {
        RoleResponse roleResponse = new RoleResponse();
        roleResponse.setName("UpdatedRole");

        mockMvc.perform(MockMvcRequestBuilders.put("/members/1/roles/2")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(roleResponse)))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
}
