package com.virtualfittingroom.data.restApi;

public class LoginRequest {
    private String email, password, deviceId;

    public LoginRequest(String email, String password, String deviceId) {
        this.email = email;
        this.password = password;
        this.deviceId = deviceId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }
}
