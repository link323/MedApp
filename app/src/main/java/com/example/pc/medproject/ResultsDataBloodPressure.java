package com.example.pc.medproject;

/**
 * Created by PC on 28.05.2016.
 */
public class ResultsDataBloodPressure {

    int result1;
    int result2;
    String date;

    public ResultsDataBloodPressure(int result1FromDB, int result2FromDB, String dateFromDB) {
        result1 = result1FromDB;
        result2 = result2FromDB;
        date = dateFromDB;
    }

    public int getResult1() {
        return result1;
    }

    public int getResult2() {
        return result2;
    }

    public String getDate() {
        return date;
    }
}
