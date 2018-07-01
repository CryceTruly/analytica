package com.crycetruly.journalapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;


public class DetailsActivity extends AppCompatActivity {
    int id;
    private static final String TAG = "DetailsActivity";
    private TextView title,grateful,done,feel;
Entry entry;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        id = getIntent().getIntExtra("id", 1);
init();


        DBHandler handler = new DBHandler(getBaseContext());
         entry = handler.getEntry(id);

        title.setText(entry.getTitle());
        grateful.setText(entry.getGrateful());
        done.setText(entry.getDone());
        feel.setText(entry.getFeel());

        getSupportActionBar().setTitle("Entry Details");
getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Log.d(TAG, "onCreate: " + entry.toString());


    }

    private void init() {
        title=findViewById(R.id.titleview);
        grateful=findViewById(R.id.grateful);
        done=findViewById(R.id.done);
        feel=findViewById(R.id.feel);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.detail, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.edit:
                Intent intent = new Intent(getBaseContext(), EditActivity.class);
                intent.putExtra("id", id);
                intent.putExtra("title",entry.getTitle());
                intent.putExtra("grateful",entry.getGrateful());
                intent.putExtra("feel",entry.getFeel());
                intent.putExtra("done",entry.getDone());

                startActivity(intent);

                break;
            case R.id.delete:
                deleteContact(id);
                break;

            case android.R.id.home:
               finish();
                break;


        }
        return super.onOptionsItemSelected(item);
    }

    private void deleteContact(int id) {
        Log.d(TAG, "deleteentry: ");
        DBHandler handler = new DBHandler(getBaseContext());
        handler.deleteEntry(id);

        Toast.makeText(this, "Mark as done success", Toast.LENGTH_SHORT).show();
        startActivity(new Intent(getBaseContext(), MainActivity.class));
    }
}
