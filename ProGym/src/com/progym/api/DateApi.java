package com.progym.api;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateApi {

    public static String getDdMmYyyyDate(String oldDate) {
        if (oldDate.equals(""))
            return new SimpleDateFormat("dd/MM/yyyy").format(new Date());
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar c = Calendar.getInstance();
        try {
            //Setting the date to the given date
            c.setTime(sdf.parse(oldDate));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return new SimpleDateFormat("dd/MM/yyyy").format(c.getTime());
    }

    public static String getDayName() {
        Date date = new Date();
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        String dayWeekText = new SimpleDateFormat("EEEE").format(date);
        return dayWeekText;
    }


}
