package com.willshen.smartAppointments;

public class Util {

    static Boolean checkEmail(String email){
        String EMAIL_REGEX = "^[\\w-\\+]+(\\.[\\w]+)*@[\\w-]+(\\.[\\w]+)*(\\.[a-z]{2,})$";
        if(email.matches(EMAIL_REGEX)){
            return true;
        } else {
            return false;
        }
    }

    static Boolean checkPhoneNumber(String phoneNumber){
        String PHONE_REGEX = "/d+";
        if (phoneNumber.matches(PHONE_REGEX)) {
            return true;
        } else {
            return false;
        }
    }
}
