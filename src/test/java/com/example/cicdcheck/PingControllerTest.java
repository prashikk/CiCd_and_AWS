package com.example.cicdcheck;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(checkController.class)
class PingControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Test
    void shouldReturnPong() throws Exception {

        mockMvc.perform(get("/api/ping"))
                .andExpect(status().isOk())
                .andExpect(content().string("pong v2"));

    }
}