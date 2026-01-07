package com.example.myapplicationtt.layouts2;

import android.content.Intent;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplicationtt.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public abstract class BaseActivity extends AppCompatActivity {

    protected void setupBase(int layoutResId, int selectedItemId) {
        setContentView(R.layout.activity_base);

        // Load screen layout
        getLayoutInflater().inflate(
                layoutResId,
                findViewById(R.id.contentContainer),
                true
        );

        BottomNavigationView bottomNav = findViewById(R.id.bottomNavigation);
        bottomNav.setSelectedItemId(selectedItemId);

        bottomNav.setOnItemSelectedListener(item -> {
            int id = item.getItemId();
            if (id == selectedItemId) return true;

            Intent intent = null;

            if (id == R.id.nav_home) {
                intent = new Intent(this, HomeActivity2.class);
            } else if (id == R.id.nav_form) {
                intent = new Intent(this, FormActivity2.class);
            } else if (id == R.id.nav_scroll) {
                intent = new Intent(this, ScrollActivity2.class);
            } else if (id == R.id.nav_profile) {
                intent = new Intent(this, ProfileActivity2.class);
            } else if (id == R.id.nav_grid) {
                intent = new Intent(this, GridActivity2.class);

            } else if (id == R.id.nav_table) {
                intent = new Intent(this, TableActivity.class);
            }

            if (intent != null) {
                startActivity(intent);
                finish();
            }
            return true;
        });
    }
}