package com.example.intilock;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;


import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.RadioGroup;
import android.widget.RadioButton;




import com.google.firebase.database.*;

public class Profile1Activity extends AppCompatActivity {
    private RadioGroup radioGroup;
    Button submit,add;

    DatabaseReference rootRef, demoRef, thirdRef;

    EditText time;

    TextView tppwd,alert_time;
    Button change1;
    private DrawerLayout dl;
    private ActionBarDrawerToggle t;
    private NavigationView nv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile1);

        time = (EditText) findViewById(R.id.time);
        change1= (Button)findViewById(R.id.change1);
        alert_time = (TextView) findViewById(R.id.alert_time);

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

        add= (Button)findViewById(R.id.add);
        submit = (Button)findViewById(R.id.submit);

        radioGroup = (RadioGroup)findViewById(R.id.groupradio);


        radioGroup.clearCheck();
        //database reference pointing to root of database
        rootRef = FirebaseDatabase.getInstance().getReference();

        //database reference pointing to demo node
        demoRef = rootRef.child(lockid).child("Users").child(username);
        thirdRef = rootRef.child(lockid);

        radioGroup.setOnCheckedChangeListener(
                new RadioGroup.OnCheckedChangeListener() {
                    @Override


                    public void onCheckedChanged(RadioGroup group,
                                                 int checkedId)
                    {


                        RadioButton
                                radioButton
                                = (RadioButton)group
                                .findViewById(checkedId);
                    }
                });

        // Add the Listener to the Submit Button
        submit.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v)
            {

                // When submit button is clicked,
                // Ge the Radio Button which is set
                // If no Radio Button is set, -1 will be returned
                int selectedId = radioGroup.getCheckedRadioButtonId();
                if (selectedId == -1) {
                    Toast.makeText(Profile1Activity.this,
                            "No answer has been selected",
                            Toast.LENGTH_SHORT)
                            .show();
                }
                else {

                    RadioButton radioButton
                            = (RadioButton)radioGroup
                            .findViewById(selectedId);

                    // Now display the value of selected item
                    // by the Toast message
                    Toast.makeText(Profile1Activity.this,
                            "You have Selected One"+radioButton.getText()+"Password",
                            Toast.LENGTH_LONG)
                            .show();

                    String value = radioButton.getText().toString();


                    rootRef.child(lockid).child("Mode").setValue(value);
                }
            }
        });



        add.setOnClickListener(new View.OnClickListener() {

                                   @Override
                                   public void onClick(View v) {
                                       Intent i = new Intent(getApplicationContext(),ThirdPartyActivity.class);
                                       i.putExtras(bundle1);
                                       startActivity(i);
                                       setContentView(R.layout.activity_thirdparty);

                                   }
                               }

        );

        rootRef.child(lockid).child("Users").child(username).child("Door_alert").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String da = dataSnapshot.getValue(String.class);
                alert_time.setText(da);
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });

        change1.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                String value = time.getText().toString();
                rootRef.child(lockid).child("Users").child(username).child("Door_alert").setValue(value);
                Toast.makeText(Profile1Activity.this,
                        "Door alert time have been updated",
                        Toast.LENGTH_SHORT)
                        .show();
            }
        });

        dl = (DrawerLayout)findViewById(R.id.activity_profile1);
        t = new ActionBarDrawerToggle(this, dl,R.string.Open, R.string.Close);

        dl.addDrawerListener(t);
        t.syncState();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        nv = (NavigationView)findViewById(R.id.nv);
        nv.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                switch(id)
                {
                    case R.id.home_nav: {
                        Intent i = new Intent(getApplicationContext(), MainDisplay.class);
                        i.putExtras(bundle1);
                        startActivity(i);
                        setContentView(R.layout.activity_maindisplay);
                        break;
                    }
                    case R.id.intruder_nav: {
                        Intent i = new Intent(getApplicationContext(), IntruderActivity.class);
                        i.putExtras(bundle1);
                        startActivity(i);
                        setContentView(R.layout.activity_intruder);
                        break;
                    }
                    case R.id.profile_nav: {
                        if(tp==1){
                            Intent i = new Intent(getApplicationContext(), ProfileActivity.class);
                            bundle1.putInt("tp", tp);
                            i.putExtras(bundle1);
                            startActivity(i);
                            setContentView(R.layout.activity_profile);

                        }
                        else{
                            Intent i = new Intent(getApplicationContext(), Profile1Activity.class);
                            bundle1.putInt("tp", tp);
                            i.putExtras(bundle1);
                            startActivity(i);
                            setContentView(R.layout.activity_profile1);
                        }
                        break;
                    }
                    case R.id.homeline_nav: {
                        Intent i = new Intent(getApplicationContext(), HomelineActivity.class);
                        i.putExtras(bundle1);
                        startActivity(i);
                        setContentView(R.layout.activity_homeline);
                        break;
                    }
                    case R.id.notification_nav: {
                        Intent i = new Intent(getApplicationContext(), NotificationActivity.class);
                        i.putExtras(bundle1);
                        startActivity(i);
                        setContentView(R.layout.activity_notification);
                        break;
                    }
                    case R.id.manageppl_nav: {
                        Intent i = new Intent(getApplicationContext(), ManagePeopleactivity.class);
                        i.putExtras(bundle1);
                        startActivity(i);
                        setContentView(R.layout.activity_manageppl);
                        break;
                    }
                    default:
                        return true;
                }


                return true;

            }
        });

     /*  intruder.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Intent i = new Intent(getApplicationContext(), IntruderActivity.class);
               i.putExtras(bundle1);
               startActivity(i);
               setContentView(R.layout.activity_intruder);
           }
       });
       home.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Intent i = new Intent(getApplicationContext(), MainDisplay.class);
               i.putExtras(bundle1);
               startActivity(i);
               setContentView(R.layout.activity_maindisplay);
           }
       });
       homeline.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Intent i = new Intent(getApplicationContext(), HomelineActivity.class);
               i.putExtras(bundle1);
               startActivity(i);
               setContentView(R.layout.activity_homeline);
           }
       });
       if(tp ==1) {
           profile.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View v) {
                   Intent i = new Intent(getApplicationContext(), ProfileActivity.class);
                   i.putExtras(bundle1);
                   startActivity(i);
                   setContentView(R.layout.activity_profile);
               }
           });
       }
       else
       {
           profile.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View v) {
                   Intent i = new Intent(getApplicationContext(), Profile1Activity.class);
                   i.putExtras(bundle1);
                   startActivity(i);
                   setContentView(R.layout.activity_profile1);
               }
           });
       }
       notification.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Intent i = new Intent(getApplicationContext(), NotificationActivity.class);
               i.putExtras(bundle1);
               startActivity(i);
               setContentView(R.layout.activity_notification);
           }
       });
       manageppl.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Intent i = new Intent(getApplicationContext(), ManagePeopleactivity.class);
               i.putExtras(bundle1);
               startActivity(i);
               setContentView(R.layout.activity_manageppl);
           }
       });
*/
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if(t.onOptionsItemSelected(item))
            return true;

        return super.onOptionsItemSelected(item);
    }
}


