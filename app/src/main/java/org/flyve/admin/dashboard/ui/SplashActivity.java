package org.flyve.admin.dashboard.ui;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

import org.flyve.admin.dashboard.R;

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
        Intent miIntent = new Intent(SplashActivity.this, MainActivity.class);
        SplashActivity.this.startActivity(miIntent);
        SplashActivity.this.finish();
    }
}
