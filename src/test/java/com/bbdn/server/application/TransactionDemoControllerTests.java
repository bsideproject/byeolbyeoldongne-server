package com.bbdn.server.application;

import com.bbdn.server.domain.entity.UserEntity;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;


@SpringBootTest
@AutoConfigureMockMvc
class TransactionDemoControllerTests {

    @Autowired
    private MockMvc mockMvc;

    private UserEntity userEntity;

    @BeforeEach
    public void setUp() {
        userEntity = UserEntity.builder()
                .email("tester@example.com")
                .password("1234qwer").build();
    }


    @Test
    void saveNewUserInfoWithDefault() throws Exception {

        ObjectMapper objectMapper = new ObjectMapper();

        mockMvc.perform(MockMvcRequestBuilders.post("/users")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(userEntity.toString())))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(content().string(containsString("{\"id\":1,\"email\":tester@example.com,\"password\":1234qwer}")));
    }

    @Test
    void saveNewUserInfoWithReadOnly() throws Exception {

        ObjectMapper objectMapper = new ObjectMapper();

        mockMvc.perform(MockMvcRequestBuilders.post("/users/failure")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(userEntity.toString())))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(content().string(containsString("{\"id\":1,\"email\":null,\"password\":null}")));
    }
}