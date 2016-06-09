package com.example.pc.medproject;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DataBaseHelper extends SQLiteOpenHelper
{
	public static final String DATABASE_NAME = "MEDdatabase.db";
	public static final int DATABASE_VERSION = 2;

	public static final String USER_TABLE = "users";
	public static final String USER_DATA_TABLE = "user_data";
	public static final String DIABETIC_RESULTS_TABLE = "diabetic_results";
	public static final String BLOOD_PRESSURE_RESULTS_TABLE = "pressure_results";

	public static final String ID_COLUMN = "id";
	public static final String USERNAME_COLUMN = "name";
	public static final String PASSWORD_COLUMN = "password";
	public static final String FIRSTNAME_COLUMN = "firstname";
	public static final String LASTNAME_COLUMN = "lastname";
	public static final String PESEL_COLUMN = "pesel";

	public static final String RESULT_COLUMN = "result";
	public static final String FOOD_COLUMN = "food";
	public static final String SYSTOLIC_COLUMN = "result1"; //skurczowe
	public static final String DIASTOLIC_COLUMN = "result2"; //rozkurczowe
	public static final String DATE_COLUMN = "date";
	public static final String COMMENT_COLUMN = "comment";


	static final String CREATE_USER_TABLE = "create table " + USER_TABLE + "( "
			+ ID_COLUMN + " integer primary key autoincrement,"
			+ USERNAME_COLUMN + " text, "
			+ PASSWORD_COLUMN + " text" + "); ";

	static final String CREATE_USER_DATA_TABLE = "create table " + USER_DATA_TABLE + "( "
			+ ID_COLUMN + " integer primary key, "
			+ USERNAME_COLUMN + " text, "
			+ FIRSTNAME_COLUMN + " text, "
			+ LASTNAME_COLUMN + " text, "
			+ PESEL_COLUMN + " text "+"); ";

	static final String CREATE_RESULTS_TABLE_1 = "create table " + DIABETIC_RESULTS_TABLE + "( "
			+ ID_COLUMN + " integer primary key, "
			+ PESEL_COLUMN + " text, "
			+ RESULT_COLUMN + " int, "
			+ DATE_COLUMN + " datetime, "
			+ FOOD_COLUMN + " boolean, "
			+ COMMENT_COLUMN + " text" + "); ";

	static final String CREATE_RESULTS_TABLE_2 = "create table " + BLOOD_PRESSURE_RESULTS_TABLE + "( "
			+ ID_COLUMN + " integer primary key,"
			+ PESEL_COLUMN + " text, "
			+ SYSTOLIC_COLUMN + " int, "
			+ DIASTOLIC_COLUMN + " int, "
			+ DATE_COLUMN + " datetime, "
			+ COMMENT_COLUMN + " text" + "); ";

	public DataBaseHelper(Context context, String name, CursorFactory factory, int version){
	           super(context, name, factory, version);
	}

	@Override
	public void onOpen(SQLiteDatabase db) {
		super.onOpen(db);
		if (!db.isReadOnly()) {
			// Enable foreign key constraints
			db.execSQL("PRAGMA foreign_keys=ON;");
		}
	}

	@Override
	public void onCreate(SQLiteDatabase _db) 
	{
		_db.execSQL(CREATE_USER_TABLE);
		_db.execSQL(CREATE_USER_DATA_TABLE);
		_db.execSQL(CREATE_RESULTS_TABLE_1);
		_db.execSQL(CREATE_RESULTS_TABLE_2);
			
	}

	// Called when there is a database version mismatch meaning that the version
	// of the database on disk needs to be upgraded to the current version.
	@Override
	public void onUpgrade(SQLiteDatabase _db, int _oldVersion, int _newVersion) 
	{
		Log.w("TaskDBAdapter", "Upgrading from version " +_oldVersion + " to " +_newVersion + ", which will destroy all old data");

		_db.execSQL("DROP TABLE IF EXISTS " + USER_TABLE);
		_db.execSQL("DROP TABLE IF EXISTS " + USER_DATA_TABLE);
		_db.execSQL("DROP TABLE IF EXISTS " + BLOOD_PRESSURE_RESULTS_TABLE);
		_db.execSQL("DROP TABLE IF EXISTS " + DIABETIC_RESULTS_TABLE);

		onCreate(_db);
	}
	

}
