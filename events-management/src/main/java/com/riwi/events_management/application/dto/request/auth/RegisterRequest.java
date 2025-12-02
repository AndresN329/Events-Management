package com.riwi.events_management.application.dto.request.auth;

public class RegisterRequest {

    private String username;
    private String password;

    // GETTERS & SETTERS

    public String getUsername() {   // <-- ESTE MÉTODO ES NECESARIO
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {   // Necesario para login
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
