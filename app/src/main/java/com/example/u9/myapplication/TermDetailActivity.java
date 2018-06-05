package com.example.u9.myapplication;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

public class TermDetailActivity extends TermActivity {

    public static ListView courseList;
    private TermsDataSource datasource;
    private CoursesDataSource coursesDatasource;
    public TextView termName;
    public TextView startDate;
    public TextView endDate;
    public Button delTermBtn;
    public Button editTermBtn;
    public static Course course;
    public static List<String> coursesList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.term_detail_activity);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_term_detail);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        termName = (TextView) findViewById(R.id.courseNameText);
        startDate = (TextView) findViewById(R.id.courseStartText);
        endDate = (TextView) findViewById(R.id.courseEndText);
        termName.setText(TermActivity.term.getTermName());
        startDate.setText("Start Date: " + TermActivity.term.getTermStartDate());
        endDate.setText("End Date: " + TermActivity.term.getTermEndDate());

        datasource = new TermsDataSource(this);
        datasource.open();
        coursesDatasource = new CoursesDataSource(this);
        coursesDatasource.open();
        courseList = (ListView) findViewById(R.id.courseList);

        coursesList = datasource.getCoursesString();
        final ArrayAdapter termAdapter = new ArrayAdapter<String>
                (this, android.R.layout.simple_list_item_1, coursesList);

        courseList.setAdapter(termAdapter);
        courseList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                course = datasource.getCourses().get(position);
                CourseActivity.course = course;
                Log.d("gg", String.valueOf(course.getCourseId()));
                CourseEditActivity.course = course;
                Intent intent = new Intent(TermDetailActivity.this, CourseDetailActivity.class);
                startActivity(intent);
            }});
    }
    public void onClickEditBtn(View v){

        startActivity(new Intent(TermDetailActivity.this, TermEditActivity.class));

    }

    public void onClickDelBtn(View v) {
        datasource = new TermsDataSource(this);
        datasource.open();
        new AlertDialog.Builder(this)
                .setTitle("Confirm Delete")
                .setMessage("Are you sure you want to Delete this Term?")
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener(){
            public void onClick(DialogInterface dialog, int whichButton) {
                if(term.getCourses().isEmpty()) {
                    datasource.deleteTerm(datasource.getAllTerms().remove(TermActivity.termInt));
                    startActivity(new Intent(TermDetailActivity.this, TermActivity.class));
                }
                else {
                    AlertDialog alertDialog = new AlertDialog.Builder(TermDetailActivity.this).create();
                    alertDialog.setTitle("Alert");
                    alertDialog.setMessage("You cannot delete a Term if there is a Course assigned to it");
                    alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            });
                    alertDialog.show();
                }
            }}).setNegativeButton(android.R.string.no, null).show();

//        datasource = new TermsDataSource(this);
//        datasource.open();
//        datasource.deleteTerm(datasource.getAllTerms().remove(TermActivity.termInt ));
//        startActivity(new Intent(TermDetailActivity.this, TermActivity.class));


    }

}
