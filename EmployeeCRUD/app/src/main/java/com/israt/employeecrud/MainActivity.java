package com.israt.employeecrud;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnCreateEmployee = findViewById(R.id.btnCreateEmployee);
        Button btnListEmployee = findViewById(R.id.btnListEmployee);

        if (btnCreateEmployee != null) {
            btnCreateEmployee.setOnClickListener(v -> {
                Intent intent = new Intent(MainActivity.this, CreateEmployeeActivity.class);
                startActivity(intent);
            });
        }

        if (btnListEmployee != null) {
            btnListEmployee.setOnClickListener(v -> {
                Intent intent = new Intent(MainActivity.this, EmployeeListActivity.class);
                startActivity(intent);
            });
        }
    }
}
