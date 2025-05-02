package com.payment.expensecontrolsystem.utils;

import java.util.Date;

public class DateUtils {

    public static boolean isDateValidFormat(Date date){ //padrão: Sun Apr 27 00:00:00 GMT-03:00 2025
        String[] dateParts = date.toString().split(" ");
        return dateParts.length == 6;
    }

    public static boolean isDatesValidTime(Date date1, Date date2){
        return date1.before(date2);
    }

    public static String normalizeDate(Date date){
        String[] dateParts = date.toString().replace("."," ").split(":");
        String[] datePartswithoutTime = dateParts[0].split("-");
        return datePartswithoutTime[2].split(" ")[0] + "/" + datePartswithoutTime[1] + "/" + datePartswithoutTime[0];
    }
}
