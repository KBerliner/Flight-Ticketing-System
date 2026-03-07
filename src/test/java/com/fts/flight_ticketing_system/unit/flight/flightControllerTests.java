package com.fts.flight_ticketing_system.unit.flight;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

import com.fts.flight_ticketing_system.flight.FlightController;

@WebMvcTest(FlightController.class)
@AutoConfigureMockMvc
public class flightControllerTests {
    @Autowired
    MockMvc MockMvc;

    @Test
    void shouldHitFlightController() throws Exception {
        MockMvc.perform(get("/api/flights/")).andExpect(status().isOk());
    }
}