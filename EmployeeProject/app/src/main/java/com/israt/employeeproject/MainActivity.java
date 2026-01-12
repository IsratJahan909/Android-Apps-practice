package com.israt.employeeproject;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.btnCreateEmployee.setOnClickListener(v -> {
            startActivity(new Intent(MainActivity.this, AddEmployeeActivity.class));
        });

        binding.btnListEmployees.setOnClickListener(v -> {
            startActivity(new Intent(MainActivity.this, EmployeeListActivity.class));

        });
    }
}