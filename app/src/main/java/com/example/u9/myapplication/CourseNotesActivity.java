package com.example.u9.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class CourseNotesActivity extends AppCompatActivity {

    public EditText editCourseNotes;
    private CoursesDataSource datasource;
    private static Course course;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.course_notes_activity);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_course_notes);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        datasource = new CoursesDataSource(this);
        datasource.open();
        course = datasource.getItem(CourseActivity.coursePosition);
        editCourseNotes = (EditText) findViewById(R.id.notesText);
        editCourseNotes.setText(course.getNotes());



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.edit_notes_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.editNotesSave:
                datasource.open();
                datasource.saveEditedNotes(editCourseNotes.getText().toString());

                finish();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }


}
