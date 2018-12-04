package org.flyve.admin.dashboard.ui;

public class CardView {

    private int mImageResource;
    private String nameText;
    private String quantity;

    public CardView (int imageResource, String text, String number){
        mImageResource = imageResource;
        nameText = text;
        quantity = number;
    }
    public int getImageResource(){
        return mImageResource;
    }

    public String getNameText(){
        return nameText;
    }

    public String getQuantity(){
        return quantity;
    }


}
