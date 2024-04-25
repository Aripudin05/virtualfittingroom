package com.virtualfittingroom.ui.login;

import android.content.Context;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.annotation.NonNull;

import com.virtualfittingroom.data.LoginDataSource;
import com.virtualfittingroom.data.LoginRepository;
import com.virtualfittingroom.data.restApi.AuthApi;

/**
 * ViewModel provider factory to instantiate LoginViewModel.
 * Required given LoginViewModel has a non-empty constructor
 */
public class LoginViewModelFactory implements ViewModelProvider.Factory {
    public Context appContext;
    public LoginViewModelFactory(Context context) {
        this.appContext = context;
    }

    @NonNull
    @Override
    @SuppressWarnings("unchecked")
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(LoginViewModel.class)) {
            return (T) new LoginViewModel(LoginRepository.getInstance(new AuthApi(appContext)));
        } else {
            throw new IllegalArgumentException("Unknown ViewModel class");
        }
    }
}