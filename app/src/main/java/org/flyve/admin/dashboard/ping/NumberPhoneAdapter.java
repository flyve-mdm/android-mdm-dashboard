package org.flyve.admin.dashboard.ping;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import org.flyve.admin.dashboard.R;

import java.util.ArrayList;

import androidx.recyclerview.widget.RecyclerView;

public class NumberPhoneAdapter extends RecyclerView.Adapter<NumberPhoneAdapter.NumberPhoneHolder> {

    private ArrayList<NumberPhoneCardView> numberPhoneList;
    private OnItemClickListener mListener;

    public interface OnItemClickListener{
        void onEditClick(int position);
        void onPingClick(int position);
        void onCallClick (int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener){
        mListener = listener;
    }


    public static class NumberPhoneHolder extends RecyclerView.ViewHolder{

        public TextView numberPhone;
        public TextView imei;
        public TextView simcard;
        public TextView lastContact;
        public ImageView editImage;
        public ImageView sendPingImage;
        public ImageView callNumberImage;

        public NumberPhoneHolder(View itemView, OnItemClickListener listener){
            super(itemView);
            editImage = itemView.findViewById(R.id.editNumber);
            sendPingImage = itemView.findViewById(R.id.sendPing);
            callNumberImage = itemView.findViewById(R.id.callNumber);

            numberPhone = itemView.findViewById(R.id.phoneNumberCard);
            imei = itemView.findViewById(R.id.imeiCard);
            simcard = itemView.findViewById(R.id.simcard);
            lastContact = itemView.findViewById(R.id.lastContact);

            editImage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(listener != null){
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION){
                            listener.onEditClick(position);
                        }
                    }
                }
            });

            sendPingImage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(listener != null){
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION){
                            listener.onPingClick(position);
                        }
                    }
                }
            });

            callNumberImage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(listener != null){
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION){
                            listener.onCallClick(position);
                        }
                    }
                }
            });
        }
    }

    public NumberPhoneAdapter(ArrayList<NumberPhoneCardView> numberList){
        numberPhoneList = numberList;
    }

    @Override
    public NumberPhoneHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.number_phone_card_view, parent, false);
        NumberPhoneHolder nph = new NumberPhoneHolder(v, mListener);
        return nph;
    }

    @Override
    public void onBindViewHolder(NumberPhoneHolder holder, int position) {
        NumberPhoneCardView currentItem = numberPhoneList.get(position);
        holder.numberPhone.setText(currentItem.getPhone());
        holder.imei.setText(currentItem.getIMEI());
        holder.simcard.setText(currentItem.getSim());
        holder.lastContact.setText(currentItem.getContact());
    }

    @Override
    public int getItemCount() {
        return numberPhoneList.size();
    }
}
