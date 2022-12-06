package com.example.codingwithbelong;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.MenuItem;

import com.example.codingwithbelong.Adapters.SectionsPagerAdapter;
import com.example.codingwithbelong.util.ScreenTimeManager;
import com.google.android.material.tabs.TabLayout;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class CourseDetailsActivity extends AppCompatActivity {


    private ViewPager mViewPager;
    private TabLayout mTabLayout;
    SectionsPagerAdapter pagerAdapter;

    public static String subject="";

    CountDownTimer timer;
    public static int time=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_details);
        getSupportActionBar().setElevation(0);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);



        Bundle bundle=getIntent().getExtras();
        if (bundle!=null){
            if (bundle.getString("course").equals("funda")){
                subject = "funda";
                getSupportActionBar().setTitle("Fundamentals");

            }else if (bundle.getString("course").equals("cpp")){
                subject = "cpp";
                getSupportActionBar().setTitle("C++");
            }
            else if (bundle.getString("course").equals("java")){
                subject = "java";
                getSupportActionBar().setTitle("Java");

            }
            else if (bundle.getString("course").equals("python")){
                subject = "python";
                getSupportActionBar().setTitle("Python");
            }
        }


        mViewPager = findViewById(R.id.tabPager);
        mTabLayout = findViewById(R.id.tabLayout);
        pagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());
        mViewPager.setAdapter(pagerAdapter);
        mTabLayout.setupWithViewPager(mViewPager);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId()==android.R.id.home){
            finish();
        }
        return true;
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("fkldjfld","onresume");
        timer = new CountDownTimer(999999999,1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                time += 1;
                Log.d("fkldjfld","time : "+time);
            }

            @Override
            public void onFinish() {

            }
        };
        timer.start();

    }

    @Override
    protected void onPause() {
        Log.d("mytestdata", "CourseDetailsActivity: onPause()");
        ScreenTimeManager.updateSessionTime(null);
        super.onPause();
    }
}