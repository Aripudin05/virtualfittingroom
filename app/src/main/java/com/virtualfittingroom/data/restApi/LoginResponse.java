package com.virtualfittingroom.data.restApi;

import com.virtualfittingroom.data.model.UserModel;

public class LoginResponse extends Response{
    LoginResponseData data;

    public LoginResponse(String status, String message, LoginResponseData data) {
        super(status, message);
        this.data = data;
    }

    public LoginResponseData getData() {
        return data;
    }

    public void setData(LoginResponseData data) {
        this.data = data;
    }

    public static class LoginResponseData{
        UserModel user;
        String authToken;

        public LoginResponseData(UserModel user, String authToken) {
            this.user = user;
            this.authToken = authToken;
        }

        public UserModel getUser() {
            return user;
        }

        public void setUser(UserModel user) {
            this.user = user;
        }

        public String getAuthToken() {
            return authToken;
        }

        public void setAuthToken(String authToken) {
            this.authToken = authToken;
        }
    }
}
