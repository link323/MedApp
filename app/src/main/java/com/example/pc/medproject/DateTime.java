package com.example.pc.medproject;

import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by PC on 13.06.2016.
 */
public class DateTime {
    private SimpleDateFormat timeFormatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");

    public DateTime(){
    }
    public String findLastMonthDate() {
        Calendar c = Calendar.getInstance();
        c.roll(Calendar.MONTH, false);
        Log.d("date last month ", c.getTime().toString());

        String time = timeFormatter.format(c.getTime());
        Log.d("date last month ", time);
        return time;
    }
    public String findActualDate() {
        Calendar c = Calendar.getInstance();
        String time = timeFormatter.format(c.getTime());
        return time;
    }
}
