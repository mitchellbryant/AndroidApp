package com.example.u9.myapplication;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.util.SparseBooleanArray;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import java.util.List;

public class AssessmentEditActivity extends AssessmentActivity {


    private AssessmentsDataSource datasource;
    public EditText editAssessmentName;
    public EditText editAssessmentDescription;
    public EditText editEnd;
    public Course course;
    public ListView courseList;
    public Course selectedCourse;
    public List<String> dlist;
    public String[] test = new String[]{};
    public String test2 = new String();
    int z = 0;

    final CoursesDataSource coursesSource = new CoursesDataSource(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.assessment_edit_activity);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_assessment_edit);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        editAssessmentName = (EditText) findViewById(R.id.editAssessmentName);
        editAssessmentDescription = (EditText) findViewById(R.id.editAssessmentDescription);

        datasource = new AssessmentsDataSource(this);
        datasource.open();
        Assessment assessment = datasource.getAllAssessments().get(AssessmentActivity.assessmentInt);
        editAssessmentName.setText(assessment.getAssessmentName());
        editAssessmentDescription.setText(assessment.getAssessmentDescription());

 }
    public void showMe() {
        datasource = new AssessmentsDataSource(this);
        datasource.open();
        datasource.saveEditedAssessment(editAssessmentName.getText().toString(), editAssessmentDescription.getText().toString(),1);
            startActivity(new Intent(AssessmentEditActivity.this, AssessmentActivity.class));
        }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.assessment_edit_menu, menu);
        return true;

    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        // Handle item selection
        switch (item.getItemId()) {

            case R.id.editAssessmentSave2:
                showMe();
                return true;

            case R.id.editAssessmentDel:
                new AlertDialog.Builder(this)
                            .setTitle("Confirm Delete")
                            .setMessage("Are you sure you want to Delete this Assessment?")
                            .setIcon(android.R.drawable.ic_dialog_alert)
                            .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener(){
                                public void onClick(DialogInterface dialog, int whichButton) {
                                    datasource.deleteAssessment(datasource.getAllAssessments().remove(AssessmentActivity.assessmentInt ));
                                    startActivity(new Intent(AssessmentEditActivity.this, AssessmentActivity.class));
                                }}).setNegativeButton(android.R.string.no, null).show();
                    return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }


}
