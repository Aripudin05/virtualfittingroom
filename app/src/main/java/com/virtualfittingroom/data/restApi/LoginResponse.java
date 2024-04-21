package com.virtualfittingroom.data.restApi;

import com.virtualfittingroom.data.model.UserModel;

public class LoginResponse extends Response{
    private UserModel user;

    public LoginResponse(String status, String message, UserModel user) {
        super(status, message);
        this.user = user;
    }

    public UserModel getUser() {
        return user;
    }

    public void setUser(UserModel user) {
        this.user = user;
    }
}
