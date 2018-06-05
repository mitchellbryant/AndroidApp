package com.example.u9.myapplication;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {
    private CoursesDataSource datasource;

    Calendar c = Calendar.getInstance();
    SimpleDateFormat df = new SimpleDateFormat("M-d-yyyy");
    String formattedDate = df.format(c.getTime());

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        datasource = new CoursesDataSource(this);
        datasource.open();
        checkStartEnd();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement


        return super.onOptionsItemSelected(item);
    }

    public void buttonTerm(View v) {
        Button button=(Button) v;
        startActivity(new Intent(getApplicationContext(), TermActivity.class));
    }
    public void buttonCourse(View v) {
        Button button=(Button) v;
        startActivity(new Intent(getApplicationContext(), CourseActivity.class));
    }
    public void buttonMentor(View v) {
        Button button=(Button) v;
        startActivity(new Intent(getApplicationContext(), AssessmentActivity.class));
    }
    public void checkStartEnd() {
        int test = datasource.getAllCourses().size();

        for (int i =0; i < test; i++) {
         Course cour = datasource.getItem(i);
         Log.d("hello", formattedDate);  // can be removed later
         if (cour.getStartAlert()){if (cour.getStartDate().contentEquals(formattedDate)){new AlertDialog.Builder(this).setTitle("Start Date Alert").setMessage("Your course " + cour.getName()+ " starts today!").setIcon(android.R.drawable.ic_dialog_alert).setPositiveButton("OK", new DialogInterface.OnClickListener(){
                     public void onClick(DialogInterface dialog, int whichButton) {return;}}).show();}}
         if (cour.getEndAlert()){if (cour.getEndDate().contentEquals(formattedDate)){new AlertDialog.Builder(this).setTitle("End Date Alert").setMessage("Your course " + cour.getName()+ " ends today!").setIcon(android.R.drawable.ic_dialog_alert).setPositiveButton("OK", new DialogInterface.OnClickListener(){
             public void onClick(DialogInterface dialog, int whichButton) {return;}}).show();}}
         if (cour.getGoalAlert()){if (cour.getoGoalDate().contentEquals(formattedDate)){new AlertDialog.Builder(this).setTitle("Goal Alert").setMessage("Have you completed the Objective Assessment pertaining to " + cour.getName()+ " ?").setIcon(android.R.drawable.ic_dialog_alert).setPositiveButton("OK", new DialogInterface.OnClickListener(){
             public void onClick(DialogInterface dialog, int whichButton) {return;}}).show();}}
         if (cour.getGoalAlertP()){if (cour.getpGoalDate().contentEquals(formattedDate)){new AlertDialog.Builder(this).setTitle("Goal Alert").setMessage("Have you completed the Performance Assessment pertaining to " + cour.getName()+ " ?").setIcon(android.R.drawable.ic_dialog_alert).setPositiveButton("OK", new DialogInterface.OnClickListener(){
             public void onClick(DialogInterface dialog, int whichButton) {return;}}).show();}}
}

    }
}
