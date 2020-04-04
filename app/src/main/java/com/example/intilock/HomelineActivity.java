package com.example.intilock;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class HomelineActivity extends AppCompatActivity {
    TextView head,body;
    DatabaseReference rootRef,demoRef;
    private DrawerLayout dl;
    private ActionBarDrawerToggle t;
    private NavigationView nv;

    public String content= "";


        @Override
        protected void onCreate(Bundle savedInstanceState) {

            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_homeline);

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
            body = (TextView) findViewById(R.id.body);

            rootRef = FirebaseDatabase.getInstance().getReference();


            //database reference pointing to demo node
            demoRef = rootRef.child(lockid);
            demoRef.child("Gateway").addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    String gateway = dataSnapshot.getValue(String.class);
                    displayhl(gateway);


                }

                @Override
                public void onCancelled(DatabaseError databaseError) {
                }
            });
            dl = (DrawerLayout)findViewById(R.id.activity_homeline);
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

       /*     intruder.setOnClickListener(new View.OnClickListener() {
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
            });*/
        }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if(t.onOptionsItemSelected(item))
            return true;

        return super.onOptionsItemSelected(item);
    }
    public void displayhl(final String gateway1)
    {

        demoRef.child("Users").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Iterable<DataSnapshot> users = dataSnapshot.getChildren();
                for(DataSnapshot user : users) {
                    String ip = user.child("IP").getValue(String.class);
                    if(gateway1.equals(ip)){
                        String name = user.getKey();
                        content += name + "\n";
                    }
                }
                body.setText(content);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });



        }

}
