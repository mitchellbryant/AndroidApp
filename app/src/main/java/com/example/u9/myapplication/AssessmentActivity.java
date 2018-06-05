package com.example.u9.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.List;

public class AssessmentActivity extends AppCompatActivity {

    public ListView assessmentList;
    private AssessmentsDataSource datasource;
    public static Assessment assessment;
    public static int assessmentInt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.assessment_activity);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar4);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        datasource = new AssessmentsDataSource(this);
        datasource.open();
        assessmentList = (ListView) findViewById(R.id.alist);

        List<String> alist = datasource.getAllAssessmentsString();
        final ArrayAdapter assessmentAdapter = new ArrayAdapter<String>
                (this, android.R.layout.simple_list_item_1, alist);
        assessmentList.setAdapter(assessmentAdapter);
        assessmentList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                assessment = datasource.getItem(position);
                Log.d("t", String.valueOf(assessment.getAssessmentId()));
                Intent intent = new Intent(AssessmentActivity.this, AssessmentDetailActivity.class);
                startActivity(intent);
                assessmentInt = position;
                //         String test = String.valueOf(assessmentInt);
//                int assessmentCount = datasource.getAllassessments().size();
//                assessment assessmentCount2 = datasource.getAllassessments().get(assessmentCount - 1);
//                long assessmentCount3 = assessmentCount2.getassessmentNo();
//                Log.d("umm", String.valueOf(assessmentCount));
//                Log.d("umm2", String.valueOf(assessmentCount2));
                //Log.d("umm3", assessment.getMentor());
            }


        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_add_assessment, menu);
        return true;

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Log.d("show", String.valueOf(assessmentList.getId()));
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.add_assessment_action:
                Intent intent = new Intent(this, AssessmentAddActivity.class);
                this.startActivity(intent);
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }
}