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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.zip.DataFormatException;

import org.json.simple.parser.JSONParser;
import org.json.simple.JSONObject;

import com.fts.flight_ticketing_system.user.User;
import com.fts.flight_ticketing_system.user.UserController;

@WebMvcTest(UserController.class)
@AutoConfigureMockMvc
public class userControllerTests {
    @Autowired
    MockMvc MockMvc;

    private JSONParser jsonParser;
    private JSONObject jObject;

    @BeforeEach
    void setUp() throws DataFormatException {
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
        // Setup with a user to get
        HashMap<String, String> mappedUser = new HashMap<>();
        mappedUser.put("username", "Username");
        mappedUser.put("firstName", "First");
        mappedUser.put("lastName", "Last");
        mappedUser.put("email", "email@gmail.com");
        mappedUser.put("password", "Password");
        
        jObject = new JSONObject(mappedUser);
        
        String content = jObject.toJSONString();
        
        MockMvc.perform(post("/api/users/").contentType(MediaType.APPLICATION_JSON).content(content));

        // MockMvc.perform(
        //     get("/api/users/{id}", user.getId())
        // ).andExpect(status().isOk())
        // .andExpect(content().string("Hello"));
    }

    @Test
    void shouldGetAllUsers() throws Exception {
        // GET Request that expects an OK status and returns the response as a String
        MockMvc.perform(get("/api/users/"))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$[0].password").doesNotHaveJsonPath())
        .andExpect(jsonPath("$[1].password").doesNotHaveJsonPath());
    }
}
