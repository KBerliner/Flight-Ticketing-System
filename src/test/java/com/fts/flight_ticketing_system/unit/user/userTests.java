package com.fts.flight_ticketing_system.unit.user;

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

import com.fts.flight_ticketing_system.user.User;



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
        assertNotEquals("Password1234", user.getPassword());
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

    @Test
    void shouldRemoveMiles() {
        user.addMiles(150.0);

        user.removeMiles(10.0);

        assertEquals(140.0, user.getMiles());
    }

    @Test
    void shouldNotRemoveMilesIfUserDoesntHaveEnough() {
        user.addMiles(5.0);
        user.removeMiles(10.0);

        assertEquals(5.0, user.getMiles());
    }

    @Test
    void shouldUpdateUserDetails() {
        String notExpectedUsername = user.getUsername();
        String notExpectedFirstName = user.getFirstName();
        String notExpectedLastName = user.getLastName();
        String notExpectedEmail = user.getEmail();
        String notExpectedPassword = user.getPassword();

        HashMap<String, String> newDetails = new HashMap<>();

        newDetails.put("username", "NewUser");
        newDetails.put("firstName", "John");
        newDetails.put("lastName", "Doe");
        newDetails.put("email", "newemail@gmail.com");
        newDetails.put("password", "UpdatedPassw0rd");

        user.update(newDetails);

        String actualUsername = user.getUsername();
        String actualFirstName = user.getFirstName();
        String actualLastName = user.getLastName();
        String actualEmail = user.getEmail();
        String actualPassword = user.getPassword();

        assertNotEquals(notExpectedUsername, actualUsername);
        assertNotEquals(notExpectedFirstName, actualFirstName);
        assertNotEquals(notExpectedLastName, actualLastName);
        assertNotEquals(notExpectedEmail, actualEmail);
        assertNotEquals(notExpectedPassword, actualPassword);
    }

    @Test
    void updatingPassword_ShouldHashNewPassword() {
        HashMap<String, String> newDetails = new HashMap<>();
        String password = "thisisanewpassword";
        newDetails.put("password", password);

        user.update(newDetails);

        assertNotEquals(password, user.getPassword());
    }
}
