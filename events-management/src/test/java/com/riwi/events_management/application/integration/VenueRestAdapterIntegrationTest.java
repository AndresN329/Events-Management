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
class VenueRestAdapterIntegrationTest extends AbstractIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void createVenue_ShouldReturn200() throws Exception {
        String json = """
            {
              "name": "Auditorio Central",
              "address": "Bogotá",
              "minCapacity": 100,
              "maxCapacity": 5000
            }
            """;

        mockMvc.perform(post("/venues")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Auditorio Central"))
                .andExpect(jsonPath("$.address").value("Bogotá"))
                .andExpect(jsonPath("$.minCapacity").value(100))
                .andExpect(jsonPath("$.maxCapacity").value(5000));
    }

    @Test
    void listVenues_ShouldReturnPage() throws Exception {
        mockMvc.perform(get("/venues")
                        .param("page", "0")
                        .param("size", "5"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content").isArray());
    }
}
