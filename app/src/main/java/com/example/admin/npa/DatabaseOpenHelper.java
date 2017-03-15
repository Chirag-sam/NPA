package com.example.admin.npa;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.HashMap;

/**
 * Created by krsnv on 13-Mar-17.
 */

public class DatabaseOpenHelper extends SQLiteOpenHelper {

    private static final String TAG = DatabaseOpenHelper.class.getSimpleName();

    // All Static variables
    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "NPA";

    // Login table name
    private static final String TABLE_NURSE = "nurse";

    // Login Table Columns names
    private static final String NURSE_NAME = "name";
    private static final String NURSE_ID = "id";
    private static final String NURSE_AVAIL = "avail";
    private static final String NURSE_NOTASKS = "notasks";
    private static final String NURSE_TASKCOMP = "taskcomp";
    private static final String NURSE_TASKPEND= "taskpend";
    private static final String NURSE_REPORTDATE = "reportdate";

    private static final String TABLE_PATIENT = "patient";

    // Login Table Columns names
    private static final String PATIENT_NAME = "name";
    private static final String PATIENT_ID = "pid";
    private static final String PATIENT_REPDATE = "repdate";
    private static final String PATIENT_GENDER = "gender";
    private static final String PATIENT_AGE = "age";


    private static final String TABLE_RESPONSE = "response";

    // Login Table Columns names

    private static final String RESPONSE_ID = "pid";
    private static final String RESPONSE_QID = "repdate";
    private static final String PATIENT_ANSWER = "gender";
    private static final String PATIENT_AGE = "age";

    public DatabaseOpenHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String CREATE_NURSE_TABLE = "CREATE TABLE " + TABLE_NURSE + "("
                + NURSE_NAME + " TEXT,"+ NURSE_ID + " TEXT NOT NULL PRIMARY KEY,"
                + NURSE_AVAIL + " INTEGER," + NURSE_NOTASKS + " TEXT," + NURSE_TASKCOMP + " TEXT," + NURSE_TASKPEND + " TEXT," + NURSE_REPORTDATE + " TEXT"
                +")";
        sqLiteDatabase.execSQL(CREATE_NURSE_TABLE);
        String CREATE_PATIENT_TABLE = "CREATE TABLE " + TABLE_PATIENT + "("
                + PATIENT_NAME + " TEXT," + PATIENT_ID + " TEXT NOT NULL PRIMARY KEY,"
                + PATIENT_REPDATE + " INTEGER," + PATIENT_GENDER + " TEXT," + PATIENT_AGE + " TEXT "
                + ")";
        sqLiteDatabase.execSQL(CREATE_PATIENT_TABLE);
        Log.d(TAG, "Database tables created");
    }


    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NURSE);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_PATIENT);

        // Create tables again
        onCreate(sqLiteDatabase);
    }

    public void addPatient(PatientJ N) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(PATIENT_NAME, N.getName()); // Name
        values.put(PATIENT_ID, N.getPid()); // Email
        values.put(PATIENT_REPDATE, N.getRepdate()); // Email
        values.put(PATIENT_GENDER, N.getGender());
        values.put(PATIENT_AGE, N.getAge());

        // Inserting Row
        long id = db.insert(TABLE_PATIENT, null, values);
        db.close(); // Closing database connection

        Log.d(TAG, "New Nurse inserted into sqlite: " + id);
    }

    public void addNurse(Nurse N) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(NURSE_NAME, N.getName()); // Name
        values.put(NURSE_ID, N.getId()); // Email
        values.put(NURSE_AVAIL, N.getAvail()); // Email
        values.put(NURSE_NOTASKS, N.getAvail());
        values.put(NURSE_TASKCOMP, N.getTaskcomp());
        values.put(NURSE_TASKPEND, N.getTaskpend());
        values.put(NURSE_REPORTDATE, N.getReportdate());// Created At

        // Inserting Row
        long id = db.insert(TABLE_NURSE, null, values);
        db.close(); // Closing database connection

        Log.d(TAG, "New Nurse inserted into sqlite: " + id);
    }

    /**
     * Getting user data from database
     * */
    public Nurse getNurseDetails() {
        Nurse N=new Nurse();
        String selectQuery = "SELECT  * FROM " + TABLE_NURSE;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        // Move to first row
        cursor.moveToFirst();
        if (cursor.getCount() > 0) {
            N=new Nurse(cursor.getString(1),cursor.getString(2),cursor.getString(3),cursor.getString(4),cursor.getString(5),cursor.getString(6),cursor.getString(7));

        }
        cursor.close();
        db.close();
        // return user

        return N;
    }

    public PatientJ getPatient() {
        PatientJ N = new PatientJ();
        String selectQuery = "SELECT  * FROM " + TABLE_NURSE;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        // Move to first row
        cursor.moveToFirst();
        if (cursor.getCount() > 0) {
            N = new PatientJ(cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4), cursor.getString(5));

        }
        cursor.close();
        db.close();
        // return user

        return N;
    }

    /**
     * Re crate database Delete all tables and create them again
     * */
    public void deleteNurseComplete() {
        SQLiteDatabase db = this.getWritableDatabase();
        // Delete All Rows
        db.delete(TABLE_NURSE, null, null);
        db.close();

        Log.d(TAG, "Deleted all Nurse info from sqlite");
    }

    public void deletePatientComplete() {
        SQLiteDatabase db = this.getWritableDatabase();
        // Delete All Rows
        db.delete(TABLE_PATIENT, null, null);
        db.close();

        Log.d(TAG, "Deleted all Patient info from sqlite");
    }
}
