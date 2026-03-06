package com.fts.flight_ticketing_system.unit.user;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;

import java.util.HashMap;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;
import java.util.zip.DataFormatException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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
        validUserMap.put("password", "Password");
    }

    @Test
    void shouldGetNoUsers_FromEmptyDB() {
        List users = userService.getAllUsers();

        assertEquals(0, users.size());
    }

    @Test
    void shouldAddAUserToDB() throws DataFormatException {
        userService.createUser(user);

        List<HashMap<String, Object>> users = userService.getAllUsers();

        HashMap<String, Object> user = users.get(0);

        assertEquals(1, users.size());
        assertEquals(user, user);
    }

    @Test
    void shouldRetrieveOneUserFromDB() throws DataFormatException {
        User userTwo = new User("userTwo", "firstName", "lastName", "second@gmail.com", "secondPassword");
        
        userService.createUser(user);
        userService.createUser(userTwo);



        User retrievedUser = userService.getUser(user.getId());

        assertEquals(user, retrievedUser);
        assertNull(retrievedUser.getPassword());
    }

    @Test
    void shouldThrowNotFoundError_IfNoUserExists() {
        assertThrows(NoSuchElementException.class, () -> {
            UUID id = UUID.randomUUID();
            
            userService.getUser(id);
        });
    }

    @Test
    void shouldUpdateOneUser() throws DataFormatException {
        userService.createUser(user);

        HashMap<String, Object> updates = new HashMap<>();

        updates.put("username", "New Username");
        updates.put("firstName", "FirstName");

        userService.updateUser(user.getId(), updates);

        User newUser = userService.getUser(user.getId());

        assertEquals("New Username", newUser.getUsername());
        assertEquals("FirstName", newUser.getFirstName());
        assertEquals("Last", newUser.getLastName());
    }

    @Test
    void shouldDeleteUser() throws DataFormatException {
        userService.createUser(user);

        assertEquals(user, userService.getUser(user.getId()));

        userService.deleteUser(user.getId());

        assertEquals(0, userService.getAllUsers().size());
    }

    @Test
    void shouldReturnFalse_WithValidInput() {
        Boolean validResult = userService.isNotValidUserInput(validUserMap);

        assertFalse(validResult);
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

    // @Test
    // void 
}
