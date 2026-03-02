package com.fts.flight_ticketing_system.unit.user;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.HashMap;
import java.util.UUID;
import java.util.zip.DataFormatException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.fts.flight_ticketing_system.database.Row;
import com.fts.flight_ticketing_system.user.User;
import com.fts.flight_ticketing_system.user.UserService;

public class userServiceTests {
    UserService userService;
    User user;
    HashMap<String, Object> validUserMap;

    @BeforeEach
    void setUp() throws DataFormatException {
        userService = new UserService();

        user = new User("Username", "First", "Last", "email@gmail.com", "Password");
        validUserMap = user.getUserAsHashMap();
    }

    @Test
    void shouldGetNoUsers_FromEmptyDB() {
        HashMap<UUID, Row> users = userService.getAllUsers();

        assertEquals(0, users.size());
    }

    @Test
    void shouldAddAUserToDB() throws DataFormatException {
        userService.createUser(user);

        HashMap<UUID, Row> users = userService.getAllUsers();

        assertEquals(1, users.size());
        assertEquals(user.getUserAsHashMap(), users.get(user.getId()).getColumnValuesMap());
    }

    @Test
    void shouldRetrieveOneUserFromDB() throws DataFormatException {
        User userTwo = new User("userTwo", "firstName", "lastName", "second@gmail.com", "secondPassword");
        
        userService.createUser(user);
        userService.createUser(userTwo);

        HashMap<String, Object> retrievedUser = userService.getUser(user.getId());

        assertEquals(user.getUserAsHashMap(), retrievedUser);
    }

    @Test
    void shouldReturnEmptyHashMap_IfNoUserExists() {
        UUID id = UUID.randomUUID();

        HashMap<String, Object> result = userService.getUser(id);

        assertEquals(0, result.size());
    }

    @Test
    void shouldUpdateOneUser() throws DataFormatException {
        userService.createUser(user);

        HashMap<String, Object> updates = new HashMap<>();

        updates.put("username", "New Username");
        updates.put("firstName", "First");

        userService.updateUser(user.getId(), updates);

        HashMap<String, Object> newUser = userService.getUser(user.getId());

        assertEquals("New Username", newUser.get("username"));
        assertEquals("First", newUser.get("firstName"));
        assertEquals("null", newUser.get("lastName"));
    }

    @Test
    void shouldDeleteUser() throws DataFormatException {
        userService.createUser(user);

        assertEquals(user.getUserAsHashMap(), userService.getUser(user.getId()));

        userService.deleteUser(user.getId());

        assertEquals(0, userService.getAllUsers().size());
    }

    @Test
    void shouldReturnFalse_WithValidInput() {
        Boolean validResult = userService.isNotValidUserInput(validUserMap);

        assertFalse(validResult);
    }

    @Test
    void shouldReturnTrue_IfInvalidInput_USERNAME() {
        String USERNAME = "username";

        validUserMap.remove(USERNAME);
        
        Boolean nullResult = userService.isNotValidUserInput(validUserMap);

        validUserMap.put("username", "");

        Boolean emptyResult = userService.isNotValidUserInput(validUserMap);

        assertTrue(nullResult);
        assertTrue(emptyResult);
    }

    @Test
    void shouldReturnTrue_IfInvalidInput() {
        String[] KEYS = {"username", "firstName", "lastName", "email", "password"};

        for (String key : KEYS) {
            Object originalValue = validUserMap.get(key);

            validUserMap.remove(key);
            Boolean nullResult = userService.isNotValidUserInput(validUserMap);

            validUserMap.put(key, "");
            Boolean emptyResult = userService.isNotValidUserInput(validUserMap);

            assertTrue(nullResult);
            assertTrue(emptyResult);

            // RESET HASHMAP
            validUserMap.put(key, originalValue);
        }
    }
}
