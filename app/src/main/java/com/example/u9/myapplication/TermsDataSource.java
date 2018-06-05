package com.example.u9.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mitchell Bryant on 09/24/2017.
 */

public class TermsDataSource {

    // Database fields
    private SQLiteDatabase database;
    private DBHelper dbHelper;
    private String[] allColumns = { DBHelper.COLUMN_ID,
            DBHelper.COLUMN_NAME, DBHelper.COLUMN_START, DBHelper.COLUMN_END, dbHelper.COLUMN_COURSES };
    private String[] allCourseColumns = { DBHelper.COLUMN_ID,
            DBHelper.COLUMN_NAME, DBHelper.COLUMN_TITLE, DBHelper.COLUMN_START, DBHelper.COLUMN_END, DBHelper.COLUMN_MENTOR,  DBHelper.COLUMN_STATUS, DBHelper.COLUMN_NOTES, DBHelper.COLUMN_PASSESS, DBHelper.COLUMN_OASSESS, DBHelper.COLUMN_OGOAL, DBHelper.COLUMN_PGOAL };
    public String blankSpace = "                ";

    public TermsDataSource(Context context) {
        dbHelper = new DBHelper(context);
    }

    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }

    public Term createTerm(long id, String name, String start, String end, String courseNums) {
        ContentValues values = new ContentValues();
        values.put(DBHelper.COLUMN_ID, id);
        values.put(DBHelper.COLUMN_NAME, name);
        values.put(DBHelper.COLUMN_START, start);
        values.put(DBHelper.COLUMN_END, end);
        values.put(DBHelper.COLUMN_COURSES, courseNums);
        long insertId = database.insert(DBHelper.TABLE_TERMS, null,
                values);
        Cursor cursor = database.query(DBHelper.TABLE_TERMS,
                allColumns, DBHelper.COLUMN_ID + " = " + insertId, null,
                null, null, null);
        cursor.moveToFirst();
        Term newTerm = cursorToTerm(cursor);
        cursor.close();
        return newTerm;
    }

    public void deleteTerm(Term term) {
        long id = term.getTermNo();
        System.out.println("Comment deleted with id: " + id);
        database.delete(DBHelper.TABLE_TERMS, DBHelper.COLUMN_ID
                + " = " + id, null);
    }
    public void saveEditedTerm(String name, String start, String end, String courseNums) {
        ContentValues cv = new ContentValues();
        cv.put(DBHelper.COLUMN_NAME, name);
        cv.put(DBHelper.COLUMN_START, start);
        cv.put(DBHelper.COLUMN_END, end);
        cv.put(DBHelper.COLUMN_COURSES, courseNums);
        database.update(DBHelper.TABLE_TERMS, cv, DBHelper.COLUMN_ID + " = " + TermActivity.term.getTermNo(),null  );

    }

    public List<Term> getAllTerms() {
        List<Term> terms = new ArrayList<Term>();

        Cursor cursor = database.query(DBHelper.TABLE_TERMS,
                allColumns, null, null, null, null, null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Term term = cursorToTerm(cursor);
            terms.add(term);
            cursor.moveToNext();
        }
        // make sure to close the cursor
        cursor.close();
        return terms;
    }

    private Term cursorToTerm(Cursor cursor) {
        Term term = new Term();
        term.setTermNo(cursor.getInt(0));
        term.setTermName(cursor.getString(1));
        term.setTermStartDate(cursor.getString(2));
        term.setTermEndDate(cursor.getString(3));
        term.setCourses(cursor.getString(4));
        return term;
    }
    public List<String> getAllTermsString() {
        List<Term> terms = new ArrayList<Term>();
        List<String> termList =new ArrayList<String>();
        Cursor cursor = database.query(DBHelper.TABLE_TERMS,
                allColumns, null, null, null, null, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Term term = cursorToTerm(cursor);
            terms.add(term);
            termList.add(term.getTermName() + blankSpace + term.getTermStartDate() + " - " + term.getTermEndDate());
            cursor.moveToNext();
        }
        // make sure to close the cursor
        cursor.close();
        return termList;
    }
    public Term getItem(int position){
        return getAllTerms().get(position);
    }


    private Course cursorToCourse(Cursor cursor) {
        Log.d("test", String.valueOf(cursor.getCount()));
        Course course = new Course();
        course.setCourseId(cursor.getInt(0));
        course.setName(cursor.getString(1));
        course.setTitle(cursor.getString(2));
        course.setStartDate(cursor.getString(3));
        course.setEndDate(cursor.getString(4));
        course.setMentor(cursor.getInt(5));
        course.setStatus(cursor.getString(6));
        course.setNotes(cursor.getString(7));
        course.setpAssessment(cursor.getInt(8) );
        course.setoAssessment(cursor.getInt(9) );
        course.setoGoalDate(cursor.getString(10));
        course.setpGoalDate(cursor.getString(11));
        return course;
    }
    public List<String> getCoursesString() {
        List<Course> courses = new ArrayList<Course>();
        List<String> courseList =new ArrayList<String>();
        Cursor cursor = database.query(DBHelper.TABLE_COURSES,
                allCourseColumns, DBHelper.COLUMN_ID + " IN ( " + TermActivity.term.getCourses() + ")" , null, null, null, null, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Course course = cursorToCourse(cursor);
            courses.add(course);
            courseList.add(course.getName() + blankSpace + course.getTitle());
            cursor.moveToNext();
        }
        // make sure to close the cursor
        cursor.close();
        return courseList;
    }
    public List<Course> getCourses() {
        List<Course> courses = new ArrayList<Course>();
        Cursor cursor = database.query(DBHelper.TABLE_COURSES,
                allCourseColumns, DBHelper.COLUMN_ID + " IN ( " + TermActivity.term.getCourses() + ")" , null, null, null, null, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Course course = cursorToCourse(cursor);
            courses.add(course);
            cursor.moveToNext();
        }
        cursor.close();
        return courses;
    }
}
