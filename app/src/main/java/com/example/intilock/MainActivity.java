package com.example.intilock;

import android.os.Bundle;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.support.annotation.NonNull;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.ImageView;
import android.view.View;
import android.widget.LinearLayout;
import android.view.WindowManager;
import android.content.Intent;

public class MainActivity extends AppCompatActivity {
    private TextView mTextMessage;
    private ImageView imageView;
    private String fullScreenInd;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    //mTextMessage.setText(R.string.title_home);
                    Intent i = new Intent(getApplicationContext(), LoginActivity.class);
                    startActivity(i);
                    setContentView(R.layout.activity_login);
                    return true;
                /*case R.id.navigation_dashboard:
                    mTextMessage.setText(R.string.title_dashboard);
                    return true;
                case R.id.navigation_notifications:
                    mTextMessage.setText(R.string.title_notifications);
                    return true;*/
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BottomNavigationView navView = findViewById(R.id.nav_view);
        mTextMessage = findViewById(R.id.message);
        navView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        imageView = findViewById(R.id.imageView);
        fullScreenInd = getIntent().getStringExtra("fullScreenIndicator");
        if ("y".equals(fullScreenInd)) {
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                    WindowManager.LayoutParams.FLAG_FULLSCREEN);
            getSupportActionBar().hide();

            imageView.setAdjustViewBounds(false);
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);

        }
    }

}
