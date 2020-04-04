package com.example.intilock;

import android.content.Context;
import android.content.Intent;
import android.net.DhcpInfo;
import android.net.wifi.WifiManager;
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
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class MainDisplay extends AppCompatActivity {

    TextView mode,tpwd,opwd,lock;
    DatabaseReference rootRef,demoRef;
    private DrawerLayout dl;
    private ActionBarDrawerToggle t;
    private NavigationView nv;
    //public String s_gateway;
    //WifiManager wifii;
    //DhcpInfo d;

    final Bundle bundle1 = new Bundle();

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maindisplay);

        Bundle bundle = getIntent().getExtras();

        //Extract the data…
        final String lockid = bundle.getString("lockid");
        final String username = bundle.getString("username");
        bundle1.putString("username", username);

        bundle1.putString("lockid",lockid);
        Intent intent = new Intent(this, NetworkCheck.class);
        intent.putExtras(bundle1);
        this.startService(intent);


        rootRef = FirebaseDatabase.getInstance().getReference();

        //rootRef.child(lockid).child("Users").child(username).child("IP").setValue(s_gateway);


        mode = (TextView) findViewById(R.id.mode);
        tpwd = (TextView) findViewById(R.id.tpwd);
        opwd = (TextView) findViewById(R.id.opwd);
        lock= (TextView)findViewById(R.id.lock);


        bundle1.putString("username", username);

        bundle1.putString("lockid",lockid);


        //database reference pointing to demo node
        demoRef = rootRef.child(lockid);
        lock.setText(lockid);
        demoRef.child("Mode").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String mode_type = dataSnapshot.getValue(String.class);
                mode.setText(mode_type);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
        demoRef.child("Users").child(username).child("Pin").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String pin_type = dataSnapshot.getValue(String.class);
                tpwd.setText(pin_type);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
        demoRef.child("Third_party").child("flag").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                int tp = Integer.parseInt(dataSnapshot.getValue(String.class));
                //int tp=1;
                display(tp);
                display_nav(tp);


            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });



/*
        intruder.setOnClickListener(new View.OnClickListener() {
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
    public void display_nav(final int tp){

        dl = (DrawerLayout)findViewById(R.id.activity_maindisplay);
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


    }
    public void display(final int tp){
        if (tp == 1) {
            demoRef.child("Third_party").child("pwd").addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    String tp1 = dataSnapshot.getValue(String.class);
                    opwd.setText(tp1);
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {
                }
            });
         /*   profile.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(getApplicationContext(), ProfileActivity.class);
                    bundle1.putInt("tp", tp);
                    i.putExtras(bundle1);
                    startActivity(i);
                    setContentView(R.layout.activity_profile);
                }
            });*/
        } else {
            opwd.setText("Nil");
        /*    profile.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(getApplicationContext(), Profile1Activity.class);
                    bundle1.putInt("tp", tp);
                    i.putExtras(bundle1);
                    startActivity(i);

                    setContentView(R.layout.activity_profile1);
                }
            });*/
        }
        }

    }









