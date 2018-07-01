package com.crycetruly.journalapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.AppCompatSpinner;
import android.text.TextUtils;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Toast;


public class EditActivity extends AppCompatActivity {
    int id;
    private AppCompatEditText title, grateful,feeling, done;
    private AppCompatEditText organistion;
    private AppCompatSpinner relationship;
    private Button add;
    private static final String TAG = "EditActivity";
    String selected;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        init();



        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String titletext = title.getText().toString();
                String gratefultext = grateful.getText().toString();
                String donetext = done.getText().toString();
                String feelingtext = feeling.getText().toString();

                if (TextUtils.isEmpty(titletext) || TextUtils.isEmpty(donetext) || TextUtils.isEmpty(feelingtext)
                        || TextUtils.isEmpty(gratefultext)) {
                    Snackbar.make(relationship, "Fill all fields", Snackbar.LENGTH_SHORT).show();
                    return;
                }
                Entry c = new Entry(titletext, gratefultext, feelingtext, donetext);
                DBHandler dbHandler = new DBHandler(getBaseContext());

                if (dbHandler.updateEntry(c) != -1) {
                    Toast.makeText(getBaseContext(), "Updated success", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(getBaseContext(), MainActivity.class));
                }
                ;


            }
        });
    }

    private void init() {
        title = findViewById(R.id.n);
        title.setText(getIntent().getStringExtra("title"));
        feeling = findViewById(R.id.e);
        feeling.setText(getIntent().getStringExtra("feeling"));
        grateful = findViewById(R.id.no);
        grateful.setText(getIntent().getStringExtra("grateful"));

        done = findViewById(R.id.o);
        done.setText(getIntent().getStringExtra("done"));
        add = findViewById(R.id.add);

        getSupportActionBar().setTitle("Edit an Entry");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        finish();
        return super.onOptionsItemSelected(item);
    }
}
