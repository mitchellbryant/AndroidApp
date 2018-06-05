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
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.util.List;

public class CourseEditActivity extends CourseActivity {


    private CoursesDataSource datasource;
    public EditText editCourseName;
    public EditText editCourseTitle;
    public EditText editCourseStart;
    public EditText editCourseEnd;
    public EditText editGoalDateO;
    public EditText editGoalDateP;
    public TextView status;
    public static Course course;
    public ListView courseList;
    public CheckBox oAssess;
    public CheckBox pAssess;
    public CheckBox startAlertCheckBox;
    public CheckBox endAlertCheckBox;
    public Mentor selectedMentor;
    private MentorsDataSource msource;
    public CheckBox assessGoalO;
    public CheckBox assessGoalP;
    private RadioGroup rGroup;
    private RadioButton radio1;
    private RadioButton radio2;
    private RadioButton radio3;
    int peAss;
    int obAss;


    public int size;
    public static int[] numbers;
    public List<String> dlist;
    public String[] test = new String[]{};
    public String test2 = new String();
    int z = 0;

    final CoursesDataSource coursesSource = new CoursesDataSource(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.course_edit_activity);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_course_edit);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        editCourseName = (EditText) findViewById(R.id.editCourseName);
        editCourseTitle = (EditText) findViewById(R.id.editCourseTitle);
        editCourseStart = (EditText) findViewById(R.id.editCourseStart);
        editCourseEnd = (EditText) findViewById(R.id.editCourseEnd);
        editGoalDateO = (EditText) findViewById(R.id.editAssessmentGoal);
        editGoalDateP = (EditText) findViewById(R.id.editAssessmentGoalP);
        assessGoalO = (CheckBox) findViewById(R.id.assessGoalAlert);
        assessGoalP = (CheckBox) findViewById(R.id.assessGoalAlertP);
        rGroup = (RadioGroup) findViewById(R.id.RGroup);
        radio1 = (RadioButton) findViewById(R.id.radioButton);
        radio2 = (RadioButton) findViewById(R.id.radioButton2);
        radio3 = (RadioButton) findViewById(R.id.radioButton3);

        if (course.getStatus().contains("In Progress")){rGroup.check(R.id.radioButton);}
        else if (course.getStatus().contains("Complete")){rGroup.check(R.id.radioButton2);}
        else if (course.getStatus().contains("Dropped")){rGroup.check(R.id.radioButton3);}

        peAss = (int)course.getpAssessment();
        obAss = (int)course.getoAssessment();

        datasource = new CoursesDataSource(this);
        datasource.open();
        msource = new MentorsDataSource(this);
        msource.open();
       // Course course = datasource.getAllCourses().get(CourseActivity.courseInt);
        editCourseName.setText(course.getName());
        editCourseTitle.setText(course.getTitle());
        editCourseStart.setText(course.getStartDate());
        editCourseEnd.setText(course.getEndDate());
        editGoalDateO.setText(course.getoGoalDate());
        editGoalDateP.setText(course.getpGoalDate());

        coursesSource.open();

        oAssess = (CheckBox) findViewById((R.id.objectiveCheck));
        pAssess = (CheckBox) findViewById((R.id.performaceCheck));
        startAlertCheckBox = (CheckBox) findViewById((R.id.startAlertCheckbox));
        endAlertCheckBox = (CheckBox) findViewById((R.id.endAlertCheckbox));
        if (course.getoAssessment() > 0){oAssess.setChecked(true);}
        if (course.getpAssessment() > 0){pAssess.setChecked(true);}
        try {if (course.getStartAlert()){startAlertCheckBox.setChecked(true);}} catch (NullPointerException exc){}
        try {if (course.getEndAlert()){endAlertCheckBox.setChecked(true);}} catch (NullPointerException exc){}
        try {if (course.getGoalAlert()){assessGoalO.setChecked(true);}} catch (NullPointerException exc){}
        try {if (course.getGoalAlertP()){assessGoalP.setChecked(true);}} catch (NullPointerException exc){}
        oAssess.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(oAssess.isChecked()){
                startActivity(new Intent(CourseEditActivity.this, CourseEditActivity2.class));
            }}
        });
        pAssess.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(pAssess.isChecked()){
                    startActivity(new Intent(CourseEditActivity.this, CourseEditActivity2P.class));
                }}
        });

 }
        public void save() {
            datasource = new CoursesDataSource(this);
            datasource.open();
            int o = 0;
            int p = 0;
            int g = 0;
            int gp= 0;
            String status = "Plan to Take";
            if(radio1.isChecked()){status = "In Progress";} else if(radio2.isChecked()){status = "Complete";} else if(radio3.isChecked()){status = "Dropped";}
            if(startAlertCheckBox.isChecked()){o = 1;}
            if(endAlertCheckBox.isChecked()){p = 1;}
            if(assessGoalO.isChecked()){g = 1;}
            if(assessGoalP.isChecked()){gp = 1;}
            if(pAssess.isChecked()){peAss = 1;}
            if(oAssess.isChecked()){obAss = 1;}
            if (!oAssess.isChecked() && !pAssess.isChecked()){datasource.saveEditedCourse(editCourseName.getText().toString(), editCourseTitle.getText().toString(), editCourseStart.getText().toString(), editCourseEnd.getText().toString(), course.getMentor(), status, course.getNotes(), 0, 0, "", "",o,p,0 ,0  );}
            try {if (oAssess.isChecked() && pAssess.isChecked()) {datasource.saveEditedCourse(editCourseName.getText().toString(), editCourseTitle.getText().toString(), editCourseStart.getText().toString(), editCourseEnd.getText().toString(), course.getMentor(), status, course.getNotes(), peAss, obAss, editGoalDateO.getText().toString(), editGoalDateP.getText().toString(),o,p,g, gp   );}}
            catch (NullPointerException exception){datasource.saveEditedCourse(editCourseName.getText().toString(), editCourseTitle.getText().toString(), editCourseStart.getText().toString(), editCourseEnd.getText().toString(), course.getMentor(), status, course.getNotes(), peAss, obAss, course.getoGoalDate(), course.getpGoalDate(),o,p,g, gp   );}
            try {if (oAssess.isChecked() && !pAssess.isChecked()) {datasource.saveEditedCourse(editCourseName.getText().toString(), editCourseTitle.getText().toString(), editCourseStart.getText().toString(), editCourseEnd.getText().toString(), course.getMentor(), status, course.getNotes(), peAss, obAss, editGoalDateO.getText().toString(), "",o,p,g, gp   );}}
            catch (NullPointerException exception){datasource.saveEditedCourse(editCourseName.getText().toString(), editCourseTitle.getText().toString(), editCourseStart.getText().toString(), editCourseEnd.getText().toString(), course.getMentor(), status, course.getNotes(), peAss, course.getoAssessment(), course.getoGoalDate(), "",o,p,g, gp   );}
            try {if (!oAssess.isChecked() && pAssess.isChecked()) {datasource.saveEditedCourse(editCourseName.getText().toString(), editCourseTitle.getText().toString(), editCourseStart.getText().toString(), editCourseEnd.getText().toString(), course.getMentor(), status, course.getNotes(), peAss, obAss, "", editGoalDateP.getText().toString(),o,p,g, gp   );}}
            catch (NullPointerException exception){datasource.saveEditedCourse(editCourseName.getText().toString(), editCourseTitle.getText().toString(), editCourseStart.getText().toString(), editCourseEnd.getText().toString(), course.getMentor(), status, course.getNotes(), course.getpAssessment(), obAss, "", course.getpGoalDate(),o,p,g, gp   );}
            startActivity(new Intent(CourseEditActivity.this, CourseActivity.class));
        }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.edit_term_menu, menu);
        return true;

    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        // Handle item selection
        switch (item.getItemId()) {

            case R.id.editTermSave2:
                save();
                return true;

            case R.id.editTermDel:
                new AlertDialog.Builder(this)
                            .setTitle("Confirm Delete")
                            .setMessage("Are you sure you want to Delete this Course?")
                            .setIcon(android.R.drawable.ic_dialog_alert)
                            .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener(){
                                public void onClick(DialogInterface dialog, int whichButton) {
                                    datasource.deleteCourse(datasource.getAllCourses().remove(CourseActivity.courseInt ));
                                    startActivity(new Intent(CourseEditActivity.this, CourseActivity.class));
                                }}).setNegativeButton(android.R.string.no, null).show();
                    return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

//    public void onCheckboxClicked(View view) {
//        // Is the view now checked?
//        boolean checked = ((CheckBox) view).isChecked();
//
//        // Check which checkbox was clicked
//        switch(view.getId()) {
//            case oAssess:
//                if (oAssess.isChecked()){
//                    oAssess.toggle();
//                }
//                else
//                {
//                    oAssess.toggle();
//                    startActivity(new Intent(CourseEditActivity.this, CourseEditActivity2.class));
//                }
//                break;
//            case R.id.performaceCheck:
//                if (checked){
//                    pAssess.toggle();
//                }
//            else
//                {
//                    pAssess.toggle();
//                    startActivity(new Intent(CourseEditActivity.this, CourseEditActivity2.class));
//                }
//                break;
//            // TODO: Veggie sandwich
//        }
//    }


}
