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
import android.widget.TextView;

import java.util.List;

public class AssessmentDetailActivity extends AppCompatActivity {

    public ListView assessmentList;
    private AssessmentsDataSource datasource;
    public static Assessment assessment;
    public static int assessmentInt;
    public TextView assName;
    public TextView assCourseName;
    public TextView assDescription;
    public Course c;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.assessment_detail_activity);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_assessment_detail);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        assName = (TextView) findViewById(R.id.assessmentTitleText);

        assDescription = (TextView) findViewById(R.id.textViewDescription);
        assName.setText(AssessmentActivity.assessment.getAssessmentName());



        assDescription.setText(AssessmentActivity.assessment.getAssessmentDescription());

    }
    public void onClickDelAssessmentBtn(View v) {
        datasource = new AssessmentsDataSource(this);
        datasource.open();
        new AlertDialog.Builder(this)
                .setTitle("Confirm Delete")
                .setMessage("Are you sure you want to Delete this Assessment?")
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener(){
                    public void onClick(DialogInterface dialog, int whichButton) {
                        datasource.deleteAssessment(datasource.getAllAssessments().remove(AssessmentActivity.assessmentInt ));
                        startActivity(new Intent(AssessmentDetailActivity.this, AssessmentActivity.class));
                    }}).setNegativeButton(android.R.string.no, null).show();
    }

    public void onClickEditBtn(View v){
        startActivity(new Intent(AssessmentDetailActivity.this, AssessmentEditActivity.class));
    }
}