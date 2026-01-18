package com.israt.navigationproject.layout;

import android.content.Intent;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.israt.navigationproject.MainActivity;
import com.israt.navigationproject.R;

public abstract class BaseActivity extends AppCompatActivity {

    protected void setupBase(int layoutResId, int selectedItemId) {
        setContentView(R.layout.activity_base);

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
                intent = new Intent(this, MainActivity.class);
            } else if (id == R.id.nav_form) {
                intent = new Intent(this, FirstActivity.class);
            } else if (id == R.id.nav_scroll) {
                intent = new Intent(this, SecondActivity.class);
            } else if (id == R.id.nav_profile) {
                intent = new Intent(this, ThirdActivity.class);
            } else if (id == R.id.nav_grid) {
                intent = new Intent(this, ForthActivity.class);

            }

            if (intent != null) {
                startActivity(intent);
                finish();
            }
            return true;
        });

    }
    }