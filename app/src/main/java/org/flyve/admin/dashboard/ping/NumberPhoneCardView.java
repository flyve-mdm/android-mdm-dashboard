package org.flyve.admin.dashboard.ping;

public class NumberPhoneCardView{

    private String PhoneCard;
    private String imeiCard;
    private String simCard;
    private String lastContact;

    public NumberPhoneCardView (String numberPhone, String imei, String simcard, String contact){
        PhoneCard = numberPhone;
        imeiCard = imei;
        simCard = simcard;
        lastContact = contact;
    }

    public void changePhoneNumber(String number){
        PhoneCard = number;
    }

    public void changeIMEI(String imei){
        imeiCard = imei;
    }

    public void changeSimCardNumber(String simcardnumber){
        simCard = simcardnumber;
    }

    public void changeContactText(String text){
        lastContact= text;
    }


    public String getPhone(){
        return PhoneCard;
    }

    public String getIMEI() {
        return imeiCard;
    }

    public String getSim(){
        return simCard;
    }

    public String getContact(){
        return lastContact;
    }
}
