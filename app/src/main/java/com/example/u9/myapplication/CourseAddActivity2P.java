package com.example.u9.myapplication;

import android.content.DialogInterface;
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
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;

import java.util.List;

public class CourseAddActivity2P extends AppCompatActivity {

    public ListView assessmentList;
    private AssessmentsDataSource assource;

    public static String assGoalDateString;
    public static Assessment selectedAssessment;

    public static Boolean goalAlert;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.course_edit_activity2);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbarEditCourse2);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);


        assource = new AssessmentsDataSource(this);
        assource.open();
        assessmentList = (ListView) findViewById(R.id.assessmentListEditCourse);
        List<String> clist = assource.getAllAssessmentsString();
        final ArrayAdapter courseAdapter = new ArrayAdapter<String>
                (this, android.R.layout.simple_list_item_single_choice, clist);
        assessmentList.setAdapter(courseAdapter);
        assessmentList.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        assessmentList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                selectedAssessment = assource.getItem(position);
                Log.d("FSDFSDF", String.valueOf(selectedAssessment.getAssessmentId()));
            }});

        goalAlert = false;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.edit_course_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {

            case R.id.editCourseSave:
                if(selectedAssessment == null){
                    new AlertDialog.Builder(this)
                            .setTitle("Course Required")
                            .setMessage("Please select an Assessment from the List. If there are no Assessments in the list, add an Assessment first")
                            .setIcon(android.R.drawable.ic_dialog_alert)
                            .setPositiveButton("OK", new DialogInterface.OnClickListener(){
                                public void onClick(DialogInterface dialog, int whichButton) {
                                    return;}}).show();}

                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

}
