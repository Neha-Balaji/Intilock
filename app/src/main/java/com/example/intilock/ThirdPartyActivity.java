package com.example.intilock;



import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ThirdPartyActivity extends AppCompatActivity {
    EditText edittext;
    Button submit,back;
    DatabaseReference rootRef, thirdRef;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thirdparty);
        Bundle bundle = getIntent().getExtras();

        //Extract the data…
        final String lockid = bundle.getString("lockid");
        final String username = bundle.getString("username");
        final int tp = bundle.getInt("tp");
        final Bundle bundle1 = new Bundle();


        bundle1.putString("username", username);

        bundle1.putString("lockid",lockid);
        bundle1.putInt("tp",tp);
        Intent intent = new Intent(this, NetworkCheck.class);
        intent.putExtras(bundle1);
        this.startService(intent);
        edittext = (EditText) findViewById(R.id.addpwd);

        submit = (Button) findViewById(R.id.submit);
        back = (Button) findViewById(R.id.btnback);

        rootRef = FirebaseDatabase.getInstance().getReference();



        thirdRef = rootRef.child(lockid);
        submit.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                String value = edittext.getText().toString();
                thirdRef.child("Third_party").child("pwd").setValue(value);
                thirdRef.child("Third_party").child("flag").setValue("1");

            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), MainDisplay.class);
                i.putExtras(bundle1);
                startActivity(i);
                setContentView(R.layout.activity_maindisplay);
            }
        });
    }
}
