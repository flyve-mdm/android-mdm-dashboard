package org.flyve.admin.dashboard.ping;

public class NumberPhoneCardView{
    private String PhoneCard;
    private String mailCard;
    private String simCard;
    private String lastContact;

    public NumberPhoneCardView (String numberPhone, String email, String simcard, String contact){
        PhoneCard = numberPhone;
        mailCard = email;
        simCard = simcard;
        lastContact = contact;
    }

    public void changeContactText(String text){
        lastContact= text;
    }
    public void getPhoneNumber(String number){
        PhoneCard = number;
    }

    public String getPhone(){
        return PhoneCard;
    }

    public String getMail() {
        return mailCard;
    }

    public String getSim(){
        return simCard;
    }

    public String getContact(){
        return lastContact;
    }
}
