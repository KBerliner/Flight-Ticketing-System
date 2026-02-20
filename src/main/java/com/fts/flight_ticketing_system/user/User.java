package com.fts.flight_ticketing_system.user;

import java.util.HashMap;
import java.util.zip.DataFormatException;

import org.springframework.security.crypto.bcrypt.BCrypt;



public class User {
    private Boolean active;
    private String username;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private Integer miles;

    /**
     * @param username
     * @param firstName
     * @param lastName
     * @param email
     * @param password
     * @throws DataFormatException
     */
    public User(String username, String firstName, String lastName, String email, String password) throws DataFormatException {
        active = true;
        miles = 0;
        
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;

        // Email Format Validation
        if (!email.matches("^[^@]+@[^@]+\\.[^@]+$")) {
            throw new DataFormatException();
        }
        this.email = email;

        // Password hashing
        String password_salt = BCrypt.gensalt(12);
        this.password = BCrypt.hashpw(password, password_salt);
    }
    
    public String getUsername() {
        return username;
    }

    public Boolean getActiveStatus() {
        return active;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public String Password() {
        return password;
    }

    public Boolean comparePassword(String password) {
        return BCrypt.checkpw(password, this.password);
    }

    public Integer getMiles() {
        return miles;
    }

    public HashMap<String, String> getUserAsHashMap() {
        HashMap<String, String> userAsHashMap = new HashMap<>();

        userAsHashMap.put("username", username);
        userAsHashMap.put("firstName", firstName);
        userAsHashMap.put("lastName", lastName);
        userAsHashMap.put("email", email);
        userAsHashMap.put("password", password);

        return userAsHashMap;
    }
}
