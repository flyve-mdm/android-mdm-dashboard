package org.flyve.admin.dashboard;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import org.flyve.admin.dashboard.adapter.DrawerAdapter;
import org.flyve.admin.dashboard.utils.FlyveLog;

import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    private DrawerLayout mDrawerLayout;
    private FragmentManager mFragmentManager;
    private ListView lstDrawer;
    private ArrayList<HashMap<String, String>> arrDrawer;
    private HashMap<String, String> selectedItem;
    private TextView txtToolbarTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Setup the DrawerLayout and NavigationView
        txtToolbarTitle = (TextView) findViewById(R.id.txtToolbarTitle);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);

        lstDrawer = (ListView) findViewById(R.id.lstNV);
        lstDrawer.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                mDrawerLayout.closeDrawers();
                selectedItem = arrDrawer.get(position);
                loadFragment(selectedItem);
            }
        });

        mFragmentManager = getSupportFragmentManager();

        // Setup Drawer Toggle of the Toolbar
        android.support.v7.widget.Toolbar toolbar = (android.support.v7.widget.Toolbar) findViewById(R.id.toolbar);
        ActionBarDrawerToggle mDrawerToggle = new ActionBarDrawerToggle(this,mDrawerLayout, toolbar,R.string.app_name,
                R.string.app_name);

        mDrawerLayout.setDrawerListener(mDrawerToggle);

        mDrawerToggle.syncState();

        loadListDrawer();
    }

    /**
     * Loads the Fragment
     * @param item
     */
    private void loadFragment(HashMap<String, String> item) {

        FragmentTransaction fragmentTransaction = mFragmentManager.beginTransaction();

        txtToolbarTitle.setText(item.get("name").toUpperCase());

        // Dashboard
        if (item.get("id").equals("1")) {
            DashboardFragment f = new DashboardFragment();
            fragmentTransaction.replace(R.id.containerView, f).commit();
            return;
        }

        // Devices
        if (item.get("id").equals("2")) {
            DeviceFragment f = new DeviceFragment();
            fragmentTransaction.replace(R.id.containerView, f).commit();
            return;
        }

        // Fleets
        if (item.get("id").equals("3")) {
            FleetFragment f = new FleetFragment();
            fragmentTransaction.replace(R.id.containerView, f).commit();
            return;
        }

        // Files
        if (item.get("id").equals("4")) {
            FileFragment f = new FileFragment();
            fragmentTransaction.replace(R.id.containerView, f).commit();
            return;
        }

        // User
        if (item.get("id").equals("6")) {
            UserFragment f = new UserFragment();
            fragmentTransaction.replace(R.id.containerView, f).commit();
            return;
        }

        // About
        if (item.get("id").equals("8")) {
            FragmentAbout f = new FragmentAbout();
            fragmentTransaction.replace(R.id.containerView, f).commit();
        }
    }

    /**
     * Load the list drawer
     */
    public void loadListDrawer() {

        arrDrawer = new ArrayList<>();

        // Dashboard
        HashMap<String, String> map = new HashMap<>();
        map.put("id", "1");
        map.put("name", getResources().getString(R.string.drawer_dashboard));
        map.put("img", "ic_dashboard");
        arrDrawer.add(map);

        // Devices
        map = new HashMap<>();
        map.put("id", "2");
        map.put("name", getResources().getString(R.string.drawer_devices));
        map.put("img", "ic_devices");
        arrDrawer.add(map);

        // Fleets
        map = new HashMap<>();
        map.put("id", "3");
        map.put("name", getResources().getString(R.string.drawer_fleets));
        map.put("img", "ic_fleets");
        arrDrawer.add(map);

        // Files
        map = new HashMap<>();
        map.put("id", "4");
        map.put("name", getResources().getString(R.string.drawer_files));
        map.put("img", "ic_files");
        arrDrawer.add(map);

        // Applications
        map = new HashMap<>();
        map.put("id", "5");
        map.put("name", getResources().getString(R.string.drawer_applications));
        map.put("img", "ic_applications");
        arrDrawer.add(map);

        // User
        map = new HashMap<>();
        map.put("id", "6");
        map.put("name", getResources().getString(R.string.drawer_users));
        map.put("img", "ic_users");
        arrDrawer.add(map);

        // Settings
        map = new HashMap<>();
        map.put("id", "7");
        map.put("name", getResources().getString(R.string.drawer_settings));
        map.put("img", "ic_settings");
        map.put("separator", "true");
        arrDrawer.add(map);

        // About
        map = new HashMap<>();
        map.put("id", "8");
        map.put("name", getResources().getString(R.string.drawer_about));
        map.put("img", "ic_about");
        arrDrawer.add(map);

        try {
            // lad adapter
            DrawerAdapter adapter = new DrawerAdapter(this, arrDrawer);
            lstDrawer.setAdapter(adapter);

            // Select Information on load //
            selectedItem = arrDrawer.get(0);
            loadFragment(selectedItem);
        } catch(Exception ex) {
            FlyveLog.e(ex.getMessage());
        }
    }

}
