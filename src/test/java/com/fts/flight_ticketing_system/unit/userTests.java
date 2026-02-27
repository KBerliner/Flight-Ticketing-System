package com.fts.flight_ticketing_system.unit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.HashMap;
import java.util.UUID;
import java.util.zip.DataFormatException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.fts.flight_ticketing_system.User;

public class userTests {
    User user;

    @BeforeEach
    void setUp() throws DataFormatException {
        String username = "exampleUsername";
        String firstName = "First";
        String lastName = "Last";
        String email = "example@gmail.com";
        String password = "Password1234";
        
        user = new User(username, firstName, lastName, email, password);
    }

    @Test
    void shouldInitializeUser_AsActiveWithNoMiles() {

        assertTrue(user.getActiveStatus());

        assertInstanceOf(UUID.class, user.getId());
        assertEquals(0, user.getMiles());
        assertEquals("exampleUsername", user.getUsername());
        assertEquals("First", user.getFirstName());
        assertEquals("Last", user.getLastName());
        assertEquals("example@gmail.com", user.getEmail());
    }

    @Test
    void shouldVerifyEmailFormat_OnInitialization() {
        assertThrows(DataFormatException.class, () -> {
            new User("", "", "", "", "");
        });

        assertThrows(DataFormatException.class, () -> {
            new User("","","","emailgmail.com", "");
        });

        assertThrows(DataFormatException.class, () -> {
            new User("", "", "", "email@gmail", "");
        });
    }

    @Test
    void shouldHashPassword_OnInitialization() throws DataFormatException {
        assertNotEquals("Password1234", user.Password());
    }

    @Test
    void shouldVerifyPasswordCorrectly() throws DataFormatException {
        assertTrue(user.comparePassword("Password1234"));

        assertFalse(user.comparePassword("123412341234"));
    }

    @Test
    void shouldReturnUserAsHashMap() throws DataFormatException {
        assertInstanceOf(HashMap.class, user.getUserAsHashMap());
    }

    @Test
    void shouldAddMiles() {
        user.addMiles(10.0);

        assertEquals(10.0, user.getMiles());
    }
}
