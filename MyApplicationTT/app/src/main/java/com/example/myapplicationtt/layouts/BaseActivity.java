package com.example.myapplicationtt.layouts;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplicationtt.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public abstract class BaseActivity extends AppCompatActivity {

    protected void setupBottomNav(int selectedItemId) {
        BottomNavigationView bottomNav = findViewById(R.id.bottomNavigation);

        if (bottomNav == null) return; // safety check

        bottomNav.setSelectedItemId(selectedItemId);

        bottomNav.setOnItemSelectedListener(item -> {
            int itemId = item.getItemId();

            if (itemId == selectedItemId) {
                return true;
            }

            Intent intent = null;

            if (itemId == R.id.nav_home) {
                intent = new Intent(this, HomeActivity.class);

            } else if (itemId == R.id.nav_form) {
                intent = new Intent(this, FormActivity.class);

            } else if (itemId == R.id.nav_scroll) {
                intent = new Intent(this, ScrollActivity.class);

            } else if (itemId == R.id.nav_profile) {
                intent = new Intent(this, ProfileActivity.class);
            }

            if (intent != null) {
                startActivity(intent);
                finish();
            }

            return true;
        });
    }
}
