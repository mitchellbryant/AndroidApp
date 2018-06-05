package com.example.u9.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class TermAddActivity extends TermActivity {


    private TermsDataSource datasource;
    public EditText termNameText;
    public EditText startDateText;
    public EditText endDateText;
    public Button saveBtn;
    public Button cancelBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.term_add_activity);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar3);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        termNameText = (EditText) findViewById(R.id.courseNameText);
        startDateText = (EditText) findViewById(R.id.courseStartText);
        endDateText = (EditText) findViewById(R.id.courseEndText);


    }

    public void addTermBtn(View v) {

        datasource = new TermsDataSource(this);
        datasource.open();
        int termCount = datasource.getAllTerms().size();
        if (termCount > 0) {
            Term termCount2 = datasource.getAllTerms().get(termCount - 1);
            int termCount3 = (int) termCount2.getTermNo() + 1;
            long termCount4 = termCount3;
            datasource.createTerm(termCount4, termNameText.getText().toString(), startDateText.getText().toString(), endDateText.getText().toString(), "");
        }
        else {
            datasource.createTerm(1, termNameText.getText().toString(), startDateText.getText().toString(), endDateText.getText().toString(), "");
        }
        startActivity(new Intent(TermAddActivity.this, TermActivity.class));
    }

    public void cancelAddTermBtn(View v) {
        startActivity(new Intent(TermAddActivity.this, TermActivity.class));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
}