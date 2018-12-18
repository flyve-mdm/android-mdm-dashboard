package org.flyve.admin.dashboard.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.flyve.admin.dashboard.R;

import androidx.fragment.app.Fragment;

public class DashboardFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_dashboard, container, false);
        return v;
    }


}
