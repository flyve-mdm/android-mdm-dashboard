package org.flyve.admin.dashboard.ping;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.flyve.admin.dashboard.R;

import java.util.ArrayList;

public class NumberPhoneAdapter extends RecyclerView.Adapter<NumberPhoneAdapter.NumberPhoneHolder> {

    private ArrayList<NumberPhoneCardView> numberPhoneList;

    public static class NumberPhoneHolder extends RecyclerView.ViewHolder{

        public TextView numberPhone;
        public TextView email;
        public TextView simcard;

        public NumberPhoneHolder(View itemView){
            super(itemView);
            numberPhone = itemView.findViewById(R.id.phoneNumberCard);
            email = itemView.findViewById(R.id.emailCard);
            simcard = itemView.findViewById(R.id.simcard);
        }
    }

    public NumberPhoneAdapter(ArrayList<NumberPhoneCardView> numberList){
        numberPhoneList = numberList;
    }

    @Override
    public NumberPhoneHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.number_phone_card_view, parent, false);
        NumberPhoneHolder nph = new NumberPhoneHolder(v);
        return nph;
    }

    @Override
    public void onBindViewHolder(NumberPhoneHolder holder, int position) {
        NumberPhoneCardView currentItem = numberPhoneList.get(position);

        holder.numberPhone.setText(currentItem.getPhone());
        holder.email.setText(currentItem.getMail());
        holder.simcard.setText(currentItem.getSim());
    }

    @Override
    public int getItemCount() {
        return numberPhoneList.size();
    }
}
