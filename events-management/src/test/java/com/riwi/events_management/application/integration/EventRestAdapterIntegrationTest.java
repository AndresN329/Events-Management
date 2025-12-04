package com.riwi.events_management.integration;

import com.riwi.events_management.AbstractIntegrationTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@AutoConfigureMockMvc
class EventRestAdapterIntegrationTest extends AbstractIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void createEvent_ShouldReturn200() throws Exception {
        String json = """
            {
              "name": "Concierto Test",
              "category": "MUSIC",
              "venueId": 1,
              "startDate": "2030-01-01T20:00:00",
              "endDate": "2030-01-01T22:00:00"
            }
            """;

        mockMvc.perform(post("/events")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Concierto Test"));
    }

    @Test
    void listEvents_ShouldReturn200AndArray() throws Exception {
        mockMvc.perform(get("/events")
                        .param("page", "0")
                        .param("size", "5"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content").isArray());
    }
}
