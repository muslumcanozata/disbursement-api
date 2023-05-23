package com.loan.disbursementapi.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.loan.disbursementapi.controller.request.BatchCreateUserRequest;
import com.loan.disbursementapi.controller.request.CreateUserRequest;
import com.loan.disbursementapi.service.UserService;
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
public class UserControllerTest {
    @InjectMocks
    private UserController controller;
    @Mock
    private UserService service;
    private MockMvc mockMvc;
    private ObjectMapper objectMapper;
    private final String URL = "/api/users/";
    private final String INSERT_ONE_URL = "user";
    private final String GET_ONE_URL = "user/";
    private final String DELETE_ONE_URL = "user/";

    @BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
        objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
    }

    @Test
    public void insertOne_ThenReturn() throws Exception {
        //given
        CreateUserRequest request = Instancio.create(CreateUserRequest.class);
        ObjectWriter objectWriter = objectMapper.writer().withDefaultPrettyPrinter();
        String requestJson = objectWriter.writeValueAsString(request);

        //then
        mockMvc.perform(MockMvcRequestBuilders.post(URL+INSERT_ONE_URL).contentType(APPLICATION_JSON_VALUE).content(requestJson)).andExpect(MockMvcResultMatchers.status().isCreated()).andReturn();
        Mockito.verify(service, Mockito.times(1)).insertOne(Mockito.any(CreateUserRequest.class));
    }

    @Test
    public void insertAll_ThenReturn() throws Exception {
        //given
        BatchCreateUserRequest request = Instancio.create(BatchCreateUserRequest.class);
        ObjectWriter objectWriter = objectMapper.writer().withDefaultPrettyPrinter();
        String requestJson = objectWriter.writeValueAsString(request);

        //then
        mockMvc.perform(MockMvcRequestBuilders.post(URL).contentType(APPLICATION_JSON_VALUE).content(requestJson)).andExpect(MockMvcResultMatchers.status().isCreated()).andReturn();
        Mockito.verify(service, Mockito.times(1)).insertAll(Mockito.anyList());
    }

    @Test
    public void getOne_ThenReturn() throws Exception {
        //given
        int id = 0;

        //then
        mockMvc.perform(MockMvcRequestBuilders.get(URL+GET_ONE_URL+id)).andExpect(MockMvcResultMatchers.status().isOk()).andReturn();
        Mockito.verify(service, Mockito.times(1)).getOne(Mockito.anyInt());
    }

    @Test
    public void getAll_ThenReturn() throws Exception {
        //then
        mockMvc.perform(MockMvcRequestBuilders.get(URL)).andExpect(MockMvcResultMatchers.status().isOk()).andReturn();
        Mockito.verify(service, Mockito.times(1)).getAll();
    }
    @Test
    public void deleteOne_ThenReturn() throws Exception {
        //given
        Integer id = 0;
        ObjectWriter objectWriter = objectMapper.writer().withDefaultPrettyPrinter();
        String requestJson = objectWriter.writeValueAsString(id);

        //then
        mockMvc.perform(MockMvcRequestBuilders.delete(URL+DELETE_ONE_URL+id).contentType(APPLICATION_JSON_VALUE).content(requestJson)).andExpect(MockMvcResultMatchers.status().isAccepted()).andReturn();
        Mockito.verify(service, Mockito.times(1)).deleteOne(Mockito.anyInt());
    }
}
