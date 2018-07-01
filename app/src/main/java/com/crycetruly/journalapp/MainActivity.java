package com.crycetruly.journalapp;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.firebase.ui.auth.AuthUI;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private FirebaseAuth firebaseAuth;
private RelativeLayout main;

    List<Entry> entryList=new ArrayList<>();
    RecyclerView listView;
    private static final String TAG = "MainActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();



         firebaseAuth = FirebaseAuth.getInstance();

            DBHandler handler = new DBHandler(getBaseContext());
            getSupportActionBar().setSubtitle(String.valueOf(handler.getEntriesCount() + " Items in diary"));
            entryList = handler.getEntries();
            Log.d(TAG, "onCreate: contact size " + entryList.size());

            Adapter adapter = new Adapter(entryList, getBaseContext());

            listView.setAdapter(adapter);



        }
            //listView.setAdapter(new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,names));




    public void gonext(View view) {
        startActivity(new Intent(getBaseContext(),AddEntryActivity.class));

    }

    private void init() {
        Log.d(TAG, "init: ");
        listView=findViewById(R.id.list);
        listView.setLayoutManager(new LinearLayoutManager(getBaseContext()));
        listView.setHasFixedSize(true);


        main=findViewById(R.id.main);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        AuthUI.getInstance().signOut(getBaseContext()).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                Toast.makeText(MainActivity.this, "Logged out", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(getBaseContext(),LoginActivity.class));
                finish();
            }
        });
        return super.onOptionsItemSelected(item);
    }
}
