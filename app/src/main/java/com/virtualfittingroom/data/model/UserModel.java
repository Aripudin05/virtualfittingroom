package com.virtualfittingroom.data.model;

public class UserModel {
    private String name, email, avatar, authToken;

    public UserModel(String name, String email, String avatar, String authToken) {
        this.name = name;
        this.email = email;
        this.avatar = avatar;
        this.authToken = authToken;
    }

    public String getAuthToken() {
        return authToken;
    }

    public void setAuthToken(String authToken) {
        this.authToken = authToken;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }
}
