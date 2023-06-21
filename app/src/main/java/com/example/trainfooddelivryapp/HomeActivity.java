package com.example.trainfooddelivryapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class HomeActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener{
    private BottomNavigationView bottomNavigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setOnNavigationItemSelectedListener(this);
        bottomNavigationView.setSelectedItemId(R.id.action_home);
    }
    HomeFragment firstFragment = new HomeFragment();
    OrderFragment fourthFragment = new OrderFragment();
    ProfileFragment fifthFragment = new ProfileFragment();

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_home:
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.flFragment, firstFragment)
                        .commit();
                return true;

            case R.id.action_orders:
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.flFragment, fourthFragment)
                        .commit();
                return true;
            case R.id.action_profile:
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.flFragment, fifthFragment)
                        .commit();
                return true;
        }
        return false;
    }
}