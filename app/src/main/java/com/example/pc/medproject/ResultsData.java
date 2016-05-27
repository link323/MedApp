package com.example.pc.medproject;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by PC on 17.05.2016.
 */
public class ResultsData {

    int result;
    String date;
    boolean beforeFood;
    public ResultsData(int resultFromDB, String dateFromDB, boolean beforeFoodFromDB) {
        result = resultFromDB;
        date = dateFromDB;
        beforeFood = beforeFoodFromDB;
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
