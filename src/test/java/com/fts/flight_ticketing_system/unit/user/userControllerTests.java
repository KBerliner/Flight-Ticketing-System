package com.fts.flight_ticketing_system.unit.user;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.UUID;

import org.json.simple.parser.JSONParser;
import org.json.simple.JSONObject;

import com.fts.flight_ticketing_system.database.Row;
import com.fts.flight_ticketing_system.user.UserController;

@WebMvcTest(UserController.class)
@AutoConfigureMockMvc
public class userControllerTests {
    @Autowired
    MockMvc MockMvc;

    private JSONParser jsonParser;
    private JSONObject jObject;

    @BeforeEach
    void setUp() {
        jsonParser = new JSONParser();
    }

    @Test
    void shouldHitUserController() throws Exception {
        MockMvc.perform(get("/api/users/")).andExpect(status().isOk());
    }
    
    @Test
    void shouldCreateAUser() throws UnsupportedEncodingException, Exception {
        HashMap<String, String> mappedUser = new HashMap<>();
        mappedUser.put("username", "Username");
        mappedUser.put("firstName", "First");
        mappedUser.put("lastName", "Last");
        mappedUser.put("email", "email@gmail.com");
        mappedUser.put("password", "Password");
        
        jObject = new JSONObject(mappedUser);
        
        String content = jObject.toJSONString();
        
        String result = MockMvc.perform(
            post("/api/users/")
            .contentType(MediaType.APPLICATION_JSON)
            .content(content)
            
        ).andExpect(status().isCreated()).andReturn().getResponse().getContentAsString();
        
        @SuppressWarnings("unchecked")
        HashMap<String, Object> jsonObject = (HashMap<String, Object>) jsonParser.parse(result);
        
        assertEquals("Username", jsonObject.get("username"));
        assertNotNull(jsonObject.get("id"));
    }
    
    @Test
    void shouldGetOneUserById() throws UnsupportedEncodingException, Exception {
        HashMap<String, String> mappedUser = new HashMap<>();
        mappedUser.put("username", "Username");
        mappedUser.put("firstName", "First");
        mappedUser.put("lastName", "Last");
        mappedUser.put("email", "email@gmail.com");
        mappedUser.put("password", "Password");
        jObject = new JSONObject(mappedUser);
        String content = jObject.toJSONString();
        
        String createdUser = MockMvc.perform(
            post("/api/users/")
            .contentType(MediaType.APPLICATION_JSON)
            .content(content)
        ).andExpect(status().isCreated()).andReturn().getResponse().getContentAsString();
        
        @SuppressWarnings("unchecked")
        HashMap<String, Object> createdUserJSON = (HashMap<String, Object>) jsonParser.parse(createdUser);
        
        String result = MockMvc.perform(
            get("/api/users/{id}", (String) createdUserJSON.get("id"))
        ).andExpect(status().isOk()).andReturn().getResponse().getContentAsString();
        
        @SuppressWarnings("unchecked")
        HashMap<String, Object> resultingJSON = (HashMap<String, Object>) jsonParser.parse(result);
        
        assertEquals("Username", resultingJSON.get("username"));
        assertEquals(createdUserJSON.get("id"), resultingJSON.get("id"));
    }

    @Test
    void shouldGetAllUsers() throws Exception {
    
        // GET Request that expects an OK status and returns the response as a String
        String result = MockMvc.perform(get("/api/users/"))
        .andExpect(status().isOk())
        .andReturn()
        .getResponse()
        .getContentAsString();
    
        // SuppressWarnings because technically parsing the result produces a general object, but it IS HashMap<UUID, Row>
        @SuppressWarnings("unchecked")
        HashMap<UUID, Row> jsonObject = (HashMap<UUID, Row>) jsonParser.parse(result);
    
        assertInstanceOf(HashMap.class, jsonObject);
        assertEquals(2, jsonObject.size());
    }
}
