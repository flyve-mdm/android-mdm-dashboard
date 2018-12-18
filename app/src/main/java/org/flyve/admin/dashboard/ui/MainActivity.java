package org.flyve.admin.dashboard.ui;

import android.content.ClipData;
import android.os.Bundle;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import org.flyve.admin.dashboard.R;
import org.flyve.admin.dashboard.adapter.CardViewAdapter;
import org.flyve.admin.dashboard.adapter.DrawerAdapter;
import org.flyve.admin.dashboard.utils.FlyveLog;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class MainActivity extends AppCompatActivity {

    private DrawerLayout drawerLayout;
    private FragmentManager mFragmentManager;
    private ListView lstDrawer;
    private ArrayList<HashMap<String, String>> arrDrawer;
    private HashMap<String, String> selectedItem;
    private TextView txtToolbarTitle;

    private RecyclerView mRecyclerView;
    private CardViewAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    private ArrayList<CardView> cardList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        createExampleList();
        buildRecyclerView();

        // Setup the DrawerLayout and NavigationView
        txtToolbarTitle = findViewById(R.id.txtToolbarTitle);
        drawerLayout = findViewById(R.id.drawerLayout);

        lstDrawer = findViewById(R.id.lstNV);
        lstDrawer.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                drawerLayout.closeDrawers();
                selectedItem = arrDrawer.get(position);
                loadFragment(selectedItem);
            }
        });

        mFragmentManager = getSupportFragmentManager();

        // Setup Drawer Toggle of the Toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        ActionBarDrawerToggle mDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar,R.string.app_name,
                R.string.app_name);

        drawerLayout.setDrawerListener(mDrawerToggle);

        mDrawerToggle.syncState();

        loadListDrawer();
    }

    /**
     * Loads card views
     */
    public  void createExampleList(){
        cardList = new ArrayList<>();
        cardList.add(new CardView(R.drawable.ic_devices, "Devices", "0"));
        cardList.add(new CardView(R.drawable.ic_fleets, "Fleets", "0"));
        cardList.add(new CardView(R.drawable.ic_files,"Files","0"));
        cardList.add(new CardView(R.drawable.ic_applications, "Applications", "0"));
        cardList.add(new CardView(R.drawable.ic_users,"Users","0"));
    }

    public void buildRecyclerView(){
        mRecyclerView = findViewById(R.id.recycler_view);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mAdapter = new CardViewAdapter(cardList);

        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);

        mAdapter.setOnItemClickListener(new CardViewAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                FragmentTransaction fragmentTransaction = mFragmentManager.beginTransaction();
                // Devices
                if (position == 0) {
                    DeviceFragment f = new DeviceFragment();
                    fragmentTransaction.replace(R.id.containerView, f).commit();
                    mRecyclerView.setVisibility(View.GONE);
                    txtToolbarTitle.setText("Devices");
                    return;
                }

                // Fleets
                if (position == 1) {
                    FleetFragment f = new FleetFragment();
                    fragmentTransaction.replace(R.id.containerView, f).commit();
                    mRecyclerView.setVisibility(View.GONE);
                    txtToolbarTitle.setText("Fleets");
                    return;
                }

                // Files
                if (position == 2) {
                    FileFragment f = new FileFragment();
                    fragmentTransaction.replace(R.id.containerView, f).commit();
                    mRecyclerView.setVisibility(View.GONE);
                    txtToolbarTitle.setText("Files");
                    return;
                }

                // Application
                if (position == 3) {
                    ApplicationFragment f = new ApplicationFragment();
                    fragmentTransaction.replace(R.id.containerView, f).commit();
                    mRecyclerView.setVisibility(View.GONE);
                    txtToolbarTitle.setText("Application");
                    return;
                }

                // User
                if (position == 4) {
                    UserFragment f = new UserFragment();
                    fragmentTransaction.replace(R.id.containerView, f).commit();
                    mRecyclerView.setVisibility(View.GONE);
                    txtToolbarTitle.setText("User");
                    return;
                }
            }
        });
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
            mRecyclerView.setVisibility(View.VISIBLE);
            return;
        }

        // Devices
        if (item.get("id").equals("2")) {
            DeviceFragment f = new DeviceFragment();
            fragmentTransaction.replace(R.id.containerView, f).commit();
            mRecyclerView.setVisibility(View.GONE);
            return;
        }

        // Fleets
        if (item.get("id").equals("3")) {
            FleetFragment f = new FleetFragment();
            fragmentTransaction.replace(R.id.containerView, f).commit();
            mRecyclerView.setVisibility(View.GONE);
            return;
        }

        // Files
        if (item.get("id").equals("4")) {
            FileFragment f = new FileFragment();
            fragmentTransaction.replace(R.id.containerView, f).commit();
            mRecyclerView.setVisibility(View.GONE);
            return;
        }

        // Application
        if (item.get("id").equals("5")) {
            ApplicationFragment f = new ApplicationFragment();
            fragmentTransaction.replace(R.id.containerView, f).commit();
            mRecyclerView.setVisibility(View.GONE);
            return;
        }

        // User
        if (item.get("id").equals("6")) {
            UserFragment f = new UserFragment();
            fragmentTransaction.replace(R.id.containerView, f).commit();
            mRecyclerView.setVisibility(View.GONE);
            return;
        }

        // Settings
        if (item.get("id").equals("7")){
            SettingsFragment f = new SettingsFragment();
            fragmentTransaction.replace(R.id.containerView, f).commit();
            mRecyclerView.setVisibility(View.GONE);
        }
        // About
        if (item.get("id").equals("8")) {
            FragmentAbout f = new FragmentAbout();
            fragmentTransaction.replace(R.id.containerView, f).commit();
            mRecyclerView.setVisibility(View.GONE);
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
