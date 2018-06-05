package com.example.u9.myapplication;

import android.app.ListActivity;
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
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.view.MenuItem.OnMenuItemClickListener;

import java.util.ArrayList;
import java.util.List;

public class TermActivity extends AppCompatActivity {

    public ListView list;
    private TermsDataSource datasource;
    public static Term term;
    public static int termInt;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.term_activity);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar5);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);



        datasource = new TermsDataSource(this);
        datasource.open();

      //  datasource.createTerm(1, "Term 1", "11-10-2016", "11-11-2016");
    //  datasource.createTerm(2, "Term 2", "11-10-2017", "11-11-2017");
    // datasource.createTerm(3, "Term 3", "11-10-2018", "11-11-2018");
//        datasource.deleteTerm(datasource.getAllTerms().get(0));


        list = (ListView) findViewById(R.id.list);

        List<String> termList = datasource.getAllTermsString();
//        Term term = new Term();
//        term.setTermNo(1);
//        term.setTermName("Term 1");
//        term.setTermStartDate("10-25-2017");
//        term.setTermEndDate("05-25-2018");
//        termList.add(term.getTermName() + blankSpace + term.getTermStartDate() + " - " + term.getTermEndDate() );
//        termList.add(term.getTermName());
        // Create the ArrayAdapter using our "courses" string array.
        // The "android.R.layout.simple_list_item_1" defines the button layout.
        final ArrayAdapter termAdapter = new ArrayAdapter<String>
                (this, android.R.layout.simple_list_item_1, termList);

        // Pass our adapter to the ListView (we extended the ListActivity class)
        list.setAdapter(termAdapter);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                term = datasource.getItem(position);
                Log.d("gg", String.valueOf(term.getCourses()));
                Intent intent = new Intent(TermActivity.this, TermDetailActivity.class);
                startActivity(intent);
                termInt = position;
                String test = String.valueOf(termInt);


//                int termCount = datasource.getAllTerms().size();
//                Term termCount2 = datasource.getAllTerms().get(termCount - 1);
//                long termCount3 = termCount2.getTermNo();
//                Log.d("umm", String.valueOf(termCount));
//                Log.d("umm2", String.valueOf(termCount2));
//                Log.d("umm3", String.valueOf(termCount3));
            }


        });

        //////////////////////////////////////////////////////////////////////////////////////




//        String[] courses = new String[] {"C169", "C188", "C196",
//                "C482", "EDV1", "TXC1", "TXP1", "TYC1", "TYP1"};
//
//        // Create the ArrayAdapter using our "courses" string array.
//        // The "android.R.layout.simple_list_item_1" defines the button layout.
//        ListAdapter termAdapter = new ArrayAdapter<String>
//                (this, android.R.layout.simple_list_item_1, courses);
//
//        // Pass our adapter to the ListView (we extended the ListActivity class)
//        list.setAdapter(termAdapter);



//        List<Term> values = new ArrayList<Term>();
//
//        // use the SimpleCursorAdapter to show the
//        // elements in a ListView
//        ArrayAdapter<Term> adapter = new ArrayAdapter<Term>(this,
//                android.R.layout.simple_list_item_1, values);





    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_test, menu);
        return true;

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.action_plus:
                Intent intent = new Intent(this, TermAddActivity.class);
                this.startActivity(intent);
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }



}
