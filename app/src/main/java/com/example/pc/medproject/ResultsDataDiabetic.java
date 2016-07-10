package com.example.pc.medproject;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

/**
 * Created by PC on 17.05.2016.
 */
public class ResultsDataDiabetic {

    final private int result;
    final private String date;
    final private boolean beforeFood;

    public ResultsDataDiabetic(int resultFromDB, String dateFromDB, boolean beforeFoodFromDB) {
        result = resultFromDB;
        date = dateFromDB;
        beforeFood = beforeFoodFromDB;
        Log.d("******constructor ", result +" "+date+ " "+ beforeFood);
    }

    public int getResult() {
        return result;
    }

    public String getDate() {
        return date;
    }

    public boolean getBeforeFood() {
        return beforeFood;
    }
}
