package org.flyve.admin.dashboard.adapter;

import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.flyve.admin.dashboard.R;

import java.util.HashMap;
import java.util.List;


public class FleetAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    private List<HashMap<String, String>> data;
    private final FleetAdapter.OnItemClickListener listener;

    private static final int ITEM_TYPE_DATA = 0;
    private static final int ITEM_TYPE_HEADER = 1;

    public FleetAdapter(List<HashMap<String, String>> data, FleetAdapter.OnItemClickListener listener) {
        this.data = data;
        this.listener = listener;
    }

    @Override
    public int getItemViewType(int position) {
        if((data.get(position)).get("type").equals("header")) {
            return ITEM_TYPE_HEADER;
        } else {
            return ITEM_TYPE_DATA;
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {

        if(viewType == ITEM_TYPE_DATA) {
            View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_fleet_item, viewGroup, false);
            return new DataViewHolder(v);
        }

        else if (viewType == ITEM_TYPE_HEADER) {
            View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_fleet_header, viewGroup, false);
            return new HeaderViewHolder(v);
        }

        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        HashMap<String, String> response = data.get(position);

        final int itemType = getItemViewType(position);

        if(itemType == ITEM_TYPE_DATA) {
            ((DataViewHolder)holder).bindData( response );
        }

        if(itemType == ITEM_TYPE_HEADER) {
            ((HeaderViewHolder)holder).bindData( response );
        }
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    public class DataViewHolder extends RecyclerView.ViewHolder {
        TextView name;
        public RelativeLayout viewBackground;
        public RelativeLayout viewForeground;


        DataViewHolder(View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name);
        }

        public void bindData(final HashMap<String, String> model) {
            name.setText( Html.fromHtml( model.get("name")) );
            viewBackground = itemView.findViewById(R.id.view_background);
            viewForeground = itemView.findViewById(R.id.view_foreground);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onItemClick(model);
                }
            });
        }
    }

    public class HeaderViewHolder extends RecyclerView.ViewHolder {
        TextView title;

        HeaderViewHolder(View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.title);
        }

        public void bindData(HashMap<String, String> model) {
            title.setText( Html.fromHtml( model.get("title") ));
        }
    }

    public void removeItem(int position) {
        data.remove(position);
        // notify the item removed by position
        // to perform recycler view delete animations
        // NOTE: don't call notifyDataSetChanged()
        notifyItemRemoved(position);
    }

    public void restoreItem(HashMap<String, String> item, int position) {
        data.add(position, item);
        // notify item added by position
        notifyItemInserted(position);
    }

    public interface OnItemClickListener {
        void onItemClick(HashMap<String, String> item);
    }


}