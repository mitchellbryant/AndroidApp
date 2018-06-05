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
import android.widget.Button;
import android.widget.CheckedTextView;
import android.widget.EditText;
import android.widget.ListView;

import java.util.List;

public class TermEditActivity extends TermActivity {


    private TermsDataSource datasource;
    public EditText editTermName;
    public EditText editStart;
    public EditText editEnd;
    public Course course;
    public ListView courseList;
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
        setContentView(R.layout.term_edit_activity);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_term_edit);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        editTermName = (EditText) findViewById(R.id.editTermName);
        editStart = (EditText) findViewById(R.id.editTermStart);
        editEnd = (EditText) findViewById(R.id.editTermEnd);

        datasource = new TermsDataSource(this);
        datasource.open();
        Term term = datasource.getAllTerms().get(TermActivity.termInt);
        editTermName.setText(term.getTermName());
        editStart.setText(term.getTermStartDate());
        editEnd.setText(term.getTermEndDate());


        coursesSource.open();
        courseList = (ListView) findViewById(R.id.courseListEditTerm);

        dlist = coursesSource.getAllCoursesString();
        Log.d("asdf", String.valueOf(dlist.size()));
        test = new String[dlist.size()];
        ArrayAdapter courseAdapter = new ArrayAdapter<String>
                (this, android.R.layout.simple_list_item_multiple_choice, dlist);

        courseList.setAdapter(courseAdapter);

        courseList.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
     //   Log.d("asdfasdf", String.valueOf(coursesSource.getItem(0).getCourseId()));
     //   Log.d("asdf", TermActivity.term.getCourses());
       // courseList.setItemChecked(1, true);

        if (!TermActivity.term.getCourses().matches("")) {
         //   String[] s = TermActivity.term.getCourses().split(",");
         //  numbers = new int[s.length];
            for (int curr = 0; curr < dlist.size(); curr++) {
             //   numbers[curr] = Integer.parseInt(s[curr]);
                if (TermDetailActivity.coursesList.contains(courseList.getItemAtPosition(curr))) {
                    courseList.setItemChecked(curr, true);
                }}}
 }
    public void showMe() {
        datasource = new TermsDataSource(this);
        datasource.open();
        SparseBooleanArray checked = courseList.getCheckedItemPositions();
        z = 0;
        for (int curr = 0; curr < dlist.size(); curr++) {
            if (checked.get(curr)) {
                test[z] = String.valueOf(coursesSource.getItem(curr).getCourseId());
                Log.d("yes", test.toString());
                z++;
            }
        }
        for (int i = 0; i < z ; i++){
            if(test[i] != null ) {
                test2 = test2 + "," + test[i] ;
            }
        }
        if (z == 0){
            datasource.saveEditedTerm(editTermName.getText().toString(), editStart.getText().toString(), editEnd.getText().toString(), "");
            startActivity(new Intent(TermEditActivity.this, TermActivity.class));
        }
        else {
            datasource.saveEditedTerm(editTermName.getText().toString(), editStart.getText().toString(), editEnd.getText().toString(), test2.substring(1, test2.length()));
            startActivity(new Intent(TermEditActivity.this, TermActivity.class));
        }
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
                showMe();
                return true;

            case R.id.editTermDel:
                new AlertDialog.Builder(this)
                            .setTitle("Confirm Delete")
                            .setMessage("Are you sure you want to Delete this Term?")
                            .setIcon(android.R.drawable.ic_dialog_alert)
                            .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener(){
                                public void onClick(DialogInterface dialog, int whichButton) {
                                    datasource.deleteTerm(datasource.getAllTerms().remove(TermActivity.termInt ));
                                    startActivity(new Intent(TermEditActivity.this, TermActivity.class));
                                }}).setNegativeButton(android.R.string.no, null).show();
                    return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }


}
