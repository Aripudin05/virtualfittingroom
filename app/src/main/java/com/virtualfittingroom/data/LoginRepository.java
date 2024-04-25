package com.virtualfittingroom.data;

import com.virtualfittingroom.data.model.LoggedInUser;
import com.virtualfittingroom.data.model.UserModel;
import com.virtualfittingroom.data.restApi.AuthApi;
import com.virtualfittingroom.data.restApi.LoginResponse;

/**
 * Class that requests authentication and user information from the remote data source and
 * maintains an in-memory cache of login status and user credentials information.
 */
public class LoginRepository {

    private static volatile LoginRepository instance;

    private AuthApi authApi;

    // If user credentials will be cached in local storage, it is recommended it be encrypted
    // @see https://developer.android.com/training/articles/keystore
    private UserModel user = null;

    public LoginRepository(AuthApi authApi) {
        this.authApi = authApi;
    }

    public static LoginRepository getInstance(AuthApi authApi) {
        if(instance == null){
            instance = new LoginRepository(authApi);
        }
        return instance;
    }

    public void login(String username, String password, RepositoryOnLoginCallback repositoryOnLoginCallback) {
        // handle login
        // get login remote
        this.authApi.login(
                username,
                password,
                new AuthApi.LoginCallback() {
                    @Override
                    public void onSuccess(LoginResponse loginResponse) {
                        setLoggedInUser(loginResponse.getData().getUser());
                        repositoryOnLoginCallback.onSuccess(loginResponse.getData().getUser());
                    }

                    @Override
                    public void onError(Throwable t) {
                        repositoryOnLoginCallback.onFail(t);
                    }
                }
        );
    }

    public void logout() {
        user = null;
        // remote logout
        // local
        setLoggedInUser(null);
    }

    public boolean isLoggedIn() {
        return user != null;
    }

    private void setLoggedInUser(UserModel user) {
        this.user = user;
        // If user credentials will be cached in local storage, it is recommended it be encrypted
        // @see https://developer.android.com/training/articles/keystore
//      TODO: SAVE LOCAL

    }

    private UserModel getSavedUser(){

        return null;
    }

    private void syncAuth(){
        // get local

        // check remote
    }

    public interface RepositoryOnLoginCallback{
        void onSuccess(UserModel user);
        void onFail(Throwable t);
    }
}