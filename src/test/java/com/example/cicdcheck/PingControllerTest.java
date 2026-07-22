package com.example.cicdcheck;

import org.junit.jupiter.api.DisplayName;
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
                .andExpect(content().string("pong v3"));

    }
    @Test
    @DisplayName("GET /api/charu should return expected greeting message")
    void checkApi1_ShouldReturnSuccessMessage() throws Exception {
        // Updated endpoint path to include /api prefix
        mockMvc.perform(get("/api/charu"))
                .andExpect(status().isOk())
                .andExpect(content().string("helloo BEBA!!!!!\uD83E\uDEE0 "));
    }
}