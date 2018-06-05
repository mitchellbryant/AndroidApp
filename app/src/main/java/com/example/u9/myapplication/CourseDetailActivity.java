package com.example.u9.myapplication;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

public class CourseDetailActivity extends TermActivity {

    public ListView courseList;
    private CoursesDataSource datasource;
    private MentorsDataSource mentorssource;
    public TextView courseName;
    public TextView courseTitle;
    public TextView startDate;
    public TextView endDate;
    public TextView courseMentor;
    public TextView courseMentorEmail;
    public TextView courseMentorPhone;
    public TextView status;
    public Button editCourseBtn;
    public static Course course;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.course_detail_activity);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_course_detail);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        courseName = (TextView) findViewById(R.id.courseNameText);
        courseTitle = (TextView) findViewById(R.id.courseTitleText);
        startDate = (TextView) findViewById(R.id.courseStartText);
        endDate = (TextView) findViewById(R.id.courseEndText);
        courseMentor = (TextView) findViewById(R.id.courseMentorText);
        courseMentorEmail = (TextView) findViewById(R.id.courseMentorEmail);
        courseMentorPhone = (TextView) findViewById(R.id.courseMentorPhone);
        status = (TextView) findViewById(R.id.statusCourse);
        courseName.setText(CourseActivity.course.getName());
        courseTitle.setText(CourseActivity.course.getTitle());
        startDate.setText("Start Date: " + CourseActivity.course.getStartDate());
        endDate.setText("Anticipated End Date: " + CourseActivity.course.getEndDate());

        mentorssource = new MentorsDataSource(this);
        mentorssource.open();
        Mentor mentor = mentorssource.getMentorById(CourseActivity.course.getMentor());
        courseMentor.setText(mentor.getMentorName());
        courseMentorPhone.setText(mentor.getPhone());
        courseMentorEmail.setText(mentor.getEmail());
        status.setText(CourseActivity.course.getStatus());


    }

    public void onClickDelBtn(View v) {
        datasource = new CoursesDataSource(this);
        datasource.open();
        new AlertDialog.Builder(this)
                .setTitle("Confirm Delete")
                .setMessage("Are you sure you want to Delete this Course?")
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener(){
            public void onClick(DialogInterface dialog, int whichButton) {
                datasource.deleteCourse(datasource.getAllCourses().remove(CourseActivity.courseInt ));
                startActivity(new Intent(CourseDetailActivity.this, CourseActivity.class));
            }}).setNegativeButton(android.R.string.no, null).show();

//        datasource = new TermsDataSource(this);
//        datasource.open();
//        datasource.deleteTerm(datasource.getAllTerms().remove(TermActivity.termInt ));
//        startActivity(new Intent(TermDetailActivity.this, TermActivity.class));

    }
    public void onClickEditBtn(View v){
        startActivity(new Intent(CourseDetailActivity.this, CourseEditActivity.class));
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_course_detail, menu);
        return true;

    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.notes_item:
                Intent intent = new Intent(this, CourseNotesActivity.class);
                this.startActivity(intent);
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
