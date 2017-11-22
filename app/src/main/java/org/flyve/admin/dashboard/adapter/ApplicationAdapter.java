package org.flyve.admin.dashboard.adapter;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.flyve.admin.dashboard.R;
import org.flyve.admin.dashboard.model.ApplicationModel;

import java.util.List;


public class ApplicationAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    private List<ApplicationModel> data;
    private final ApplicationAdapter.OnItemClickListener listener;

    private static final int ITEM_TYPE_DATA = 0;
    private static final int ITEM_TYPE_HEADER = 1;

    public ApplicationAdapter(List<ApplicationModel> data, ApplicationAdapter.OnItemClickListener listener) {
        this.data = data;
        this.listener = listener;
    }

    @Override
    public int getItemViewType(int position) {
        if(!(data.get(position)).getHeader().equals(ApplicationModel.NO_HEADER)) {
            return ITEM_TYPE_HEADER;
        } else {
            return ITEM_TYPE_DATA;
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {

        if(viewType == ITEM_TYPE_DATA) {
            View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_application_item, viewGroup, false);
            return new DataViewHolder(v);
        }

        else if (viewType == ITEM_TYPE_HEADER) {
            View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_application_header, viewGroup, false);
            return new HeaderViewHolder(v);
        }

        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ApplicationModel response = data.get(position);

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
        ImageView img;
        TextView name;
        TextView packages;
        TextView version;
        public RelativeLayout viewBackground;
        public RelativeLayout viewForeground;

        DataViewHolder(View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.img);
            name = itemView.findViewById(R.id.name);
            packages = itemView.findViewById(R.id.packages);
            version = itemView.findViewById(R.id.version);
            viewBackground = itemView.findViewById(R.id.view_background);
            viewForeground = itemView.findViewById(R.id.view_foreground);
        }

        public void bindData(final ApplicationModel model) {

            byte[] decodedString = Base64.decode( model.getIcon().getBytes(), Base64.DEFAULT);
            Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
            img.setImageBitmap(decodedByte);

            name.setText( Html.fromHtml( model.getName()) );
            packages.setText( Html.fromHtml( model.getPackages() ));
            version.setText( Html.fromHtml( "v." +  model.getVersion() ));

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

        public void bindData(ApplicationModel model) {
            title.setText( Html.fromHtml( model.getHeader() ));
        }
    }

    public void removeItem(int position) {
        data.remove(position);
        // notify the item removed by position
        // to perform recycler view delete animations
        // NOTE: don't call notifyDataSetChanged()
        notifyItemRemoved(position);
    }

    public void restoreItem(ApplicationModel item, int position) {
        data.add(position, item);
        // notify item added by position
        notifyItemInserted(position);
    }

    public interface OnItemClickListener {
        void onItemClick(ApplicationModel item);
    }


}