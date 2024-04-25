package com.virtualfittingroom.ui.splash;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.virtualfittingroom.R;
import com.virtualfittingroom.data.restApi.AuthApi;
import com.virtualfittingroom.data.restApi.LoginResponse;

import java.util.UUID;

public class SplashScreen extends AppCompatActivity {
    private static final String TAG = "ActivitySplashScreen";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_splash_screen);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // TODO: check rather if user is logged on
        AuthApi authApi = new AuthApi(getBaseContext());

        authApi.login(
                "4mail@site.com",
                "password",
                new AuthApi.LoginCallback() {
                    @Override
                    public void onSuccess(LoginResponse loginResponse) {
                        Log.d(TAG, "onSuccess: Login success");
                        Log.d(TAG, "onSuccess: " + loginResponse.getData().getUser().getName());
                    }

                    @Override
                    public void onError(Throwable t) {
                        Log.d(TAG, "onError: ", t);
                    }
                });
    }
}