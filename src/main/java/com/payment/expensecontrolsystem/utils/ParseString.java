package com.payment.expensecontrolsystem.utils;

public class ParseString {

    public static String parseStringToNumber(String text){
        return text.replaceAll("[^0-9,]", "").replaceAll(",", ".");
    }
}
