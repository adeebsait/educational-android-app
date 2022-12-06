package com.example.codingwithbelong;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import com.example.codingwithbelong.Fragments.DashboardFragment;
import com.example.codingwithbelong.Fragments.HomeFragment;
import com.example.codingwithbelong.Fragments.ProfileFragment;
import com.example.codingwithbelong.util.ScreenTimeManager;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class HomeActivity extends AppCompatActivity {
BottomNavigationView bNav;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        getSupportActionBar().setTitle("Home");
        getSupportFragmentManager().beginTransaction().replace(R.id.container,new HomeFragment()).commit();

        ScreenTimeManager.startTime=System.currentTimeMillis();
        ScreenTimeManager.saveTimeIn();

        bNav=findViewById(R.id.bNav);
        bNav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if (item.getItemId()==R.id.home){
                    getSupportActionBar().setTitle("Home");
                       getSupportFragmentManager().beginTransaction().replace(R.id.container,new HomeFragment()).commit();
                }
//                else if (item.getItemId()==R.id.dashboard){
//                    getSupportActionBar().setTitle("Dashboard");
//                    getSupportFragmentManager().beginTransaction().replace(R.id.container,new BashboardFragment()).commit();
//                }
                else if (item.getItemId()==R.id.profile){
                    getSupportActionBar().setTitle("Profile");
                    getSupportFragmentManager().beginTransaction().replace(R.id.container,new ProfileFragment()).commit();
                }
                return true;
            }
        });
    }
    @Override
    protected void onPause() {
        ScreenTimeManager.updateSessionTime(null);
        super.onPause();
    }
}