package com.example.casio.controller;

import com.example.casio.dto.request.UserRequestDto;
import com.example.casio.dto.response.UserResponseDto;
import com.example.casio.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.equalTo;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class UserControllerTest {
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private UserService userService;

    private String baseUri;
    private Long id;
    private UserRequestDto userRequest;
    private UserResponseDto userResponse;
    UserRequestDto patchUserRequest;

    @BeforeEach
    public void setUp() {

        baseUri = "/api/v1/users";
        id = 1L;

        userRequest = new UserRequestDto(
                "testName",
                "testLastName",
                "tester",
                "test@email.com",
                "012-345-67-89",
                "7S*P1Wr~V99t=!\"_QLS);x5Vj>EPm$YQ");

        userResponse = new UserResponseDto(
                "testName",
                "testLastName",
                "tester",
                "test@email.com",
                "012-345-67-89");

        patchUserRequest = new UserRequestDto(
                "patchedTestName",
                "testLastName",
                "tester",
                null,
                null,
                null);
    }

    @Test
    void testCreateUser() throws Exception {

        String userRequestJson = objectMapper.writeValueAsString(userRequest);

//        when(userService.createUser(any(UserRequestDto.class)))
//                .thenReturn(userResponse);
        mockMvc.perform(post(baseUri)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(userRequestJson))
                .andExpect(status().isAccepted());

//                .andExpect(jsonPath("$.name", equalTo(userRequest.name())))
//                .andExpect(jsonPath("$.surname", equalTo(userRequest.surname())))
//                .andExpect(jsonPath("$.nickname", equalTo(userRequest.nickname())))
//                .andExpect(jsonPath("$.email", equalTo(userRequest.email())))
//                .andExpect(jsonPath("$.phoneNumber", equalTo(userRequest.phoneNumber())));
    }

//    @Test
//    void testFindById() throws Exception {
//        when(userService.findById(id)).thenReturn(userResponse);
//
//        mockMvc.perform(get(baseUri + "/" + id))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.id", equalTo(id)))
//                .andExpect(jsonPath("$.name", equalTo(userRequest.name())))
//                .andExpect(jsonPath("$.surname", equalTo(userRequest.surname())))
//                .andExpect(jsonPath("$.nickname", equalTo(userRequest.nickname())))
//                .andExpect(jsonPath("$.email", equalTo(userRequest.email())))
//                .andExpect(jsonPath("$.phoneNumber", equalTo(userRequest.phoneNumber())));
//    }

    @Test
    void testUpdateUser() throws Exception {
        userResponse = new UserResponseDto(
                "patchedTestName",
                "testLastName",
                "tester",
                "test@email.com",
                "012-345-67-89");

        when(userService.updateUser(anyLong(), any(UserRequestDto.class))).thenReturn(userResponse);

        String patchRequestJson = objectMapper.writeValueAsString(patchUserRequest);

        mockMvc.perform(put(baseUri + "/" + id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(patchRequestJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value(userResponse.name()))
                .andExpect(jsonPath("$.surname", equalTo(userResponse.surname())))
                .andExpect(jsonPath("$.nickname", equalTo(userResponse.nickname())))
                .andExpect(jsonPath("$.email", equalTo(userResponse.email())))
                .andExpect(jsonPath("$.phoneNumber", equalTo(userResponse.phoneNumber())));
    }

    @Test
    void testDeleteUserById() throws Exception {
        mockMvc.perform(delete(baseUri + "/" + id))
                .andExpect(status().isNoContent());
    }
}