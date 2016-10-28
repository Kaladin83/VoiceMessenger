package com.example.maratbe.mytest1;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Spinner;

/**
 * Created by MARATBE on 10/16/2016.
 */

public class AdvancedActivity extends Activity {
    Intent intent, myIntent;
    Spinner activitySpiner;
    AutoModeActivity auto;
    ArrayAdapter<String> adapter;
    String[] activityStrings = new String[] {"Record messages","Change images"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.advanced_activity);

        activitySpiner = (Spinner) findViewById(R.id.activitySpinner);
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, activityStrings);
        activitySpiner.setAdapter(adapter);
        activitySpiner.setSelection(0);
        activitySpiner.setOnItemSelectedListener(null);
        Button button = (Button) findViewById(R.id.imageButton);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.advanced_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.general:
                myIntent = new Intent(this, MainActivity.class);
                myIntent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                startActivity(myIntent);
            case R.id.autoMode:
                myIntent = new Intent(this, AutoModeActivity.class);
                myIntent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                startActivity(myIntent);
                return true;
            case R.id.mapingImages:
                myIntent = new Intent(this, MapImageActivity.class);
                myIntent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                startActivity(myIntent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
