package org.flyve.admin.dashboard.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import org.flyve.admin.dashboard.R;
import org.flyve.admin.dashboard.ui.CardView;

import java.util.ArrayList;

public class CardViewAdapter extends RecyclerView.Adapter<CardViewAdapter.CardViewHolder> {

    private ArrayList<CardView> arrayCardList;
    private OnItemClickListener mListener;

    public interface OnItemClickListener{
        void onItemClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener){
        mListener = listener;
    }


    public static class CardViewHolder extends RecyclerView.ViewHolder {
        public ImageView mImageView;
        public TextView titleTV;
        public TextView NumberTextView;

        public CardViewHolder(View itemView, OnItemClickListener listener) {
            super(itemView);
            mImageView = itemView.findViewById(R.id.imageView);
            titleTV = itemView.findViewById(R.id.textName);
            NumberTextView = itemView.findViewById(R.id.quantity);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(listener != null){
                        int position = getAdapterPosition();
                        if (position!= RecyclerView.NO_POSITION){
                            listener.onItemClick(position);
                        }
                    }
                }
            });
        }
    }
    public CardViewAdapter (ArrayList<CardView> CardList){
        arrayCardList = CardList;
    }

    @Override
    public CardViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_view_layout, parent, false);
        CardViewHolder cva = new CardViewHolder(v, mListener);
        return cva;
    }

    @Override
    public void onBindViewHolder(CardViewHolder holder, int position) {
        CardView currentItem = arrayCardList.get(position);
        holder.mImageView.setImageResource(currentItem.getImageResource());
        holder.titleTV.setText(currentItem.getNameText());
        holder.NumberTextView.setText(currentItem.getQuantity());
    }

    @Override
    public int getItemCount() {
        return arrayCardList.size();
    }

}
