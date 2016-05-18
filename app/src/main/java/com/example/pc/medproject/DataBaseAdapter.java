package com.example.pc.medproject;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class DataBaseAdapter {


    // Context of the application using the database.
    private final Context context;
    // Variable to hold the database instance
    public SQLiteDatabase db;
    // Database open/upgrade helper
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

    public void insertEntry(String userName, String password) {
        ContentValues newValues = new ContentValues();
        // Assign values for each row.
        newValues.put(DataBaseHelper.USERNAME_COLUMN, userName);
        newValues.put(DataBaseHelper.PASSWORD_COLUMN, password);
        //newValues.put(PESEL_COLUMN, pesel);
        // Insert the row into your table
        db.insert(DataBaseHelper.USER_TABLE, null, newValues);
        ///Toast.makeText(context, "Reminder Is Successfully Saved", Toast.LENGTH_LONG).show();
    }

    public int deleteEntry(String UserName) {
        //String id=String.valueOf(ID);
        String where = "name=?";
        int numberOFEntriesDeleted = db.delete("users", where, new String[]{UserName});
        // Toast.makeText(context, "Number fo Entry Deleted Successfully : "+numberOFEntriesDeleted, Toast.LENGTH_LONG).show();
        return numberOFEntriesDeleted;
    }

    public String getSinlgeEntry(String userName) {
        Cursor cursor = db.query(DataBaseHelper.USER_TABLE, null, " name=?", new String[]{userName}, null, null, null);
        if (cursor.getCount() < 1) // UserName Not Exist
        {
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
        // Define the updated row content.
        ContentValues updatedValues = new ContentValues();
        // Assign values for each row.
        updatedValues.put("name", userName);
        updatedValues.put("password", password);
        String where = "name=?";
        db.update(DataBaseHelper.USER_TABLE, updatedValues, where, new String[]{userName});
    }
// dla cukrzycy
    public void insertEntryToDiabeticTable(float result, String date, boolean beforeFood) {
        ContentValues newValues = new ContentValues();
        // Assign values for each row.
        newValues.put(DataBaseHelper.RESULT_COLUMN, result);
        newValues.put(DataBaseHelper.DATE_COLUMN, date);
        newValues.put(DataBaseHelper.FOOD_COLUMN, beforeFood);
        // Insert the row into your table
        db.insert(DataBaseHelper.DIABETIC_RESULTS_TABLE, null, newValues);
        ///Toast.makeText(context, "Reminder Is Successfully Saved", Toast.LENGTH_LONG).show();
    }
// dla ciśnienia
    public void insertEntryToBloodTable(int systolic, int diastolic, String date) {
        ContentValues newValues = new ContentValues();
        // Assign values for each row.
        newValues.put(DataBaseHelper.SYSTOLIC_COLUMN, systolic);
        newValues.put(DataBaseHelper.DIASTOLIC_COLUMN, diastolic);
        newValues.put(DataBaseHelper.DATE_COLUMN, date);
        // Insert the row into your table
        db.insert(DataBaseHelper.BLOOD_PRESSURE_RESULTS_TABLE, null, newValues);
        ///Toast.makeText(context, "Reminder Is Successfully Saved", Toast.LENGTH_LONG).show();
    }
}