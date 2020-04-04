package com.example.intilock;



import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.*;

public class RegisterActivity extends AppCompatActivity {

    private EditText inputusername, inputcontactno,inputmobileip,inputage,inputbg,inputdooral,inputmode,inputhead;
    private Button btnBack, btnSignUp, btnResetPassword;
    private ProgressBar progressBar;
    private FirebaseAuth auth;
    DatabaseReference rootRef, demoRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registeractivity);
        Bundle bundle = getIntent().getExtras();

        //Extract the data…
        final String lockid = bundle.getString("lockid");
        final String email = bundle.getString("email");
        final String password=bundle.getString("password");

        rootRef = FirebaseDatabase.getInstance().getReference();
        demoRef= rootRef.child(lockid);
        //Get Firebase auth instance
        auth = FirebaseAuth.getInstance();


        btnSignUp = (Button) findViewById(R.id.sign_up_button);
        btnBack = (Button) findViewById(R.id.btn_back);

        progressBar = (ProgressBar) findViewById(R.id.progressBar);

        inputusername = (EditText) findViewById(R.id.username);
        //inputlockpin = (EditText) findViewById(R.id.lockpin);
        inputcontactno = (EditText) findViewById(R.id.contactno);
        inputmobileip = (EditText) findViewById(R.id.mobileip);
        inputage = (EditText) findViewById(R.id.age);
        inputbg = (EditText) findViewById(R.id.bloodgrp);
        inputdooral = (EditText) findViewById(R.id.dooralert);
        inputmode = (EditText) findViewById(R.id.mode);
        inputhead= (EditText) findViewById(R.id.head);


        btnBack.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v)
            {
                startActivity(new Intent(RegisterActivity.this, RegisterActivity1.class));
            }
        });



        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                String username = inputusername.getText().toString().trim();
                //String lockpin = inputlockpin.getText().toString().trim();
                String contactno = inputcontactno.getText().toString().trim();
                String mobileip = inputmobileip.getText().toString().trim();
                String age = inputage.getText().toString().trim();
                String bg = inputbg.getText().toString().trim();
                String dooral = inputdooral.getText().toString().trim();
                String mode = inputmode.getText().toString().trim();
                String head = inputhead.getText().toString().trim();



                if (TextUtils.isEmpty(age)) {
                    Toast.makeText(getApplicationContext(), "Enter age!", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(head)) {
                    Toast.makeText(getApplicationContext(), "Enter whether you are a head or an user!", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(bg)) {
                    Toast.makeText(getApplicationContext(), "Enter blood group!", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(username)) {
                    Toast.makeText(getApplicationContext(), "Enter User name!", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(mode)) {
                    Toast.makeText(getApplicationContext(), "Enter mode!", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(contactno)) {
                    Toast.makeText(getApplicationContext(), "Enter Contact number!", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(mobileip)) {
                    Toast.makeText(getApplicationContext(), "Enter Mobile IP!", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(dooral)) {
                    Toast.makeText(getApplicationContext(), "Enter door alert!", Toast.LENGTH_SHORT).show();
                    return;
                }



                progressBar.setVisibility(View.VISIBLE);
                demoRef.child("Users").child(username).child("Email").setValue(email);
                demoRef.child("Users").child(username).child("Pwd").setValue(password);
                demoRef.child("Users").child(username).child("Mobileip").setValue(mobileip);
                demoRef.child("Users").child(username).child("Contactno").setValue(contactno);
                demoRef.child("Users").child(username).child("Age").setValue(age);
                demoRef.child("Users").child(username).child("Blood_grp").setValue(bg);
                demoRef.child("Users").child(username).child("Door_alert").setValue(dooral);
                demoRef.child("Mode").setValue(mode);
                demoRef.child("Users").child(username).child("Head").setValue(head);
                demoRef.child("Users").child(username).child("IP").setValue("0");
                demoRef.child("Users").child(username).child("Pin").setValue("0");
                demoRef.child("Third_party").child("flag").setValue("0");
                demoRef.child("Third_party").child("ack").setValue("0");



                Intent i = new Intent(RegisterActivity.this, MainDisplay.class);
                Bundle bundle = new Bundle();


                bundle.putString("username", username);

                bundle.putString("lockid",lockid);



                i.putExtras(bundle);
                startActivity(i);
                finish();

                //create user
                /*auth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener(RegisterActivity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                Toast.makeText(RegisterActivity.this, "createUserWithEmail:onComplete:" + task.isSuccessful(), Toast.LENGTH_SHORT).show();
                                progressBar.setVisibility(View.GONE);
                                // If sign in fails, display a message to the user. If sign in succeeds
                                // the auth state listener will be notified and logic to handle the
                                // signed in user can be handled in the listener.
                                if (!task.isSuccessful()) {
                                    Toast.makeText(RegisterActivity.this, "Authentication failed." + task.getException(),
                                            Toast.LENGTH_SHORT).show();
                                } else {
                                    startActivity(new Intent(RegisterActivity.this, MainDisplay.class));
                                    finish();
                                }
                            }
                        });*/

            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        progressBar.setVisibility(View.GONE);
    }
}