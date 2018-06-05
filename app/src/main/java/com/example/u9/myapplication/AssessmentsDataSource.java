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

public class AssessmentsDataSource {

    // Database fields
    private SQLiteDatabase database;
    private DBHelper dbHelper;
    private String[] allColumns = { DBHelper.COLUMN_ID,
            DBHelper.COLUMN_MENTOR_COURSE_ID, DBHelper.COLUMN_NAME, DBHelper.COLUMN_DESCRIPTION };
    public String blankSpace = "                ";

    public AssessmentsDataSource(Context context) {
        dbHelper = new DBHelper(context);
    }

    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }

    public Assessment createAssessment(long id, long courseID, String assessmentName, String assessmentDescription) {
        ContentValues values = new ContentValues();
        values.put(DBHelper.COLUMN_ID, id);
        values.put(DBHelper.COLUMN_MENTOR_COURSE_ID, courseID);
        values.put(DBHelper.COLUMN_NAME, assessmentName);
        values.put(DBHelper.COLUMN_DESCRIPTION, assessmentDescription);
        long insertId = database.insert(DBHelper.TABLE_ASSESSMENTS, null,
                values);
        Cursor cursor = database.query(DBHelper.TABLE_ASSESSMENTS,
                allColumns, DBHelper.COLUMN_ID + " = " + insertId, null,
                null, null, null);
        cursor.moveToFirst();
        Assessment newAssessment = cursorToAssessment(cursor);
        cursor.close();
        return newAssessment;
    }

    public void deleteAssessment(Assessment assessment) {
        long id = assessment.getAssessmentId();
        database.delete(DBHelper.TABLE_ASSESSMENTS, DBHelper.COLUMN_ID
                + " = " + id, null);
    }
    public void saveEditedAssessment(String name, String description, long courseID) {
        ContentValues cv = new ContentValues();
        cv.put(DBHelper.COLUMN_NAME, name);
        cv.put(DBHelper.COLUMN_DESCRIPTION, description);
        cv.put(DBHelper.COLUMN_MENTOR_COURSE_ID, courseID);
        database.update(DBHelper.TABLE_ASSESSMENTS, cv, DBHelper.COLUMN_ID + " = " + AssessmentActivity.assessment.getAssessmentId(),null  );

    }
    public Assessment getAssessmentById(long id) {
        Cursor cursor = database.query(DBHelper.TABLE_ASSESSMENTS,
                allColumns, DBHelper.COLUMN_ID + " = " + id, null, null, null, null);
        cursor.moveToFirst();
        Assessment newAssessment = cursorToAssessment(cursor);
        cursor.close();
        return newAssessment;
    }

    public List<Assessment> getAllAssessments() {
        List<Assessment> ass = new ArrayList<Assessment>();

        Cursor cursor = database.query(DBHelper.TABLE_ASSESSMENTS,
                allColumns, null, null, null, null, null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Assessment assessment = cursorToAssessment(cursor);
            ass.add(assessment);
            cursor.moveToNext();
        }
        // make sure to close the cursor
        cursor.close();
        return ass;
    }

    private Assessment cursorToAssessment(Cursor cursor) {
        Assessment assessment = new Assessment();
        assessment.setAssessmentId(cursor.getInt(0));
        assessment.setCourseId(cursor.getInt(1));
        assessment.setAssessmentName(cursor.getString(2));
        assessment.setAssessmentDescription(cursor.getString(3));
        return assessment;
    }
    public List<String> getAllAssessmentsString() {
        List<Assessment> ass = new ArrayList<Assessment>();
        List<String> assessmentList =new ArrayList<String>();
        Cursor cursor = database.query(DBHelper.TABLE_ASSESSMENTS,
                allColumns, null, null, null, null, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Assessment assessment = cursorToAssessment(cursor);
            ass.add(assessment);
            assessmentList.add(assessment.getAssessmentName());
            cursor.moveToNext();
        }
        // make sure to close the cursor
        cursor.close();
        return assessmentList;
    }
    public Assessment getItem(int position){
        return getAllAssessments().get(position);
    }
}