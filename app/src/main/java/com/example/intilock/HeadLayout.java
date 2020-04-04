package com.example.intilock;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.google.firebase.database.*;

public class HeadLayout extends AppCompatActivity {
    private EditText inputLockid, inputScratchid;
    private Button btnSubmit,btnnohead;
    DatabaseReference rootRef, demoRef;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_headlayout);
        btnSubmit = (Button) findViewById(R.id.btn_submit);
        btnnohead = (Button) findViewById(R.id.btn_no_head);
        inputLockid = (EditText) findViewById(R.id.lockid);
        inputScratchid = (EditText) findViewById(R.id.scratchid);
        //database reference pointing to root of database
        rootRef = FirebaseDatabase.getInstance().getReference();

        btnnohead.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HeadLayout.this, SignUpActivity.class));
            }
        });

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String lockid = inputLockid.getText().toString();
                final String scratchid = inputScratchid.getText().toString();

                if (TextUtils.isEmpty(lockid)) {
                    Toast.makeText(getApplicationContext(), "Enter Lock id!", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(scratchid)) {
                    Toast.makeText(getApplicationContext(), "Enter Scratch id!", Toast.LENGTH_SHORT).show();
                    return;
                }
                demoRef= rootRef.child(lockid);
                demoRef.child("Gateway").setValue("0");
                demoRef.child("Scratchid").setValue(scratchid);
                //demoRef.child("Users").child("Head").child("Head").setValue("1");



                Intent i = new Intent(HeadLayout.this, RegisterActivity1.class);
                Bundle bundle = new Bundle();


                bundle.putString("lockid", lockid);


                i.putExtras(bundle);


                startActivity(i);
                //startActivity(new Intent(HeadLayout.this, RegisterActivity.class));
            }
        });
    }
}
