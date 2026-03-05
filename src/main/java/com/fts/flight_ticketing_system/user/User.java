package com.fts.flight_ticketing_system.user;

import java.util.HashMap;
import java.util.Set;
import java.util.UUID;
import java.util.zip.DataFormatException;

import org.springframework.security.crypto.bcrypt.BCrypt;



public class User {
    private UUID id;
    private Boolean active;
    private String username;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private Double miles;

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
        miles = 0.0;
        
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;

        // Email Format Validation
        if (!isValidEmail(email)) {
            throw new DataFormatException();
        } else {
            this.email = email;
        }

        // Password hashing
        this.password = hashPassword(password);

        // ID Generation
        this.id = UUID.randomUUID();
    }

    private Boolean isValidEmail(String email) {
        return email.matches("^[^@]+@[^@]+\\.[^@]+$");
    }

    private String hashPassword(String password) {
        String password_salt = BCrypt.gensalt(12);
        return BCrypt.hashpw(password, password_salt);
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

    public String getPassword() {
        return password;
    }

    public Boolean comparePassword(String password) {
        return BCrypt.checkpw(password, this.password);
    }

    public Double getMiles() {
        return miles;
    }

    public HashMap<String, Object> getUserAsHashMap() {
        HashMap<String, Object> userAsHashMap = new HashMap<>();

        userAsHashMap.put("id", id);
        userAsHashMap.put("active", active);
        userAsHashMap.put("username", username);
        userAsHashMap.put("firstName", firstName);
        userAsHashMap.put("lastName", lastName);
        userAsHashMap.put("email", email);
        userAsHashMap.put("miles", miles);

        return userAsHashMap;
    }

    public UUID getId() {
        return this.id;
    }

    public void addMiles(Double miles) {
        this.miles += miles;
    }

    public void removeMiles(Double miles) {
        if (this.miles >= miles) this.miles -= miles;
    }

    public void update(HashMap<String, String> newDetails) {
        Set<String> keys = newDetails.keySet();

        keys.forEach((key) -> {
            switch (key) {
                case "username":
                    setUsername(newDetails.get(key));
                    break;
            
                case "firstName":
                    setFirstName(newDetails.get(key));
                    break;

                case "lastName":
                    setLastName(newDetails.get(key));
                    break;

                case "email":
                    if (isValidEmail(newDetails.get(key))) setEmail(newDetails.get(key));
                    break;

                case "password":
                    setPassword(hashPassword(newDetails.get(key)));
                    break;

                default:
                    break;
            }
        });
    }

    private void setPassword(String newPassword) {
        

        this.password = newPassword;
    }

    private void setEmail(String newEmail) {
        this.email = newEmail;
    }

    private void setLastName(String newLastName) {
        this.lastName = newLastName;
    }

    private void setFirstName(String newFirstName) {
        this.firstName = newFirstName;
    }

    private void setUsername(String newUsername) {
        this.username = newUsername;
    }
}
