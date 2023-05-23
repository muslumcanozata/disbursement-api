package com.loan.disbursementapi.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.loan.disbursementapi.controller.request.PaybackRequest;
import com.loan.disbursementapi.service.PaybackService;
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

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@ExtendWith(MockitoExtension.class)
public class PaybackControllerTest {
    @InjectMocks
    private PaybackController controller;
    @Mock
    private PaybackService service;
    private MockMvc mockMvc;
    private ObjectMapper objectMapper;
    private final String URL = "/api/payback";

    @BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
        objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
    }

    @Test
    public void payback_ThenReturn() throws Exception {
        //given
        PaybackRequest request = Instancio.create(PaybackRequest.class);
        ObjectWriter objectWriter = objectMapper.writer().withDefaultPrettyPrinter();
        String requestJson = objectWriter.writeValueAsString(request);

        //then
        mockMvc.perform(MockMvcRequestBuilders.patch(URL).contentType(APPLICATION_JSON_VALUE).content(requestJson)).andExpect(MockMvcResultMatchers.status().isNoContent()).andReturn();
        Mockito.verify(service, Mockito.times(1)).payback(Mockito.any(PaybackRequest.class));
    }
}
