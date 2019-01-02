package org.flyve.admin.dashboard.data;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.flyve.admin.dashboard.R;
import org.glpi.api.GLPI;

import java.util.List;

import androidx.recyclerview.widget.RecyclerView;

public class GLPIadapter extends RecyclerView.Adapter<GLPIadapter.ActivityHolder> {

    private List<String> data;

    public GLPIadapter(List<String> data) {
        this.data = data;
    }

    @Override
    public ActivityHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        int layout = R.layout.list_item_activity;
        View view = LayoutInflater.from(parent.getContext()).inflate(layout, parent, false);
        return new ActivityHolder(view);
    }

    @Override
    public void onBindViewHolder(ActivityHolder holder, int position) {
        String text = data.get(position);
        if (text.contains("Success")) {
            holder.title.setTextColor(Color.parseColor("#25cf3c"));
        } else {
            holder.title.setTextColor(Color.parseColor("#ff4040"));
        }
        holder.title.setText(text);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class ActivityHolder extends RecyclerView.ViewHolder {
        TextView title;

        ActivityHolder(View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.textResult);
        }
    }

}
