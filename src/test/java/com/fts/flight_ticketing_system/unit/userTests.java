package com.fts.flight_ticketing_system.unit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.HashMap;
import java.util.zip.DataFormatException;

import org.junit.jupiter.api.Test;

import com.fts.flight_ticketing_system.user.User;

public class userTests {
    @Test
    void shouldPass() {
        assertTrue(true);
    }

    @Test
    void shouldInitializeUser_AsActiveWithNoMiles() throws DataFormatException {
        String username = "exampleUsername";
        String firstName = "First";
        String lastName = "Last";
        String email = "example@gmail.com";
        String password = "Password1234";

        User user = new User(username, firstName, lastName, email, password);

        assertTrue(user.getActiveStatus());

        assertEquals(0, user.getDistance());
        assertEquals(username, user.getUsername());
        assertEquals(firstName, user.getFirstName());
        assertEquals(lastName, user.getLastName());
        assertEquals(email, user.getEmail());
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
        User user = new User("", "", "", "email@gmail.com", "Password1234");

        assertNotEquals("Password1234", user.Password());
    }

    @Test
    void shouldVerifyPasswordCorrectly() throws DataFormatException {
        User user = new User(null, null, null, "email@gmail.com", "Password1234");

        assertTrue(user.comparePassword("Password1234"));

        assertFalse(user.comparePassword("123412341234"));
    }

    @Test
    void shouldReturnUserAsHashMap() throws DataFormatException {
        String username = "exampleUsername";
        String firstName = "First";
        String lastName = "Last";
        String email = "example@gmail.com";
        String password = "Password1234";

        User user = new User(username, firstName, lastName, email, password);

        assertInstanceOf(HashMap.class, user.getUserAsHashMap());
    }
}
