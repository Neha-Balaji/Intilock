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

public class NotificationActivity extends AppCompatActivity {
    private DrawerLayout dl;
    private ActionBarDrawerToggle t;
    private NavigationView nv;
    DatabaseReference rootRef,demoRef;

    TextView body;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);
        Bundle bundle = getIntent().getExtras();

        //Extract the data…
        final String lockid = bundle.getString("lockid");
        final String username = bundle.getString("username");
        final int tp = bundle.getInt("tp");
        final Bundle bundle1 = new Bundle();


        bundle1.putString("username", username);

        bundle1.putString("lockid",lockid);
        bundle1.putInt("tp",tp);
        dl = (DrawerLayout)findViewById(R.id.activity_notification);
        t = new ActionBarDrawerToggle(this, dl,R.string.Open, R.string.Close);

        dl.addDrawerListener(t);
        t.syncState();
        body = (TextView) findViewById(R.id.body);
        rootRef = FirebaseDatabase.getInstance().getReference();
        demoRef = rootRef.child(lockid);



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

       /* intruder.setOnClickListener(new View.OnClickListener() {
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
        demoRef.child("Dalrtflag").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String da = dataSnapshot.getValue(String.class);
                if(da.equals("1")){
                    body.setText("open");
                }
                else{
                    body.setText("close");
                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });

    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if(t.onOptionsItemSelected(item))
            return true;

        return super.onOptionsItemSelected(item);
    }
}
