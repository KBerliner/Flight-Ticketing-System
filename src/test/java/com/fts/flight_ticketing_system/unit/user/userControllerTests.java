package com.fts.flight_ticketing_system.unit.user;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fts.flight_ticketing_system.user.UserController;

@WebMvcTest(UserController.class)
@AutoConfigureMockMvc
public class userControllerTests {
    @Autowired
    MockMvc MockMvc;

    @Test
    void shouldHitUserController() throws Exception {
        MockMvc.perform(get("/api/users/")).andExpect(status().isOk());
    }
}
