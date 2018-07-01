package com.crycetruly.journalapp;

import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.firebase.ui.auth.AuthUI;
import com.firebase.ui.auth.ErrorCodes;
import com.firebase.ui.auth.IdpResponse;
import com.google.firebase.auth.FirebaseAuth;

import java.util.Arrays;
import java.util.Collections;

public class LoginActivity extends AppCompatActivity {
    private FirebaseAuth firebaseAuth;
    private static final String TAG = "MainActivity";
    private static final int RC_SIGN_IN = 123;
    private RelativeLayout main;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        main=findViewById(R.id.main);
        firebaseAuth = FirebaseAuth.getInstance();
        if (firebaseAuth.getCurrentUser() != null) {
            // already signed in
            Toast.makeText(this, "Authenticated with: "+firebaseAuth.getCurrentUser().getEmail(), Toast.LENGTH_SHORT).show();
            goToMain();
        } else {

// already signed in

            Snackbar.make(main,"You need to login with google first",Snackbar.LENGTH_INDEFINITE).setAction("ok", new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    startActivityForResult(
                            AuthUI.getInstance()
                                    .createSignInIntentBuilder()
                                    .setAvailableProviders(Collections.singletonList(
                                            new AuthUI.IdpConfig.GoogleBuilder().build())
                                    )
                                    .build(),
                            RC_SIGN_IN);                }
            }).show();
        }



    }

    private void goToMain() {
        Log.d(TAG, "goToMain: ");
        startActivity(new Intent(getBaseContext(),MainActivity.class));
        finish();
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // RC_SIGN_IN is the request code you passed into startActivityForResult(...) when starting the sign in flow.
        if (requestCode == RC_SIGN_IN) {
            IdpResponse response = IdpResponse.fromResultIntent(data);

            // Successfully signed in
            if (resultCode == RESULT_OK) {
               goToMain();
            } else {
                // Sign in failed
                if (response == null) {
                    // User pressed back button
                    Snackbar.make(main,"Cannot Login at this time",Snackbar.LENGTH_INDEFINITE).setAction("ok", new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                           finish();
                        }
                    }).show();
                    return;
                }

                if (response.getError().getErrorCode() == ErrorCodes.NO_NETWORK) {
                    Snackbar.make(main,"Cannot Login at this time No network",Snackbar.LENGTH_INDEFINITE).setAction("ok", new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            finish();
                        }
                    }).show();
                    return;
                }


                Log.e(TAG, "Sign-in error: ", response.getError());
            }
        }
    }

    }
