package org.flyve.admin.dashboard.login;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import org.flyve.admin.dashboard.R;
import org.flyve.admin.dashboard.ui.MainActivity;
import org.flyve.admin.dashboard.utils.FlyveLog;
import org.glpi.api.BuildConfig;
import org.glpi.api.GLPI;
import org.glpi.api.response.InitSession;

import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {

    EditText username, password;
    Button login;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        login = findViewById(R.id.login);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent myIntent = new Intent (getApplicationContext(), MainActivity.class);
                LoginActivity.this.startActivity(myIntent);
                LoginActivity.this.finish();
            }
        });

    }


}
