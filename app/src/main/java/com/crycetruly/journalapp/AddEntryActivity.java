package com.crycetruly.journalapp;

import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

public class AddEntryActivity extends AppCompatActivity {

    private AppCompatEditText title,feeling,grateful;
    private AppCompatEditText done;
    private Button add;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_entry);
        init();




        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String titletext=title.getText().toString();
                String gratefultext=grateful.getText().toString();
                String feelingtext=feeling.getText().toString();
                String donetext=done.getText().toString();

                if(TextUtils.isEmpty(titletext)||TextUtils.isEmpty(gratefultext)|| TextUtils.isEmpty(feelingtext)
                        ||TextUtils.isEmpty(donetext)){
                    Toast.makeText(AddEntryActivity.this, "Fill all fields", Toast.LENGTH_SHORT).show();

                    return;
                }
                Entry c=new Entry(titletext,gratefultext,feelingtext,donetext);
                DBHandler dbHandler=new DBHandler(getBaseContext());

                if (dbHandler.addNew(c)){
                    Toast.makeText(AddEntryActivity.this, "Added A New entry", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(getBaseContext(),MainActivity.class));
                };


            }
        });
    }

    private void init() {
        title=findViewById(R.id.n);
        feeling=findViewById(R.id.e);
        grateful=findViewById(R.id.no);
        done=findViewById(R.id.o);
        add=findViewById(R.id.add);

        getSupportActionBar().setTitle("Add a new Entry");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        finish();
        return super.onOptionsItemSelected(item);
    }
}
