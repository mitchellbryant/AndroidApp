package com.example.u9.myapplication;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ListView;

import java.util.List;

public class CourseAddActivity extends TermActivity {


    private CoursesDataSource datasource;
    public EditText courseNameText;
    public EditText courseTitleText;
    public EditText startDateText;
    public EditText endDateText;
    public EditText courseMentorText;
    public CheckBox pAssess;
    public CheckBox oAssess;
    private MentorsDataSource msource;
    public ListView mentorList;
    public Mentor selectedMentor;
    private long oSelectedAssessentId;
    private long pSelectedAssessentId;
    private Assessment selectedAssessment;
    private Assessment selectedOAssessment;
    private Assessment selectedPAssessment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.course_add_activity);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbarAddCourse);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        courseNameText = (EditText) findViewById(R.id.courseNameText);
        courseTitleText = (EditText) findViewById(R.id.courseTitleText);
        startDateText = (EditText) findViewById(R.id.courseStartText);
        endDateText = (EditText) findViewById(R.id.courseEndText);
        pAssess = (CheckBox) findViewById(R.id.pAssess);
        oAssess = (CheckBox) findViewById(R.id.assessGoalAlert);
        oAssess.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(oAssess.isChecked()){
                    startActivity(new Intent(CourseAddActivity.this, CourseAddActivity2O.class));
                }}
        });
        pAssess.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(pAssess.isChecked()){
                    startActivity(new Intent(CourseAddActivity.this, CourseAddActivity2P.class));
                }}
        });
        msource = new MentorsDataSource(this);
        msource.open();
        mentorList = (ListView) findViewById(R.id.courseAddMentors);
        List<String> clist = msource.getAllMentorsString();
        final ArrayAdapter courseAdapter = new ArrayAdapter<String>
                (this, android.R.layout.simple_list_item_single_choice, clist);
        mentorList.setAdapter(courseAdapter);
        mentorList.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        mentorList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                selectedMentor = msource.getItem(position);
                Log.d("FSDFSDF", String.valueOf(selectedMentor.getMentorId()));
            }});

    }

    public void addCourseBtn() {
        selectedAssessment = CourseEditActivity2.selectedAssessment;
        selectedOAssessment = CourseAddActivity2O.selectedAssessment;
        selectedPAssessment = CourseAddActivity2P.selectedAssessment;

        datasource = new CoursesDataSource(this);
        datasource.open();
        int courseCount = datasource.getAllCourses().size();
        if(selectedMentor == null) {
            new AlertDialog.Builder(this)
                    .setTitle("Mentor Required")
                    .setMessage("Please select a Mentor from the List.")
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int whichButton) {
                            return;
                        }
                    }).show();
            return;
        }
        if (courseCount > 0) {
            Course courseCount2 = datasource.getAllCourses().get(courseCount - 1);
            int courseCount3 = (int) courseCount2.getCourseId() + 1;
            long courseCount4 = courseCount3;
            if (oAssess.isChecked() && pAssess.isChecked()) {
                datasource.createCourse(courseCount4, courseNameText.getText().toString(), courseTitleText.getText().toString(), startDateText.getText().toString(), endDateText.getText().toString(), selectedMentor.getMentorId(), "Plan to take", "Notes", selectedPAssessment.getAssessmentId(), selectedOAssessment.getAssessmentId(), CourseAddActivity2O.assGoalDateString, CourseAddActivity2P.assGoalDateString, 0,0,0, 0);
            } else if (oAssess.isChecked() && !pAssess.isChecked()) {
                datasource.createCourseO(courseCount4, courseNameText.getText().toString(), courseTitleText.getText().toString(), startDateText.getText().toString(), endDateText.getText().toString(), selectedMentor.getMentorId(), "Plan to take", "Notes", selectedOAssessment.getAssessmentId(), CourseAddActivity2O.assGoalDateString, 0,0,0, 0);
            } else if (pAssess.isChecked() && !oAssess.isChecked()) {
                datasource.createCourseP(courseCount4, courseNameText.getText().toString(), courseTitleText.getText().toString(), startDateText.getText().toString(), endDateText.getText().toString(), selectedMentor.getMentorId(), "Plan to take", "Notes", selectedPAssessment.getAssessmentId(), CourseAddActivity2P.assGoalDateString, 0,0,0, 0);
            } else if (!oAssess.isChecked() && !pAssess.isChecked() && !(selectedMentor == null)) {
                datasource.createCourseNoAssessments(courseCount4, courseNameText.getText().toString(), courseTitleText.getText().toString(), startDateText.getText().toString(), endDateText.getText().toString(), selectedMentor.getMentorId(), "Plan to take", "Notes", 0, 0);
            }
        }else {
            datasource.createCourse(1, courseNameText.getText().toString(), courseTitleText.getText().toString(), startDateText.getText().toString(), endDateText.getText().toString(), selectedMentor.getMentorId(), "Plan to take", "Notes", 0, 0, "", "", 0,0,0, 0);
        }
        startActivity(new Intent(CourseAddActivity.this, CourseActivity.class));
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.asdf, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {

            case R.id.editCourseSave:
                addCourseBtn();
                return true;
            case R.id.editCourseCancel:
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
