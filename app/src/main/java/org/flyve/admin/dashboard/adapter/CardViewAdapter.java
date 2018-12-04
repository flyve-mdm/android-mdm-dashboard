package org.flyve.admin.dashboard.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

public class CardViewAdapter extends RecyclerView.Adapter<CardViewAdapter.CardViewHolder> {

    public ImageView mImageView;
    public TextView titleTV;
    public TextView NumberTextView;

    @Override
    public CardViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(CardViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public static class CardViewHolder extends RecyclerView.ViewHolder{

        public CardViewHolder(View itemView) {
            super(itemView);
        }
    }
}
