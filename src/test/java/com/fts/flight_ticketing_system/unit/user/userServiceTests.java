package com.fts.flight_ticketing_system.unit.user;

import static org.junit.Assert.assertEquals;

import java.util.HashMap;
import java.util.UUID;
import java.util.zip.DataFormatException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.fts.flight_ticketing_system.database.Row;
import com.fts.flight_ticketing_system.user.UserService;

public class userServiceTests {
    UserService userService;

    @BeforeEach
    void setUp() throws DataFormatException {
        userService = new UserService();
    }

    @Test
    void shouldGetNoUsers_FromEmptyDB() {
        HashMap<UUID, Row> users = userService.getAllUsers();

        assertEquals(0, users.size());
    }
}
