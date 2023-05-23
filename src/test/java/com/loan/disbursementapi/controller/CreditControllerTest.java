package com.loan.disbursementapi.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.loan.disbursementapi.controller.request.GetCreditsWithPaginationAndFilterRequest;
import com.loan.disbursementapi.domain.enums.CreditStatus;
import com.loan.disbursementapi.service.CreditService;
import org.instancio.Instancio;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Pageable;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.LocalDate;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@ExtendWith(MockitoExtension.class)
public class CreditControllerTest {
    @InjectMocks
    private CreditController creditController;
    @Mock
    private CreditService creditService;
    private MockMvc mockMvc;
    private ObjectMapper objectMapper;
    private final String URL = "/api/credits/";
    private final String GET_ALL_BY_USER_ID_WITH_PAGINATION_AND_FILTER_URL = "page/";

    @BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(creditController).build();
        objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
    }

    @Test
    public void getAllByUserIdWithPaginationAndFilter_ThenReturn() throws Exception {
        //given
        int id = 0;
        GetCreditsWithPaginationAndFilterRequest request = Instancio.create(GetCreditsWithPaginationAndFilterRequest.class);
        ObjectWriter objectWriter = objectMapper.writer().withDefaultPrettyPrinter();
        String requestJson = objectWriter.writeValueAsString(request);

        //then
        mockMvc.perform(MockMvcRequestBuilders.post(URL+GET_ALL_BY_USER_ID_WITH_PAGINATION_AND_FILTER_URL+id).contentType(APPLICATION_JSON_VALUE).content(requestJson)).andExpect(MockMvcResultMatchers.status().isOk()).andReturn();
        Mockito.verify(creditService, Mockito.times(1)).getAllByUserIdAndStatusAndDateWithPageable(Mockito.anyInt(), Mockito.any(CreditStatus.class), Mockito.any(LocalDate.class), Mockito.any(Pageable.class));
    }

    @Test
    public void getAllByUserId_ThenReturn() throws Exception {
        //given
        Integer id = 0;
        ObjectWriter objectWriter = objectMapper.writer().withDefaultPrettyPrinter();
        String requestJson = objectWriter.writeValueAsString(id);

        //then
        mockMvc.perform(MockMvcRequestBuilders.get(URL+id).contentType(APPLICATION_JSON_VALUE).content(requestJson)).andExpect(MockMvcResultMatchers.status().isOk()).andReturn();
        Mockito.verify(creditService, Mockito.times(1)).getAllByUserId(Mockito.anyInt());
    }
}
