package org.flyve.admin.dashboard.ping;

public class NumberPhoneCardView{
    private String PhoneCard;
    private String mailCard;
    private String simCard;
    private String lastContact;

    public NumberPhoneCardView (String numberPhone, String email, String simcard){
        PhoneCard = numberPhone;
        mailCard = email;
        simCard = simcard;
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
}
