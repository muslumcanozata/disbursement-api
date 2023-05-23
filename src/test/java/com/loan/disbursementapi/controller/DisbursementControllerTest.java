package com.loan.disbursementapi.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.loan.disbursementapi.controller.request.DisbursementRequest;
import com.loan.disbursementapi.controller.response.DisbursementResponse;
import com.loan.disbursementapi.service.DisbursementService;
import org.instancio.Instancio;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@ExtendWith(MockitoExtension.class)
public class DisbursementControllerTest {
    @InjectMocks
    private DisbursementController controller;
    @Mock
    private DisbursementService service;
    private MockMvc mockMvc;
    private ObjectMapper objectMapper;
    private final String URL = "/api/disbursement";

    @BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
        objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
    }

    @Test
    public void payback_WhenItHasRequestBody_ThenReturnCreated() throws Exception {
        //given
        DisbursementRequest request = Instancio.create(DisbursementRequest.class);
        ObjectWriter objectWriter = objectMapper.writer().withDefaultPrettyPrinter();
        String requestJson = objectWriter.writeValueAsString(request);

        //when
        when(service.disburse(Mockito.any(DisbursementRequest.class))).thenReturn(new DisbursementResponse());
        //then
        mockMvc.perform(MockMvcRequestBuilders.post(URL).contentType(APPLICATION_JSON_VALUE).content(requestJson)).andExpect(MockMvcResultMatchers.status().isCreated()).andReturn();
        Mockito.verify(service, Mockito.times(1)).disburse(Mockito.any(DisbursementRequest.class));
    }

    @Test
    public void payback_WhenItHasNullRequestBody_ThenReturnBadRequest() throws Exception {
        //given
        DisbursementRequest request = Instancio.create(DisbursementRequest.class);
        ObjectWriter objectWriter = objectMapper.writer().withDefaultPrettyPrinter();
        String requestJson = objectWriter.writeValueAsString(request);

        //when
        when(service.disburse(Mockito.any(DisbursementRequest.class))).thenReturn(new DisbursementResponse());

        //then
        mockMvc.perform(MockMvcRequestBuilders.post(URL).contentType(APPLICATION_JSON_VALUE).content(requestJson)).andExpect(MockMvcResultMatchers.status().isBadRequest()).andReturn();
        Mockito.verify(service, Mockito.times(0)).disburse(Mockito.any(DisbursementRequest.class));
    }
}
