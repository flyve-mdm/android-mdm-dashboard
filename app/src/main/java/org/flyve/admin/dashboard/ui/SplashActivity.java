package org.flyve.admin.dashboard.ui;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import org.flyve.admin.dashboard.R;
import org.flyve.admin.dashboard.login.LoginActivity;

import androidx.appcompat.app.AppCompatActivity;


public class SplashActivity extends AppCompatActivity {

    private static final int SPLASH_TIME = 3000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                openActivity();
            }
        }, SPLASH_TIME);
    }

    private void openActivity() {
        Intent miIntent = new Intent(SplashActivity.this, LoginActivity.class);
        SplashActivity.this.startActivity(miIntent);
        SplashActivity.this.finish();
    }
}
