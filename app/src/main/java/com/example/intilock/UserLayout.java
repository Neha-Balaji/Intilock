package com.example.intilock;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class UserLayout extends AppCompatActivity {
    private EditText inputLockid;
    private Button btnRequest,btnnouser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_userlayout);
        btnRequest = (Button) findViewById(R.id.btn_request);
        btnnouser = (Button) findViewById(R.id.btn_no_user);
        inputLockid = (EditText) findViewById(R.id.lockid);

        btnnouser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(UserLayout.this, SignUpActivity.class));
            }
        });

        btnRequest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String lockid = inputLockid.getText().toString();


                if (TextUtils.isEmpty(lockid)) {
                    Toast.makeText(getApplicationContext(), "Enter Lock id!", Toast.LENGTH_SHORT).show();
                    return;
                }

                else
                {
                    Toast.makeText(getApplicationContext(), "Request accepted", Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(UserLayout.this, RegisterActivity1.class);
                    Bundle bundle = new Bundle();


                    bundle.putString("lockid", lockid);


                    i.putExtras(bundle);


                    startActivity(i);
                    //startActivity(new Intent(UserLayout.this, RegisterActivity1.class));
                }

            }
        });
    }
}
