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

public class CourseActivity extends AppCompatActivity {

    public ListView courseList;
    private CoursesDataSource datasource;
    public static Course course;
    public static int courseInt;
    public static int coursePosition;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.course_activity);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar3);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        datasource = new CoursesDataSource(this);
        datasource.open();
       // datasource.deleteCourse(datasource.getAllCourses().get(1));
       // datasource.createCourse(2, "Course 1", "Programming", "11-11-2017", "11-12-2017",  "jim", false, true, true);


        courseList = (ListView) findViewById(R.id.clist);

        List<String> clist = datasource.getAllCoursesString();
        final ArrayAdapter courseAdapter = new ArrayAdapter<String>
                (this, android.R.layout.simple_list_item_1, clist);
        courseList.setAdapter(courseAdapter);
        courseList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                coursePosition = position;
                course = datasource.getItem(position);
                Log.d("t", String.valueOf(course.getCourseId()));
                CourseEditActivity.course = course;
                Intent intent = new Intent(CourseActivity.this, CourseDetailActivity.class);
                startActivity(intent);
                courseInt = position;
       //         String test = String.valueOf(courseInt);
//                int courseCount = datasource.getAllcourses().size();
//                course courseCount2 = datasource.getAllcourses().get(courseCount - 1);
//                long courseCount3 = courseCount2.getcourseNo();
//                Log.d("umm", String.valueOf(courseCount));
//                Log.d("umm2", String.valueOf(courseCount2));
                //Log.d("umm3", course.getMentor());
            }


        });
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_add_course, menu);
        return true;

    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Log.d("show", String.valueOf(courseList.getId() ));
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.add_term_action:
                Intent intent = new Intent(this, CourseAddActivity.class);
                this.startActivity(intent);
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }




}
