package com.example.pc.medproject;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class DataBaseAdapter {

    private final Context context;
    public SQLiteDatabase db;
    private DataBaseHelper dbHelper;

    public DataBaseAdapter(Context _context) {
        context = _context;
        dbHelper = new DataBaseHelper(context, DataBaseHelper.DATABASE_NAME, null, DataBaseHelper.DATABASE_VERSION);
    }

    public DataBaseAdapter open() throws SQLException {
        db = dbHelper.getWritableDatabase();
        return this;
    }

    public void close() {
        db.close();
    }

    public void insertEntryUsersTable(String userName, String password) {
        ContentValues newValues = new ContentValues();
        newValues.put(DataBaseHelper.USERNAME_COLUMN, userName);
        newValues.put(DataBaseHelper.PASSWORD_COLUMN, password);
        db.insert(DataBaseHelper.USER_TABLE, null, newValues);
    }
    public void insertEntryUsersDataTable(String firstName, String lastName, String pesel) {
        ContentValues newValues = new ContentValues();
        newValues.put(DataBaseHelper.FIRSTNAME_COLUMN, firstName);
        newValues.put(DataBaseHelper.LASTNAME_COLUMN, lastName);
        newValues.put(DataBaseHelper.PESEL_COLUMN, pesel);
        db.insert(DataBaseHelper.USER_DATA_TABLE, null, newValues);
    }

    public int deleteEntry(String UserName) {
        String where = "name=?";
        int numberOFEntriesDeleted = db.delete("users", where, new String[]{UserName});
        return numberOFEntriesDeleted;
    }

    public String getSinlgeEntry(String userName) {
        Cursor cursor = db.query(DataBaseHelper.USER_TABLE, null, " name=?", new String[]{userName}, null, null, null);
        if (cursor.getCount() < 1){
            cursor.close();
            return "NOT EXIST";
        }
        cursor.moveToFirst();
        String password = cursor.getString(cursor.getColumnIndex("password"));
        Log.d("home", password);
        cursor.close();
        return password;
    }

    public void updateEntry(String userName, String password) {
        ContentValues updatedValues = new ContentValues();
        updatedValues.put("name", userName);
        updatedValues.put("password", password);
        String where = "name=?";
        db.update(DataBaseHelper.USER_TABLE, updatedValues, where, new String[]{userName});
    }
// dla cukrzycy
    public void insertEntryToDiabeticTable(int result, String date, boolean beforeFood) {
        ContentValues newValues = new ContentValues();
        newValues.put(DataBaseHelper.RESULT_COLUMN, result);
        newValues.put(DataBaseHelper.DATE_COLUMN, date);
        newValues.put(DataBaseHelper.FOOD_COLUMN, beforeFood);
        db.insert(DataBaseHelper.DIABETIC_RESULTS_TABLE, null, newValues);
    }
// dla ciśnienia
    public void insertEntryToBloodTable(int systolic, int diastolic, String date) {
        ContentValues newValues = new ContentValues();
        newValues.put(DataBaseHelper.SYSTOLIC_COLUMN, systolic);
        newValues.put(DataBaseHelper.DIASTOLIC_COLUMN, diastolic);
        newValues.put(DataBaseHelper.DATE_COLUMN, date);
        db.insert(DataBaseHelper.BLOOD_PRESSURE_RESULTS_TABLE, null, newValues);
    }

    public List<ResultsDataDiabetic> getAllDiabeticResults() {
        List<ResultsDataDiabetic> resultsList = new ArrayList<>();
        String selectQuery = "SELECT  * FROM " + DataBaseHelper.DIABETIC_RESULTS_TABLE;
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                ResultsDataDiabetic data = new ResultsDataDiabetic(Integer.parseInt(cursor.getString(0)),cursor.getString(1),Boolean.parseBoolean(cursor.getString(2)));
                Log.d("data from table ", cursor.getString(0) +" " + cursor.getString(1) + " " + cursor.getString(2));
                resultsList.add(data);
            } while (cursor.moveToNext());
        }
        return  resultsList;
    }
    public List<ResultsDataBloodPressure> getAllBloodPressureResults(){
        List<ResultsDataBloodPressure> resultsList = new ArrayList<>();
        String selectQuery = "SELECT  * FROM " + DataBaseHelper.BLOOD_PRESSURE_RESULTS_TABLE;
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                ResultsDataBloodPressure data = new ResultsDataBloodPressure(Integer.parseInt(cursor.getString(0)), Integer.parseInt(cursor.getString(1)), cursor.getString(2));
                Log.d("data from table ", cursor.getString(0) +" " + cursor.getString(1) + " " + cursor.getString(2));
                resultsList.add(data);
            } while (cursor.moveToNext());
        }
        return  resultsList;
    }
}