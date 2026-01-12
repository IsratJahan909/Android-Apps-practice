package com.israt.employeeproject.layout;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.israt.employeeproject.R;
import com.israt.employeeproject.adapter.EmployeeAdapter;
import com.israt.employeeproject.db.DatabaseHelper;

public class EmployeeListActivity extends AppCompatActivity {

    private ActivityEmployeeListBinding binding;
    private EmployeeAdapter adapter;
    private DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityEmployeeListBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("কর্মচারী তালিকা");
        }

        dbHelper = new DatabaseHelper(this);
        adapter = new EmployeeAdapter();

        binding.recyclerView.setLayoutManager(new LinearLayoutManager(this));
        binding.recyclerView.setAdapter(adapter);

        loadEmployees();
    }

    private void loadEmployees() {
        adapter.setEmployees(dbHelper.getAllEmployees());
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadEmployees(); // refresh after adding new employee
    }
}