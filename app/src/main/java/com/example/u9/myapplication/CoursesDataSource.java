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

public class CoursesDataSource {

    // Database fields
    private SQLiteDatabase database;
    private DBHelper dbHelper;
    private String[] allColumns = { DBHelper.COLUMN_ID, DBHelper.COLUMN_NAME, DBHelper.COLUMN_TITLE, DBHelper.COLUMN_START, DBHelper.COLUMN_END, DBHelper.COLUMN_MENTOR,  DBHelper.COLUMN_STATUS, DBHelper.COLUMN_NOTES, DBHelper.COLUMN_PASSESS, DBHelper.COLUMN_OASSESS, DBHelper.COLUMN_OGOAL, DBHelper.COLUMN_PGOAL, DBHelper.COLUMN_START_ALERT, DBHelper.COLUMN_END_ALERT, DBHelper.COLUMN_GOAL_ALERT, DBHelper.COLUMN_GOAL_ALERTP };
    private String[] allColumnsO = { DBHelper.COLUMN_ID, DBHelper.COLUMN_NAME, DBHelper.COLUMN_TITLE, DBHelper.COLUMN_START, DBHelper.COLUMN_END, DBHelper.COLUMN_MENTOR,  DBHelper.COLUMN_STATUS, DBHelper.COLUMN_NOTES, DBHelper.COLUMN_OASSESS, DBHelper.COLUMN_OGOAL, DBHelper.COLUMN_START_ALERT, DBHelper.COLUMN_END_ALERT, DBHelper.COLUMN_GOAL_ALERT, DBHelper.COLUMN_GOAL_ALERTP };
    private String[] allColumnsP = { DBHelper.COLUMN_ID, DBHelper.COLUMN_NAME, DBHelper.COLUMN_TITLE, DBHelper.COLUMN_START, DBHelper.COLUMN_END, DBHelper.COLUMN_MENTOR,  DBHelper.COLUMN_STATUS, DBHelper.COLUMN_NOTES, DBHelper.COLUMN_PASSESS,  DBHelper.COLUMN_PGOAL, DBHelper.COLUMN_START_ALERT, DBHelper.COLUMN_END_ALERT, DBHelper.COLUMN_GOAL_ALERT, DBHelper.COLUMN_GOAL_ALERTP };
    private String[] allColumnsNo = { DBHelper.COLUMN_ID, DBHelper.COLUMN_NAME, DBHelper.COLUMN_TITLE, DBHelper.COLUMN_START, DBHelper.COLUMN_END, DBHelper.COLUMN_MENTOR,  DBHelper.COLUMN_STATUS, DBHelper.COLUMN_NOTES, DBHelper.COLUMN_START_ALERT, DBHelper.COLUMN_END_ALERT };
    private String[] allMentorColumns = { DBHelper.COLUMN_MENTOR_ID, DBHelper.COLUMN_MENTOR_NAME, DBHelper.COLUMN_MENTOR_PHONE, DBHelper.COLUMN_MENTOR_EMAIL, DBHelper.COLUMN_MENTOR_COURSE_ID};
    public String blankSpace = "                ";

    public CoursesDataSource(Context context) {
        dbHelper = new DBHelper(context);
    }

    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }

    public Course createCourse(long id, String name, String title, String start, String end, long mentors, String status, String notes, long passess, long oassess, String ogoal, String pgoal, long startalert, long endalert, long goalalert, long goalalertp ) {
        ContentValues values = new ContentValues();
        values.put(DBHelper.COLUMN_ID, id);
        values.put(DBHelper.COLUMN_NAME, name);
        values.put(DBHelper.COLUMN_TITLE, title);
        values.put(DBHelper.COLUMN_START, start);
        values.put(DBHelper.COLUMN_END, end);
        values.put(DBHelper.COLUMN_MENTOR, mentors);
        values.put(DBHelper.COLUMN_STATUS, status);
        values.put(DBHelper.COLUMN_NOTES, notes);
        values.put(DBHelper.COLUMN_PASSESS, passess);
        values.put(DBHelper.COLUMN_OASSESS, oassess);
        values.put(DBHelper.COLUMN_OGOAL, ogoal);
        values.put(DBHelper.COLUMN_PGOAL, pgoal);
        values.put(DBHelper.COLUMN_START_ALERT, startalert);
        values.put(DBHelper.COLUMN_END_ALERT, endalert);
        values.put(DBHelper.COLUMN_GOAL_ALERT, goalalert);
        values.put(DBHelper.COLUMN_GOAL_ALERTP, goalalertp);
        long insertId = database.insert(DBHelper.TABLE_COURSES, null,
                values);
        Cursor cursor = database.query(DBHelper.TABLE_COURSES,
                allColumns, DBHelper.COLUMN_ID + " = " + insertId, null,
                null, null, null);
        cursor.moveToFirst();
        Course newCourse = cursorToCourse(cursor);
        cursor.close();
        return newCourse;
    }
    public Course createCourseO(long id, String name, String title, String start, String end, long mentors, String status, String notes, long oassess, String ogoal, long startalert, long endalert, long goalalert, long goalalertp) {
        ContentValues values = new ContentValues();
        values.put(DBHelper.COLUMN_ID, id);
        values.put(DBHelper.COLUMN_NAME, name);
        values.put(DBHelper.COLUMN_TITLE, title);
        values.put(DBHelper.COLUMN_START, start);
        values.put(DBHelper.COLUMN_END, end);
        values.put(DBHelper.COLUMN_MENTOR, mentors);
        values.put(DBHelper.COLUMN_STATUS, status);
        values.put(DBHelper.COLUMN_NOTES, notes);
        values.put(DBHelper.COLUMN_PASSESS, "null");
        values.put(DBHelper.COLUMN_OASSESS, oassess);
        values.put(DBHelper.COLUMN_OGOAL, ogoal);
        values.put(DBHelper.COLUMN_PGOAL, "");
        values.put(DBHelper.COLUMN_START_ALERT, startalert);
        values.put(DBHelper.COLUMN_END_ALERT, endalert);
        values.put(DBHelper.COLUMN_GOAL_ALERT, goalalert);
        values.put(DBHelper.COLUMN_GOAL_ALERTP, goalalertp);
        long insertId = database.insert(DBHelper.TABLE_COURSES, null,
                values);
        Cursor cursor = database.query(DBHelper.TABLE_COURSES,
                allColumnsO, DBHelper.COLUMN_ID + " = " + insertId, null,
                null, null, null);
        cursor.moveToFirst();
        Course newCourse = cursorToCourseO(cursor);
        cursor.close();
        return newCourse;
    }
    public Course createCourseP(long id, String name, String title, String start, String end, long mentors, String status, String notes, long passess, String pgoal, long startalert, long endalert, long goalalert, long goalalertp) {
        ContentValues values = new ContentValues();
        values.put(DBHelper.COLUMN_ID, id);
        values.put(DBHelper.COLUMN_NAME, name);
        values.put(DBHelper.COLUMN_TITLE, title);
        values.put(DBHelper.COLUMN_START, start);
        values.put(DBHelper.COLUMN_END, end);
        values.put(DBHelper.COLUMN_MENTOR, mentors);
        values.put(DBHelper.COLUMN_STATUS, status);
        values.put(DBHelper.COLUMN_NOTES, notes);
        values.put(DBHelper.COLUMN_PASSESS, passess);
        values.put(DBHelper.COLUMN_OASSESS, "null");
        values.put(DBHelper.COLUMN_OGOAL, "");
        values.put(DBHelper.COLUMN_PGOAL, pgoal);
        values.put(DBHelper.COLUMN_START_ALERT, startalert);
        values.put(DBHelper.COLUMN_END_ALERT, endalert);
        values.put(DBHelper.COLUMN_GOAL_ALERT, goalalert);
        values.put(DBHelper.COLUMN_GOAL_ALERTP, goalalertp);
        long insertId = database.insert(DBHelper.TABLE_COURSES, null,
                values);
        Cursor cursor = database.query(DBHelper.TABLE_COURSES,
                allColumnsP, DBHelper.COLUMN_ID + " = " + insertId, null,
                null, null, null);
        cursor.moveToFirst();
        Course newCourse = cursorToCourseP(cursor);
        cursor.close();
        return newCourse;
    }
    public Course createCourseNoAssessments(long id, String name, String title, String start, String end, long mentors, String status, String notes, long startalert, long endalert) {
        ContentValues values = new ContentValues();
        values.put(DBHelper.COLUMN_ID, id);
        values.put(DBHelper.COLUMN_NAME, name);
        values.put(DBHelper.COLUMN_TITLE, title);
        values.put(DBHelper.COLUMN_START, start);
        values.put(DBHelper.COLUMN_END, end);
        values.put(DBHelper.COLUMN_MENTOR, mentors);
        values.put(DBHelper.COLUMN_STATUS, status);
        values.put(DBHelper.COLUMN_NOTES, notes);
        values.put(DBHelper.COLUMN_PASSESS, 0);
        values.put(DBHelper.COLUMN_OASSESS, 0);
        values.put(DBHelper.COLUMN_OGOAL, "");
        values.put(DBHelper.COLUMN_PGOAL, "");
        values.put(DBHelper.COLUMN_START_ALERT, startalert);
        values.put(DBHelper.COLUMN_END_ALERT, endalert);
        values.put(DBHelper.COLUMN_GOAL_ALERT, 0);
        values.put(DBHelper.COLUMN_GOAL_ALERTP, 0);

        long insertId = database.insert(DBHelper.TABLE_COURSES, null,
                values);
        Cursor cursor = database.query(DBHelper.TABLE_COURSES,
                allColumnsNo, DBHelper.COLUMN_ID + " = " + insertId, null,
                null, null, null);
        cursor.moveToFirst();
        Course newCourse = cursorToCourseNo(cursor);
        cursor.close();
        return newCourse;
    }

    public void deleteCourse(Course course) {
        long id = course.getCourseId();
        System.out.println("Comment deleted with id: " + id);
        database.delete(DBHelper.TABLE_COURSES, DBHelper.COLUMN_ID
                + " = " + id, null);
    }
    public Course getCourseById(long id) {
        Cursor cursor = database.query(DBHelper.TABLE_COURSES,
                allColumns, DBHelper.COLUMN_ID + " = " + id, null, null, null, null);
        cursor.moveToFirst();
        Course newCourse = cursorToCourse(cursor);
        cursor.close();
        return newCourse;
    }

    public List<Course> getAllCourses() {
        List<Course> courses = new ArrayList<Course>();

        Cursor cursor = database.query(DBHelper.TABLE_COURSES,
                allColumns, null, null, null, null, null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Course course = cursorToCourse(cursor);
            courses.add(course);
            cursor.moveToNext();
        }
        // make sure to close the cursor
        cursor.close();
        return courses;
    }

    public static Course cursorToCourse(Cursor cursor) {
        Course course = new Course();
        course.setCourseId(cursor.getInt(0));
        course.setName(cursor.getString(1));
        course.setTitle(cursor.getString(2));
        course.setStartDate(cursor.getString(3));
        course.setEndDate(cursor.getString(4));
        course.setMentor(cursor.getInt(5));
        course.setStatus(cursor.getString(6));
        course.setNotes(cursor.getString(7));
        course.setpAssessment(cursor.getInt(8));
        course.setoAssessment(cursor.getInt(9));
        course.setoGoalDate(cursor.getString(10));
        course.setpGoalDate(cursor.getString(11));
        course.setStartAlert(cursor.getInt(12)>0);
        course.setEndAlert(cursor.getInt(13)>0);
        course.setGoalAlert(cursor.getInt(14)>0);
        course.setGoalAlertP(cursor.getInt(15)>0);
        return course;
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
    public List<String> getAllCoursesString() {
        List<Course> courses = new ArrayList<Course>();
        List<String> courseList =new ArrayList<String>();
        Cursor cursor = database.query(DBHelper.TABLE_COURSES,
                allColumns, null, null, null, null, null);
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
    public List<String> getAllMentorsString() {
        List<Mentor> mentors = new ArrayList<Mentor>();
        List<String> mentorList =new ArrayList<String>();
        Cursor cursor = database.query(DBHelper.TABLE_MENTORS,
                allMentorColumns, null, null, null, null, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Mentor mentor = cursorToMentor(cursor);
            mentors.add(mentor);
            mentorList.add(mentor.getMentorName() + blankSpace + mentor.getPhone());
            cursor.moveToNext();
        }
        cursor.close();
        return mentorList;
    }
    public Course getItem(int position){
        return getAllCourses().get(position);
    }

    public void saveEditedNotes(String notes) {
        ContentValues cv = new ContentValues();
        cv.put(DBHelper.COLUMN_NOTES, notes);
        database.update(DBHelper.TABLE_COURSES, cv, DBHelper.COLUMN_ID + " = " + CourseActivity.course.getCourseId(),null  );
    }
    private Course cursorToCourseO(Cursor cursor) {
        Course course = new Course();
        course.setCourseId(cursor.getInt(0));
        course.setName(cursor.getString(1));
        course.setTitle(cursor.getString(2));
        course.setStartDate(cursor.getString(3));
        course.setEndDate(cursor.getString(4));
        course.setMentor(cursor.getInt(5));
        course.setStatus(cursor.getString(6));
        course.setNotes(cursor.getString(7));
        course.setoAssessment(cursor.getInt(8));
        course.setoGoalDate(cursor.getString(9));
        course.setStartAlert(cursor.getInt(10)>0);
        course.setEndAlert(cursor.getInt(11)>0);
        course.setGoalAlert(cursor.getInt(12)>0);
        course.setGoalAlertP(cursor.getInt(13)>0);
        return course;
    }
    private Course cursorToCourseP(Cursor cursor) {
        Course course = new Course();
        course.setCourseId(cursor.getInt(0));
        course.setName(cursor.getString(1));
        course.setTitle(cursor.getString(2));
        course.setStartDate(cursor.getString(3));
        course.setEndDate(cursor.getString(4));
        course.setMentor(cursor.getInt(5));
        course.setStatus(cursor.getString(6));
        course.setNotes(cursor.getString(7));
        course.setpAssessment(cursor.getInt(8));
        course.setpGoalDate(cursor.getString(9));
        course.setStartAlert(cursor.getInt(10)>0);
        course.setEndAlert(cursor.getInt(11)>0);
        course.setGoalAlert(cursor.getInt(12)>0);
        course.setGoalAlertP(cursor.getInt(13)>0);
        return course;
    }
    private Course cursorToCourseNo(Cursor cursor) {
        Course course = new Course();
        course.setCourseId(cursor.getInt(0));
        course.setName(cursor.getString(1));
        course.setTitle(cursor.getString(2));
        course.setStartDate(cursor.getString(3));
        course.setEndDate(cursor.getString(4));
        course.setMentor(cursor.getInt(5));
        course.setStatus(cursor.getString(6));
        course.setNotes(cursor.getString(7));
        course.setStartAlert(cursor.getInt(8)>0);
        course.setEndAlert(cursor.getInt(9)>0);
        return course;
    }
    public void saveEditedCourse(String name, String title, String start, String end, long mentors, String status, String notes, long passess, long oassess, String ogoal, String pgoal, long startalert, long endalert, long goalalert, long goalalertp) {
        ContentValues cv = new ContentValues();
        cv.put(DBHelper.COLUMN_NAME, name);
        cv.put(DBHelper.COLUMN_TITLE, title);
        cv.put(DBHelper.COLUMN_START, start);
        cv.put(DBHelper.COLUMN_END, end);
        cv.put(DBHelper.COLUMN_MENTOR, mentors);
        cv.put(DBHelper.COLUMN_STATUS, status);
        cv.put(DBHelper.COLUMN_NOTES, notes);
        cv.put(DBHelper.COLUMN_PASSESS, passess);
        cv.put(DBHelper.COLUMN_OASSESS, oassess);
        cv.put(DBHelper.COLUMN_OGOAL, ogoal);
        cv.put(DBHelper.COLUMN_PGOAL, pgoal);
        cv.put(DBHelper.COLUMN_START_ALERT, startalert);
        cv.put(DBHelper.COLUMN_END_ALERT, endalert);
        cv.put(DBHelper.COLUMN_GOAL_ALERT, goalalert);
        cv.put(DBHelper.COLUMN_GOAL_ALERTP, goalalertp);
        database.update(DBHelper.TABLE_COURSES, cv, DBHelper.COLUMN_ID + " = " + CourseEditActivity.course.getCourseId(),null  );

    }
}
