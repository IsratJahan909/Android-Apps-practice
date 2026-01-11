package com.israt.employeecrud;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class EmployeeListActivity extends AppCompatActivity {

    private RecyclerView rvEmployees;
    private EmployeeAdapter adapter;
    private DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee_list);

        rvEmployees = findViewById(R.id.rvEmployees);
        dbHelper = new DatabaseHelper(this);

        refreshList();
    }

    @Override
    protected void onResume() {
        super.onResume();
        refreshList();
    }

    private void refreshList() {
        List<Employee> employeeList = dbHelper.getAllEmployees();
        adapter = new EmployeeAdapter(employeeList, this);
        rvEmployees.setLayoutManager(new LinearLayoutManager(this));
        rvEmployees.setAdapter(adapter);
    }
}
