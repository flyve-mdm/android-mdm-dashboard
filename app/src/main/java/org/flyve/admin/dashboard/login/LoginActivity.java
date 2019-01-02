package org.flyve.admin.dashboard.login;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telecom.Call;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Toast;

import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.FormatStrategy;
import com.orhanobut.logger.Logger;
import com.orhanobut.logger.PrettyFormatStrategy;

import org.flyve.admin.dashboard.R;
import org.flyve.admin.dashboard.data.GLPIadapter;
import org.flyve.admin.dashboard.data.GLPIdata;
import org.flyve.admin.dashboard.ui.DeviceDetailActivity;
import org.flyve.admin.dashboard.ui.MainActivity;
import org.flyve.admin.dashboard.utils.FlyveLog;
import org.glpi.api.BuildConfig;
import org.glpi.api.GLPI;
import org.glpi.api.Routes;
import org.glpi.api.response.FullSessionModel;
import org.glpi.api.response.InitSession;
import org.glpi.api.utils.Helpers;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SortedList;

public class LoginActivity extends AppCompatActivity {

    public EditText userName;
    public EditText passWord;
    public Button login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        userName = findViewById(R.id.username);
        passWord = findViewById(R.id.password);
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
