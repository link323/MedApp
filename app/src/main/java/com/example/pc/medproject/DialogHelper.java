package com.example.pc.medproject;

import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

/**
 * Created by PC on 25.05.2016.
 */
public class DialogHelper {

    public ArrayList<String> returnDefaultTime(ArrayList<String> list){
        final Calendar c = Calendar.getInstance();
        int hour = c.get(Calendar.HOUR_OF_DAY);
        int minute = c.get(Calendar.MINUTE);

        SimpleDateFormat timeFormatter = new SimpleDateFormat("HH:mm");
        String time = timeFormatter.format(c.getTime());
        Log.d("czas ", time);
        list.add(time);

        return list;
    }
}
