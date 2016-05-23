package com.example.pc.medproject.views;


import android.app.Dialog;

import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;

import android.widget.TimePicker;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;


/**
 * Created by PC on 22.05.2016.
 */
public class TimeDialogFragment extends DialogFragment implements TimePickerDialog.OnTimeSetListener{
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

    final Calendar c = Calendar.getInstance();

        int hour = c.get(Calendar.HOUR_OF_DAY);
        int minute = c.get(Calendar.MINUTE);
        return new TimePickerDialog(getActivity(), this, hour, minute, true);
    }


    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        Calendar c = Calendar.getInstance();

        c.set(Calendar.HOUR_OF_DAY, hourOfDay);
        c.set(Calendar.MINUTE, minute);
        SimpleDateFormat timeFormatter = new SimpleDateFormat("HH:mm");
        String time = timeFormatter.format(c.getTime());
        ArrayList<String> list = new ArrayList<>();
        list.add(time);
        Intent intent = new Intent();
        intent.putStringArrayListExtra("key_time", list);
        getTargetFragment().onActivityResult(getTargetRequestCode(),1,intent);
    }

}
