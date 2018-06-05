package com.example.u9.myapplication;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import java.util.List;

public class AssessmentAddActivity extends AppCompatActivity {

    public ListView assessmentList;
    public ListView courseList;
    private CoursesDataSource datasource;
    private AssessmentsDataSource assource;
    public static Assessment assessment;
    public static int assessmentInt;
    public Course selectedCourse;
    public EditText assessmentNameText;
    public EditText assessmentDescriptionText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.assessment_add_activity);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbarAddAssessment);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        assessmentNameText = (EditText) findViewById(R.id.assessmentNameText);
        assessmentDescriptionText = (EditText) findViewById(R.id.assessmentDescriptionText);


        }
    public void saveAss(){
        AssessmentsDataSource assSource = new AssessmentsDataSource(this);
        assSource.open();
        int assCount = assSource.getAllAssessments().size();

        if (assCount > 0){
            Assessment assessmentCount2 = assSource.getAllAssessments().get(assCount - 1);
            int assCount3 = (int) assessmentCount2.getAssessmentId() + 1;
            long assCount4 = assCount3;
            assSource.createAssessment(assCount4, 1, assessmentNameText.getText().toString(), assessmentDescriptionText.getText().toString());
            startActivity(new Intent(AssessmentAddActivity.this, AssessmentActivity.class));
        }
        else {
            assSource.createAssessment(0, 1, assessmentNameText.getText().toString(), assessmentDescriptionText.getText().toString());
            startActivity(new Intent(AssessmentAddActivity.this, AssessmentActivity.class));
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.assessment_add_save_menu, menu);
        return true;

    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        // Handle item selection
        switch (item.getItemId()) {
            case R.id.assAddSave:
                saveAss();
                return true;
            case R.id.assAddSave2:
                saveAss();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }


    }
