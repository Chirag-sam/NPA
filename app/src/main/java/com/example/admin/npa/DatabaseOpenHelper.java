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
    private static final int DATABASE_VERSION =28;

    // Database Name
    private static final String DATABASE_NAME = "NPA";

    // Login table name
    private static final String TABLE_NURSE = "nurse";

    // Login Table Columns names
    private static final String NURSE_ID = "nid";
    private static final String NURSE_UNAME= "uname";
    private static final String NURSE_PASS= "password";
    private static final String NURSE_NAME = "nname";
    private static final String NURSE_GENDER= "gender";
    private static final String NURSE_LASTSYNC= "lastsync";
    private static final String NURSE_TC= "tc";
    private static final String NURSE_HOSPLOGO= "hosplogo";
    private static final String NURSE_HOSPNAME= "hospname";

    private static final String TABLE_PATIENT = "patient";

    // Login Table Columns names
    private static final String PATIENT_ID = "pid";
    private static final String PATIENT_NURSEID = "nurseid";
    private static final String PATIENT_NAME = "pname";
    private static final String PATIENT_GENDER = "gender";
    private static final String PATIENT_DOB = "dob";
    private static final String PATIENT_ILLNESS = "Illness";
    private static final String PATIENT_SURVEYID = "surveyid";
    private static final String PATIENT_SURVEYNAME = "surveyname";
    private static final String PATIENT_SURVEYDESC = "surveydesc";
    private static final String PATIENT_APPDATE = "appdate";
    private static final String PATIENT_STATUS = "status";
    private static final String PATIENT_REPDATE = "repdate";
    private static final String PATIENT_REPSCORE = "repscore";
    private static final String PATIENT_REPMAXSCORE = "repmaxscore";



    private static final String TABLE_RESPONSE = "response";

    // Login Table Columns names

    private static final String RESPONSE_ID = "pid";
    private static final String RESPONSE_QID = "qid";
    private static final String RESPONSE_QUESTION= "question";
    private static final String RESPONSE_ANSWER = "answer";
    private static final String RESPONSE_SCORE = "score";
    private static final String RESPONSE_MAXSCORE = "maxscore";



    private static final String TABLE_QUESTION = "questions";

    // Login Table Columns names

    private static final String QUESTION_ID = "qid";
    private static final String QUESTION_DESC = "qdesc";
    private static final String QUESTION_TYPE = "restype";
    private static final String QUESTION_OPTIONS = "options";
    private static final String QUESTION_SURVEYTYPE = "surveytype";



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
                NURSE_GENDER+ " TEXT, "+
                NURSE_LASTSYNC+ " TEXT, "+
                NURSE_TC+ " TEXT, "+
                NURSE_HOSPLOGO+ " TEXT, "+
                NURSE_HOSPNAME+ " TEXT "+


                ")";
        sqLiteDatabase.execSQL(CREATE_NURSE_TABLE);
        String CREATE_PATIENT_TABLE = "CREATE TABLE " + TABLE_PATIENT +
                " ("+
                PATIENT_ID + " TEXT NOT NULL PRIMARY KEY," +
                PATIENT_NURSEID + " TEXT," +
                PATIENT_NAME + " TEXT," +
                PATIENT_GENDER + " TEXT," +
                PATIENT_DOB + " TEXT," +
                PATIENT_ILLNESS + " TEXT," +
                PATIENT_SURVEYID + " TEXT," +
                PATIENT_SURVEYNAME + " TEXT," +
                PATIENT_SURVEYDESC+ " TEXT," +
                PATIENT_APPDATE + " TEXT," +
                PATIENT_STATUS + " TEXT," +
                PATIENT_REPDATE+ " TEXT," +
                PATIENT_REPSCORE + " TEXT," +
                PATIENT_REPMAXSCORE + " TEXT" +
                 ")";
        sqLiteDatabase.execSQL(CREATE_PATIENT_TABLE);

        String CREATE_RESPONSE_TABLE = "CREATE TABLE " + TABLE_RESPONSE + " ("
                + RESPONSE_ID + " TEXT," +
                RESPONSE_QID + " TEXT ,"
                +RESPONSE_QUESTION+" TEXT ,"
                + RESPONSE_ANSWER + " TEXT, "
                + RESPONSE_SCORE + " TEXT, "
                + RESPONSE_MAXSCORE + " TEXT "
                + ")";
        sqLiteDatabase.execSQL(CREATE_RESPONSE_TABLE);
        String CREATE_QUESTION_TABLE = "CREATE TABLE " + TABLE_QUESTION + " ("
                + QUESTION_ID + " TEXT NOT NULL PRIMARY KEY,"
                + QUESTION_DESC + " TEXT,"
                + QUESTION_TYPE + " TEXT ,"
                + QUESTION_OPTIONS + " TEXT ,"
                + QUESTION_SURVEYTYPE + " TEXT "

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
        values.put(PATIENT_NURSEID, N.getNurseid());
        values.put(PATIENT_NAME, N.getFname());
        values.put(PATIENT_GENDER, N.getGender());
        values.put(PATIENT_DOB, N.getDob());
        values.put(PATIENT_ILLNESS, N.getDisease());
        values.put(PATIENT_SURVEYID, N.getSurveyid());
        values.put(PATIENT_SURVEYNAME, N.getSurveyname());
        values.put(PATIENT_SURVEYDESC, N.getSurveydesc());
        values.put(PATIENT_APPDATE, N.getAppdate());
        values.put(PATIENT_STATUS, N.getStatus());
        values.put(PATIENT_REPDATE, N.getRepdate());
        values.put(PATIENT_REPSCORE, N.getRepscore());
        values.put(PATIENT_REPMAXSCORE, N.getRepmaxscore());
        // Inserting Row
        long id = db.insert(TABLE_PATIENT, null, values);
        db.close(); // Closing database connection

        Log.d(TAG, "New Nurse inserted into sqlite: " + id);
    }
    public void updatepatient(PatientJ N) {
        SQLiteDatabase db = getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(PATIENT_ID, N.getPid());
        values.put(PATIENT_NURSEID, N.getNurseid());
        values.put(PATIENT_NAME, N.getFname());// Name         // Email
        values.put(PATIENT_GENDER, N.getGender());
        values.put(PATIENT_DOB, N.getDob());
        values.put(PATIENT_ILLNESS, N.getDisease());
        values.put(PATIENT_SURVEYID, N.getSurveyid());
        values.put(PATIENT_SURVEYNAME, N.getSurveyname());
        values.put(PATIENT_SURVEYDESC, N.getSurveydesc());
        values.put(PATIENT_APPDATE, N.getAppdate());
        values.put(PATIENT_STATUS, N.getStatus());
        values.put(PATIENT_REPDATE, N.getRepdate());
        values.put(PATIENT_REPSCORE, N.getRepscore());
        values.put(PATIENT_REPMAXSCORE, N.getRepmaxscore());
        // Inserting Row
        long id = db.update(TABLE_PATIENT, values, PATIENT_ID+" = '"+N.getPid()+"'",null);
        db.close(); // Closing database connection

        Log.d(TAG, "New Nurse inserted into sqlite: " + id);
    }
    public void addallpatients(List<PatientJ> List,String nid) {
        SQLiteDatabase db = getWritableDatabase();

        for (PatientJ N:List) {
            if (N.getStatus()==null)
                N.setStatus("pending");
            if (N.getNurseid()==null)
                N.setNurseid(nid);
            addPatient(N);
            /*
            ContentValues values = new ContentValues();
            values.put(PATIENT_ID, N.getPid());
            values.put(PATIENT_NAME, N.getName());
            values.put(PATIENT_GENDER, N.getGender());
            values.put(PATIENT_AGE, N.getAge());// Name         // Email
            values.put(PATIENT_REPDATE, N.getRepdate());
            values.put(PATIENT_ILLNESS, N.getDisease());
            values.put(PATIENT_NID, N.getNid());
            values.put(PATIENT_STATUS, N.getStatus());

            // Inserting Row
            long id = db.insert(TABLE_PATIENT, null, values);*/
        }
        db.close(); // Closing database connection

    }
    public void addallqns(List<Question> List) {
        SQLiteDatabase db = getWritableDatabase();

        for (Question N:List) {
            addQuestion(N);
            /*
            ContentValues values = new ContentValues();
            values.put(PATIENT_ID, N.getPid());
            values.put(PATIENT_NAME, N.getName());
            values.put(PATIENT_GENDER, N.getGender());
            values.put(PATIENT_AGE, N.getAge());// Name         // Email
            values.put(PATIENT_REPDATE, N.getRepdate());
            values.put(PATIENT_ILLNESS, N.getDisease());
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
        values.put(NURSE_NAME, N.getFirstname());
        values.put(NURSE_GENDER, N.getGender());
        values.put(NURSE_LASTSYNC, N.getLastsync());
        values.put(NURSE_TC, N.getTcs());
        values.put(NURSE_HOSPLOGO, N.getHosplogo());
        values.put(NURSE_HOSPNAME, N.getHospname());
        long id = db.insert(TABLE_NURSE, null, values);
        db.close();
        Log.d(TAG, "New Nurse inserted into sqlite: " + id);

    }


    public void updatenurse(Nurse N) {
        SQLiteDatabase db =getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(NURSE_ID, N.getNid());
        values.put(NURSE_UNAME, N.getUname());
        values.put(NURSE_PASS, N.getPassword());
        values.put(NURSE_NAME, N.getFirstname());
        values.put(NURSE_GENDER, N.getGender());
        values.put(NURSE_LASTSYNC, N.getLastsync());
        values.put(NURSE_TC, N.getTcs());
        values.put(NURSE_HOSPLOGO, N.getHosplogo());
        values.put(NURSE_HOSPNAME, N.getHospname());
        long id = db.update(TABLE_NURSE, values, NURSE_ID+" = '"+N.getNid()+"'",null);
        db.close();
        Log.d(TAG, "New Nurse inserted into sqlite: " + id);

    }
    public void addResponse(Question N,String uid) {
        SQLiteDatabase db = getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(RESPONSE_ID,uid);
        values.put(RESPONSE_QID, N.getQid());
        values.put(RESPONSE_QUESTION,N.getQdesc());
        values.put(RESPONSE_ANSWER, N.getAnswer());
        values.put(RESPONSE_SCORE, N.getScore());
        values.put(RESPONSE_MAXSCORE, N.getMaxscore());

        // Inserting Row
        long id = db.insert(TABLE_RESPONSE, null, values);
        db.close(); // Closing database connection

        Log.d(TAG, "New Nurse inserted into sqlite: " + id);
    }
    public void addallresponse(ArrayList<Question> N,String uid) {
        SQLiteDatabase db = getWritableDatabase();

        for (Question x:N)
            addResponse(x,uid);
        db.close(); // Closing database connection


    }

    public void addQuestion(Question N) {
        SQLiteDatabase db = getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(QUESTION_ID, N.getQid());
        values.put(QUESTION_DESC, N.getQdesc());
        values.put(QUESTION_TYPE, N.getRestype());
        values.put(QUESTION_OPTIONS, N.getOption());
        values.put(QUESTION_SURVEYTYPE, N.getDiseasetype());


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
            N=new Nurse(cursor.getString(0),cursor.getString(1),cursor.getString(2),cursor.getString(3),cursor.getString(4),cursor.getString(5),cursor.getString(6),cursor.getString(7),cursor.getString(8));

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
        cursor.close();
        db.close();
        if(icount==1)
            return true;
        else {
            deleteNurseComplete();
            return false;}

        // return user

    }

    public int getcountcompleted()
    {
        String selectQuery = "SELECT count(*) FROM " + TABLE_PATIENT+" WHERE "+PATIENT_STATUS+" ='completed' ";

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // Move to first row
        cursor.moveToFirst();
        int icount = cursor.getInt(0);

        cursor.close();
        db.close();
        // return user

        return icount;
    }
    public int getcountpending()
    {
        String selectQuery = "SELECT count(*) FROM " + TABLE_PATIENT+" WHERE "+PATIENT_STATUS+" ='pending' ";

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // Move to first row
        cursor.moveToFirst();
        int icount = cursor.getInt(0);

        cursor.close();
        db.close();
        // return user

        return icount;
    }

    public PatientJ getPatient(String uid) {
        PatientJ N = new PatientJ();
        String selectQuery = "SELECT  * FROM " + TABLE_PATIENT+" WHERE "+PATIENT_ID+" ='"+uid+"'";

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        // Move to first row
        cursor.moveToFirst();
        if (cursor.getCount() > 0) {
            N = new PatientJ(cursor.getString(0), cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4), cursor.getString(5), cursor.getString(6), cursor.getString(7), cursor.getString(8),cursor.getString(9),cursor.getString(10),cursor.getString(11),cursor.getString(12),cursor.getString(13));

        }
        cursor.close();
        db.close();
        // return user

        return N;
    }

    public List<Question> getallResponse(String pid) {
        List<Question> N = new ArrayList<>();
        String selectQuery = "SELECT  * FROM " + TABLE_RESPONSE+ " WHERE "+RESPONSE_ID+"='"+pid+"'";


        SQLiteDatabase db = getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {

                N.add(new Question(null,cursor.getString(1), cursor.getString(2), cursor.getString(3),cursor.getString(4),cursor.getString(5)));

                // Adding contact to list
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();

        return N;
    }
    public List<Question> getallpatientsResponse() {
        List<Question> N = new ArrayList<>();
        String selectQuery = "SELECT  * FROM " + TABLE_RESPONSE;


        SQLiteDatabase db = getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {

                N.add(new Question(cursor.getString(0),cursor.getString(1), cursor.getString(2), cursor.getString(3),cursor.getString(4),cursor.getString(5)));

                // Adding contact to list
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();

        return N;
    }

    public ArrayList<PatientJ> getAllPatientspend() {
        ArrayList<PatientJ> patientlist = new ArrayList<PatientJ>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_PATIENT+ " WHERE "+PATIENT_STATUS + " = 'pending' OR "+PATIENT_STATUS + " IS NULL";

        SQLiteDatabase db = getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {

              patientlist.add(new PatientJ(cursor.getString(0), cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4), cursor.getString(5), cursor.getString(6), cursor.getString(7), cursor.getString(8),cursor.getString(9),cursor.getString(10)));

                // Adding contact to list
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        // return contact list
        return patientlist;
    }
    public ArrayList<PatientJ> getAllPatientcomp() {
        ArrayList<PatientJ> patientlist = new ArrayList<PatientJ>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_PATIENT+ " WHERE "+PATIENT_STATUS + " = 'completed'";

        SQLiteDatabase db = getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {

                patientlist.add(new PatientJ(cursor.getString(0), cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4), cursor.getString(5), cursor.getString(6), cursor.getString(7), cursor.getString(8),cursor.getString(9),cursor.getString(10),cursor.getString(11),cursor.getString(12),cursor.getString(13)));

                // Adding contact to list
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        // return contact list
        return patientlist;
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

                patientlist.add(new PatientJ(cursor.getString(0), cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4), cursor.getString(5), cursor.getString(6), cursor.getString(7), cursor.getString(8),cursor.getString(9),cursor.getString(10),cursor.getString(11),cursor.getString(12),cursor.getString(13)));


                // Adding contact to list
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        // return contact list
        return patientlist;
    }
    public ArrayList<String> getallIllnesss() {
        ArrayList<String> questions = new ArrayList<String>();
        // Select All Query
        String selectQuery = "SELECT  "+PATIENT_ILLNESS+" FROM " + TABLE_PATIENT;

        SQLiteDatabase db = getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {

                questions.add(cursor.getString(0));

                // Adding contact to list
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        // return contact list
        return questions;
    }
    public ArrayList<Question> getallquestions(String surveytype) {
        ArrayList<Question> questions = new ArrayList<Question>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_QUESTION+ " WHERE "+QUESTION_SURVEYTYPE + " = '"+surveytype+"'";

        SQLiteDatabase db = getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {

                questions.add(new Question(cursor.getString(0), cursor.getString(1), cursor.getString(2), cursor.getString(3),cursor.getString(4)));

                // Adding contact to list
            } while (cursor.moveToNext());

        }
        cursor.close();
        db.close();
        // return contact list
        return questions;
    }

//    public Question getQuestion() {
//        Question N = new Question();
//        String selectQuery = "SELECT  * FROM " + TABLE_QUESTION;
//
//        SQLiteDatabase db = this.getReadableDatabase();
//        Cursor cursor = db.rawQuery(selectQuery, null);
//        // Move to first row
//        cursor.moveToFirst();
//        if (cursor.getCount() > 0) {
//            N = new Question(cursor.getString(0), cursor.getString(1), cursor.getString(2), cursor.getString(3));
//
//        }
//        cursor.close();
//        db.close();
//        // return user
//
//        return N;
//    }

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
        SQLiteDatabase db = getWritableDatabase();
        // Delete All Rows
        db.delete(TABLE_RESPONSE, null, null);
        db.close();

        Log.d(TAG, "Deleted all Patient info from sqlite");
    }

    public void deleteQuestionComplete() {
        SQLiteDatabase db = getWritableDatabase();
        // Delete All Rows
        db.delete(TABLE_QUESTION, null, null);
        db.close();

        Log.d(TAG, "Deleted all Patient info from sqlite");
    }

}
