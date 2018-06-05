package com.example.u9.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mitchell Bryant on 09/24/2017.
 */

public class MentorsDataSource {

    // Database fields
    private SQLiteDatabase database;
    private DBHelper dbHelper;
    private String[] allColumns = { DBHelper.COLUMN_ID,
            DBHelper.COLUMN_NAME, DBHelper.COLUMN_MENTOR_PHONE, DBHelper.COLUMN_MENTOR_EMAIL, DBHelper.COLUMN_MENTOR_COURSE_ID };
    public String blankSpace = "                ";

    public MentorsDataSource(Context context) {
        dbHelper = new DBHelper(context);
    }

    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }

    public Mentor createMentor(long id, String name, String phone, String email, long courseID) {
        ContentValues values = new ContentValues();
        values.put(DBHelper.COLUMN_ID, id);
        values.put(DBHelper.COLUMN_NAME, name);
        values.put(DBHelper.COLUMN_MENTOR_PHONE, phone);
        values.put(DBHelper.COLUMN_MENTOR_EMAIL, email);
        values.put(DBHelper.COLUMN_MENTOR_COURSE_ID, courseID);

        long insertId = database.insert(DBHelper.TABLE_MENTORS, null,
                values);
        Cursor cursor = database.query(DBHelper.TABLE_MENTORS,
                allColumns, DBHelper.COLUMN_ID + " = " + insertId, null,
                null, null, null);
        cursor.moveToFirst();
        Mentor newMentor = cursorToMentor(cursor);
        cursor.close();
        return newMentor;
    }

    public void deleteMentor(Mentor mentor) {
        long id = mentor.getMentorId();
        System.out.println("Comment deleted with id: " + id);
        database.delete(DBHelper.TABLE_MENTORS, DBHelper.COLUMN_ID
                + " = " + id, null);
    }
    public Mentor getMentorById(long id) {
        Cursor cursor = database.query(DBHelper.TABLE_MENTORS,
                allColumns, DBHelper.COLUMN_ID + " = " + id, null, null, null, null);
        cursor.moveToFirst();
        Mentor newMentor = cursorToMentor(cursor);
        cursor.close();
        return newMentor;
    }

    public List<Mentor> getAllMentors() {
        List<Mentor> mentors = new ArrayList<Mentor>();

        Cursor cursor = database.query(DBHelper.TABLE_MENTORS,
                allColumns, null, null, null, null, null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Mentor mentor = cursorToMentor(cursor);
            mentors.add(mentor);
            cursor.moveToNext();
        }
        // make sure to close the cursor
        cursor.close();
        return mentors;
    }

    private Mentor cursorToMentor(Cursor cursor) {
        Mentor mentor = new Mentor();
        mentor.setMentorId(cursor.getInt(0));
        mentor.setMentorName(cursor.getString(1));
        mentor.setPhone(cursor.getString(2));
        mentor.setEmail(cursor.getString(3));
        mentor.setCourseId(cursor.getInt(4));

        return mentor;
    }
    public List<String> getAllMentorsString() {
        List<Mentor> mentors = new ArrayList<Mentor>();
        List<String> mentorList =new ArrayList<String>();
        Cursor cursor = database.query(DBHelper.TABLE_MENTORS,
                allColumns, null, null, null, null, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Mentor mentor = cursorToMentor(cursor);
            mentors.add(mentor);
            mentorList.add(mentor.getMentorName());
            cursor.moveToNext();
        }
        // make sure to close the cursor
        cursor.close();
        return mentorList;
    }
    public Mentor getItem(int position){
        return getAllMentors().get(position);
    }
}