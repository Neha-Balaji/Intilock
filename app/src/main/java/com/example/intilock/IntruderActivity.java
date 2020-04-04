package com.example.intilock;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class IntruderActivity extends AppCompatActivity {
    private DrawerLayout dl;
    private ActionBarDrawerToggle t;
    private NavigationView nv;
    //recyclerview object
    public TextView text1,text2;


    private RecyclerView recyclerView;

    //adapter object
    private RecyclerView.Adapter adapter;

    //database reference
    private DatabaseReference mDatabase;

    //progress dialog
    private ProgressDialog progressDialog;

    //list to hold all the uploaded images
    private List<Upload> uploads;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intruder);
        Bundle bundle = getIntent().getExtras();

        //Extract the data…
        final String lockid = bundle.getString("lockid");
        final String username = bundle.getString("username");
        final int tp = bundle.getInt("tp");
        final Bundle bundle1 = new Bundle();


        bundle1.putString("username", username);

        bundle1.putString("lockid",lockid);
        bundle1.putInt("tp",tp);
        text1 = findViewById(R.id.textView);
        text2 = findViewById(R.id.textView1);



        /*intruder.setOnClickListener(new View.OnClickListener() {
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
        dl = (DrawerLayout)findViewById(R.id.activity_intruder);
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
                            i.putExtras(bundle1);
                            startActivity(i);
                            setContentView(R.layout.activity_profile);

                        }
                        else{
                            Intent i = new Intent(getApplicationContext(), Profile1Activity.class);
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

        /*recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
              recyclerView.setHasFixedSize(true);
                recyclerView.setLayoutManager(new LinearLayoutManager(this));


                progressDialog = new ProgressDialog(this);

                uploads = new ArrayList<>();

                //displaying progress dialog while fetching images
                progressDialog.setMessage("Please wait...");
                progressDialog.show();
                mDatabase = FirebaseDatabase.getInstance().getReference(Constants.DATABASE_PATH_UPLOADS);

                //adding an event listener to fetch values
                mDatabase.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot snapshot) {
                        //dismissing the progress dialog
                        progressDialog.dismiss();

                        //iterating through all the values in database
                        for (DataSnapshot postSnapshot : snapshot.getChildren()) {
                            Upload upload = postSnapshot.getValue(Upload.class);
                            uploads.add(upload);
                        }
                        //creating adapter
                        adapter = new MyAdapter(getApplicationContext(), uploads);

                        //adding adapter to recyclerview
                        recyclerView.setAdapter(adapter);
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        progressDialog.dismiss();
                    }
                });*/

            }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if(t.onOptionsItemSelected(item))
            return true;

        return super.onOptionsItemSelected(item);
    }
        }

