package com.example.u9.myapplication;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by Mitchell Bryant on 09/24/2017.
 */

public class DBHelper extends SQLiteOpenHelper {

    public static final String TABLE_TERMS = "terms";
    public static final String TABLE_COURSES = "courses";
    public static final String TABLE_ASSESSMENTS = "assessments";
    public static final String TABLE_MENTORS = "mentors";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_TITLE = "title";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_START = "start";
    public static final String COLUMN_END = "end";
    public static final String COLUMN_STATUS = "status";
    public static final String COLUMN_MENTOR = "mentor";
    public static final String COLUMN_NOTES = "notes";
    public static final String COLUMN_PASSESS= "performance";
    public static final String COLUMN_OASSESS = "objective";
    public static final String COLUMN_COURSES = "courseNumbers";
    public static final String COLUMN_MENTOR_ID = "_id";
    public static final String COLUMN_MENTOR_NAME = "Name";
    public static final String COLUMN_MENTOR_PHONE = "Phone";
    public static final String COLUMN_MENTOR_EMAIL = "Email";
    public static final String COLUMN_MENTOR_COURSE_ID = "CourseId";
    public static final String COLUMN_DESCRIPTION = "description";
    public static final String COLUMN_OGOAL = "odate";
    public static final String COLUMN_PGOAL = "pdate";
    public static final String COLUMN_START_ALERT = "startalert";
    public static final String COLUMN_END_ALERT = "endalert";
    public static final String COLUMN_GOAL_ALERT = "goalalert";
    public static final String COLUMN_GOAL_ALERTP = "goalalertp";


    private static final String DATABASE_NAME = "students.db";
    private static final int DATABASE_VERSION = 1;

   //  Database creation sql statement
    private static final String DATABASE_CREATE = "create table IF NOT EXISTS "
           + TABLE_TERMS + "( " + COLUMN_ID
            + " integer primary key autoincrement, " + COLUMN_NAME
            + " text not null, "
            + COLUMN_START
            + " text not null, "
            + COLUMN_END
            + " text not null, "
            + COLUMN_COURSES
            + " text) ;";
    private static final String DATABASE_CREATE2 = "create table IF NOT EXISTS "
            + TABLE_COURSES + "( " + COLUMN_ID
            + " integer primary key autoincrement, " +  COLUMN_NAME
            + " text not null, " + COLUMN_TITLE
            + " text not null, " + COLUMN_START
            + " text not null, " + COLUMN_END
            + " text not null, " + COLUMN_MENTOR
            + " integer not null, " + COLUMN_STATUS
            + " text not null, " + COLUMN_NOTES
            + " text not null, " + COLUMN_PASSESS
            + " integer , " + COLUMN_OASSESS
            + " integer , " + COLUMN_OGOAL
            + " text, " + COLUMN_PGOAL
            + " text, " + COLUMN_START_ALERT
            + " integer not null, " + COLUMN_END_ALERT
            + " integer not null, " + COLUMN_GOAL_ALERT
            + " integer not null, " + COLUMN_GOAL_ALERTP
            + " integer not null) ;";
    private static final String DATABASE_CREATE_ASSESSMENTS_TABLE = "create table IF NOT EXISTS "
            + TABLE_ASSESSMENTS + "( " + COLUMN_ID
            + " integer primary key autoincrement, " + COLUMN_MENTOR_COURSE_ID
            + " integer, "
            + COLUMN_NAME
            + " text not null, "
            + COLUMN_DESCRIPTION
            + " text) ;";
    private static final String DATABASE_CREATE_MENTORS_TABLE = "create table IF NOT EXISTS "
            + TABLE_MENTORS + "( " + COLUMN_MENTOR_ID
            + " integer primary key autoincrement, " +  COLUMN_NAME
            + " text not null, " + COLUMN_MENTOR_PHONE
            + " text not null, " + COLUMN_MENTOR_EMAIL
            + " text not null, " + COLUMN_MENTOR_COURSE_ID
            + " text) ;";
    private static final String MENTOR_ADD_DATA = "INSERT INTO " + TABLE_MENTORS + " (" + COLUMN_MENTOR_ID+","+COLUMN_MENTOR_NAME+","+COLUMN_MENTOR_PHONE+","+COLUMN_MENTOR_EMAIL+")"+ " VALUES " + "(1, "+ "'Jerry Young'" +", " + "'555-555-3456', " + "'jerry.young@test.com')" ;
    private static final String MENTOR_ADD_DATA2 = "INSERT INTO " + TABLE_MENTORS + " (" + COLUMN_MENTOR_ID+","+COLUMN_MENTOR_NAME+","+COLUMN_MENTOR_PHONE+","+COLUMN_MENTOR_EMAIL+")"+ " VALUES " + "(2, "+ "'Mary York'" +", " + "'555-574-3346', " + "'mary.york@school.com')" ;
    private static final String MENTOR_ADD_DATA3 = "INSERT INTO " + TABLE_MENTORS + " (" + COLUMN_MENTOR_ID+","+COLUMN_MENTOR_NAME+","+COLUMN_MENTOR_PHONE+","+COLUMN_MENTOR_EMAIL+")"+ " VALUES " + "(3, "+ "'Jon Snow'" +", " + "'555-777-8888', " + "'jon.snow@thewall.com')" ;
    private static final String ASS_ADD_DATA = "INSERT INTO " + TABLE_ASSESSMENTS + " (" + COLUMN_ID+","+COLUMN_MENTOR_COURSE_ID+","+COLUMN_NAME+","+COLUMN_DESCRIPTION+")"+ " VALUES " + "(1, "+ 1 +", " + "'Dummy Assessment', " + "'This is an autopopulated description')" ;
    private static final String COURSE_ADD_DATA = "INSERT INTO " + TABLE_COURSES + " (" + COLUMN_ID+","+COLUMN_NAME+","+COLUMN_TITLE+","+COLUMN_START+","+COLUMN_END+","+COLUMN_MENTOR+","+COLUMN_STATUS+","+COLUMN_NOTES+","+COLUMN_PASSESS+","+COLUMN_OASSESS+","+COLUMN_OGOAL+","+COLUMN_PGOAL+","+COLUMN_START_ALERT+","+COLUMN_END_ALERT+","+COLUMN_GOAL_ALERT+","+COLUMN_GOAL_ALERTP+")"+ " VALUES " + "(1, "+ "'Course Dummy'" +", " + "'Dummy Course', "  + "'12-12-2016', " +"'12-21-2017', "+ "1, "+ "'Plan to Take', "+ "'Dummy Notes', "+ "null, "+ "null, "+ "'', "+ "'', " + "1, " + "1, " + "1, " +"1 )" ;
    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase database) {
        database.execSQL(DATABASE_CREATE);
        database.execSQL(DATABASE_CREATE2);
        database.execSQL(DATABASE_CREATE_ASSESSMENTS_TABLE);
        database.execSQL(DATABASE_CREATE_MENTORS_TABLE);
        database.execSQL(MENTOR_ADD_DATA);
        database.execSQL(MENTOR_ADD_DATA2);
        database.execSQL(MENTOR_ADD_DATA3);
        database.execSQL(ASS_ADD_DATA);
        database.execSQL(COURSE_ADD_DATA);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w(DBHelper.class.getName(),
                "Upgrading database from version " + oldVersion + " to "
                        + newVersion + ", which will destroy all old data");
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_TERMS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_COURSES);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ASSESSMENTS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_MENTORS);
        db.execSQL(DATABASE_CREATE);
        db.execSQL(DATABASE_CREATE2);
        db.execSQL(DATABASE_CREATE_ASSESSMENTS_TABLE);
        db.execSQL(DATABASE_CREATE_MENTORS_TABLE);
        onCreate(db);
        
    }
}
