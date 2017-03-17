package com.example.admin.npa;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by krsnv on 13-Mar-17.
 */

public class DatabaseOpenHelper extends SQLiteOpenHelper {

    private static final String TAG = DatabaseOpenHelper.class.getSimpleName();
    private static DatabaseOpenHelper sInstance;
    // All Static variables
    // Database Version
    private static final int DATABASE_VERSION = 4;

    // Database Name
    private static final String DATABASE_NAME = "NPA";

    // Login table name
    private static final String TABLE_NURSE = "nurse";

    // Login Table Columns names
    private static final String NURSE_ID = "nid";
    private static final String NURSE_UNAME= "uname";
    private static final String NURSE_PASS= "password";
    private static final String NURSE_NAME = "name";
    private static final String NURSE_LASTSYNC= "lastsync";

    private static final String TABLE_PATIENT = "patient";

    // Login Table Columns names
    private static final String PATIENT_ID = "pid";
    private static final String PATIENT_NAME = "name";
    private static final String PATIENT_GENDER = "gender";
    private static final String PATIENT_AGE = "age";
    private static final String PATIENT_REPDATE = "repdate";
    private static final String PATIENT_DISEASE = "disease";
    private static final String PATIENT_NID = "nid";
    private static final String PATIENT_STATUS = "status";


    private static final String TABLE_RESPONSE = "response";

    // Login Table Columns names

    private static final String RESPONSE_ID = "pid";
    private static final String RESPONSE_QID = "qid";
    private static final String RESPONSE_ANSWER = "gender";

    private static final String TABLE_QUESTION = "questions";

    // Login Table Columns names

    private static final String QUESTION_ID = "qid";
    private static final String QUESTION_DESC = "qdesc";
    private static final String QUESTION_TYPE = "restype";


    public static synchronized DatabaseOpenHelper getInstance(Context context) {
        // Use the application context, which will ensure that you
        // don't accidentally leak an Activity's context.
        // See this article for more information: http://bit.ly/6LRzfx
        if (sInstance == null) {
            sInstance = new DatabaseOpenHelper(context.getApplicationContext());
        }
        return sInstance;
    }

    public DatabaseOpenHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String CREATE_NURSE_TABLE = "CREATE TABLE " + TABLE_NURSE +
                "( "+
                NURSE_ID + " TEXT NOT NULL PRIMARY KEY,"+
                NURSE_UNAME + " TEXT,"+
                NURSE_PASS + " TEXT,"+
                NURSE_NAME + " TEXT,"+
                NURSE_LASTSYNC + " TEXT "+
                ")";
        sqLiteDatabase.execSQL(CREATE_NURSE_TABLE);
        String CREATE_PATIENT_TABLE = "CREATE TABLE " + TABLE_PATIENT +
                " ("+
                PATIENT_ID + " TEXT NOT NULL PRIMARY KEY," +
                PATIENT_NAME + " TEXT," +
                PATIENT_GENDER + " TEXT," +
                PATIENT_AGE + " TEXT "+
                PATIENT_REPDATE + " TEXT," +
                PATIENT_DISEASE + " TEXT," +
                PATIENT_NID + " TEXT," +
                PATIENT_STATUS + " TEXT" +
                 ")";
        sqLiteDatabase.execSQL(CREATE_PATIENT_TABLE);

        String CREATE_RESPONSE_TABLE = "CREATE TABLE " + TABLE_RESPONSE + " ("
                + RESPONSE_ID + " TEXT," + RESPONSE_QID + " TEXT NOT NULL PRIMARY KEY,"
                + RESPONSE_ANSWER + " TEXT "
                + ")";
        sqLiteDatabase.execSQL(CREATE_RESPONSE_TABLE);
        String CREATE_QUESTION_TABLE = "CREATE TABLE " + TABLE_QUESTION + " ("
                + QUESTION_ID + " TEXT  NOT NULL PRIMARY KEY ," + QUESTION_DESC + " TEXT,"
                + QUESTION_TYPE + " TEXT "
                + ")";
        sqLiteDatabase.execSQL(CREATE_QUESTION_TABLE);
        Log.d(TAG, "Database tables created");
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (oldVersion != newVersion) {
            // Simplest implementation is to drop all old tables and recreate them
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_NURSE);
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_PATIENT);
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_RESPONSE);
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_QUESTION);
            onCreate(db);
        }
    }



    public void addPatient(PatientJ N) {
        SQLiteDatabase db = getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(PATIENT_ID, N.getPid());
        values.put(PATIENT_NAME, N.getName());
        values.put(PATIENT_GENDER, N.getGender());
        values.put(PATIENT_AGE, N.getAge());// Name         // Email
        values.put(PATIENT_REPDATE, N.getRepdate());
        values.put(PATIENT_DISEASE, N.getDisease());
        values.put(PATIENT_NID, N.getNid());
        values.put(PATIENT_STATUS, N.getStatus());

        // Inserting Row
        long id = db.insert(TABLE_PATIENT, null, values);
        db.close(); // Closing database connection

        Log.d(TAG, "New Nurse inserted into sqlite: " + id);
    }
    public void addallpatients(ArrayList<PatientJ> List) {
        SQLiteDatabase db = getWritableDatabase();

        for (PatientJ N:List) {
            addPatient(N);
            /*
            ContentValues values = new ContentValues();
            values.put(PATIENT_ID, N.getPid());
            values.put(PATIENT_NAME, N.getName());
            values.put(PATIENT_GENDER, N.getGender());
            values.put(PATIENT_AGE, N.getAge());// Name         // Email
            values.put(PATIENT_REPDATE, N.getRepdate());
            values.put(PATIENT_DISEASE, N.getDisease());
            values.put(PATIENT_NID, N.getNid());
            values.put(PATIENT_STATUS, N.getStatus());

            // Inserting Row
            long id = db.insert(TABLE_PATIENT, null, values);*/
        }
        db.close(); // Closing database connection

    }

    public void addNurse(Nurse N) {
        SQLiteDatabase db =getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(NURSE_ID, N.getNid());
        values.put(NURSE_UNAME, N.getUname());
        values.put(NURSE_PASS, N.getPassword());
        values.put(NURSE_NAME, N.getName());
        values.put(NURSE_LASTSYNC, N.getLastsync());

        long id = db.insert(TABLE_NURSE, null, values);
        db.close();
        Log.d(TAG, "New Nurse inserted into sqlite: " + id);

    }

    public void addResponse(ResponseQn N) {
        SQLiteDatabase db = getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(RESPONSE_ID, N.getPid());
        values.put(RESPONSE_QID, N.getQid());
        values.put(RESPONSE_ANSWER, N.getAnswer());

        // Inserting Row
        long id = db.insert(TABLE_RESPONSE, null, values);
        db.close(); // Closing database connection

        Log.d(TAG, "New Nurse inserted into sqlite: " + id);
    }

    public void addQuestion(Question N) {
        SQLiteDatabase db = getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(QUESTION_ID, N.getQid());
        values.put(QUESTION_DESC, N.getQdesc());
        values.put(QUESTION_TYPE, N.getRestype());

        // Inserting Row
        long id = db.insert(TABLE_QUESTION, null, values);
        db.close(); // Closing database connection

        Log.d(TAG, "New Qn inserted into sqlite: " + id);
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
            N=new Nurse(cursor.getString(0),cursor.getString(1),cursor.getString(2),cursor.getString(3),cursor.getString(4));

        }
        cursor.close();
        db.close();
        // return user

        return N;
    }
    public boolean getLoginsession() {
        String selectQuery = "SELECT  count(*) FROM " + TABLE_NURSE;

        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        // Move to first row
        cursor.moveToFirst();
        int icount = cursor.getInt(0);
        if(icount==1)
            return true;
        else {
            deleteNurseComplete();
            return false;}

        // return user

    }

    public PatientJ getPatient() {
        PatientJ N = new PatientJ();
        String selectQuery = "SELECT  * FROM " + TABLE_NURSE;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        // Move to first row
        cursor.moveToFirst();
        if (cursor.getCount() > 0) {
            N = new PatientJ(cursor.getString(0), cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4), cursor.getString(5), cursor.getString(6), cursor.getString(7));

        }
        cursor.close();
        db.close();
        // return user

        return N;
    }

    public ResponseQn getResponse() {
        ResponseQn N = new ResponseQn();
        String selectQuery = "SELECT  * FROM " + TABLE_RESPONSE;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        // Move to first row
        cursor.moveToFirst();
        if (cursor.getCount() > 0) {
            N = new ResponseQn(cursor.getString(1), cursor.getString(2), cursor.getString(3));

        }
        cursor.close();
        db.close();
        // return user

        return N;
    }

    public ArrayList<PatientJ> getAllPatients() {
        ArrayList<PatientJ> patientlist = new ArrayList<PatientJ>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_PATIENT;

        SQLiteDatabase db = getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {

              patientlist.add(new PatientJ(cursor.getString(0), cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4), cursor.getString(5), cursor.getString(6), cursor.getString(7)));

                // Adding contact to list
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        // return contact list
        return patientlist;
    }
    public Question getQuestion() {
        Question N = new Question();
        String selectQuery = "SELECT  * FROM " + TABLE_QUESTION;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        // Move to first row
        cursor.moveToFirst();
        if (cursor.getCount() > 0) {
            N = new Question(cursor.getString(1), cursor.getString(2), cursor.getString(3));

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
        SQLiteDatabase db = getWritableDatabase();
        // Delete All Rows
        db.delete(TABLE_NURSE, null, null);
        db.close();

        Log.d(TAG, "Deleted all Nurse info from sqlite");
    }

    public void deletePatientComplete() {
        SQLiteDatabase db = getWritableDatabase();
        // Delete All Rows
        db.delete(TABLE_PATIENT, null, null);
        db.close();

        Log.d(TAG, "Deleted all Patient info from sqlite");
    }

    public void deleteResponseComplete() {
        SQLiteDatabase db = this.getWritableDatabase();
        // Delete All Rows
        db.delete(TABLE_RESPONSE, null, null);
        db.close();

        Log.d(TAG, "Deleted all Patient info from sqlite");
    }

    public void deleteQuestionComplete() {
        SQLiteDatabase db = this.getWritableDatabase();
        // Delete All Rows
        db.delete(TABLE_QUESTION, null, null);
        db.close();

        Log.d(TAG, "Deleted all Patient info from sqlite");
    }

}
