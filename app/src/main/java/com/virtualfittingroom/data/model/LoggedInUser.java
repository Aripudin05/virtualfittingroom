package com.virtualfittingroom.data.model;

/**
 * Data class that captures user information for logged in users retrieved from LoginRepository
 */
public class LoggedInUser {

    private String email;
    private String name;
    private String avatar;

    public LoggedInUser(String email, String name, String avatar) {
        this.email = email;
        this.name = name;
        this.avatar = avatar;
    }

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }

    public String getAvatar() {
        return avatar;
    }
}