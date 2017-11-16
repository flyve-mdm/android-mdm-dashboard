package org.flyve.admin.dashboard;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import org.flyve.admin.dashboard.adapter.FileAdapter;
import org.flyve.admin.dashboard.utils.FlyveLog;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;

public class FileFragment extends Fragment {

    private ProgressBar pb;
    private RecyclerView lst;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_file, container, false);

        pb = (ProgressBar) v.findViewById(R.id.pb);
        pb.setVisibility(View.VISIBLE);

        lst = v.findViewById(R.id.lst);

        LinearLayoutManager llm = new LinearLayoutManager(FileFragment.this.getActivity());
        lst.setLayoutManager(llm);

        load(loadJSONFromAsset());

        return v;
    }

    public String loadJSONFromAsset() {
        String json = null;
        try {
            InputStream is = getActivity().getAssets().open("json/files.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            FlyveLog.e(ex.getMessage());
            return null;
        }
        return json;
    }

    public void load(String jsonStr) {

        ArrayList<HashMap<String, String>> data = new ArrayList<HashMap<String, String>>();

        try {
            JSONObject json = new JSONObject(jsonStr);

            JSONArray items = json.getJSONArray("data");
            for (int y = 0; y < items.length(); y++) {

                JSONObject obj = items.getJSONObject(y);
                HashMap<String, String> c = new HashMap<>();

                c.put("type", "data"); // clasify the item if data or header
                c.put("name", obj.getString("PluginFlyvemdmFile.name"));

                long fileKb = Long.parseLong(obj.getString("PluginFlyvemdmFile.filesize")) / 1024;
                c.put("size", String.valueOf(fileKb) + " KB");

                data.add(c);
            }

            pb.setVisibility(View.GONE);

            FileAdapter mAdapter = new FileAdapter(data);
            lst.setAdapter(mAdapter);

        } catch (Exception ex) {
            pb.setVisibility(View.GONE);
            FlyveLog.e(ex.getMessage());
        }

    }
}
