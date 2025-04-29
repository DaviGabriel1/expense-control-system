package com.payment.expensecontrolsystem.utils;

import java.util.Date;

public class DateUtils {

    public static boolean isDateValidFormat(Date date){ //padrão: Sun Apr 27 00:00:00 GMT-03:00 2025
        String[] dateParts = date.toString().split(" ");
        return dateParts.length == 6;
    }

    public static boolean isDatesValidTime(Date date1, Date date2){
        System.out.println(date1.before(date2));
        return date1.before(date2);
    }
}
