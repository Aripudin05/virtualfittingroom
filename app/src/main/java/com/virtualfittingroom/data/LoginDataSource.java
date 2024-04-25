package com.virtualfittingroom.data;

import com.virtualfittingroom.data.model.LoggedInUser;
import com.virtualfittingroom.data.restApi.AuthApi;
import com.virtualfittingroom.data.restApi.LoginResponse;

import java.io.IOException;

/**
 * Class that handles authentication w/ login credentials and retrieves user information.
 */
public class LoginDataSource {
    AuthApi authApi;

    public LoginDataSource(AuthApi authApi) {
        this.authApi = authApi;
    }

    public Result<LoggedInUser> login(String email, String password, String androidId) {

        try {
            // TODO: handle loggedInUser authentication
            this.authApi.login(
                    email,
                    password,
                    new AuthApi.LoginCallback() {
                        @Override
                        public void onSuccess(LoginResponse loginResponse) {

                        }

                        @Override
                        public void onError(Throwable t) {

                        }
                    });

            return null;
        } catch (Exception e) {
            return new Result.Error(new IOException("Error logging in", e));
        }
    }



    public void logout() {
        // TODO: revoke authentication
    }
}