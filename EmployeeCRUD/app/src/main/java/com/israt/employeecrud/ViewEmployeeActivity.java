package com.israt.employeecrud;

import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class ViewEmployeeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_employee);

        ImageView ivProfile = findViewById(R.id.ivViewProfile);
        TextView tvName = findViewById(R.id.tvViewName);
        TextView tvEmail = findViewById(R.id.tvViewEmail);
        TextView tvPhone = findViewById(R.id.tvViewPhone);
        TextView tvAge = findViewById(R.id.tvViewAge);
        TextView tvSalary = findViewById(R.id.tvViewSalary);
        TextView tvDept = findViewById(R.id.tvViewDept);
        TextView tvSkills = findViewById(R.id.tvViewSkills);
        TextView tvActive = findViewById(R.id.tvViewActive);
        TextView tvJoiningDate = findViewById(R.id.tvViewJoiningDate);
        Button btnBack = findViewById(R.id.btnBack);

        int employeeId = getIntent().getIntExtra("EMPLOYEE_ID", -1);
        if (employeeId != -1) {
            DatabaseHelper dbHelper = new DatabaseHelper(this);
            Employee employee = dbHelper.getEmployee(employeeId);

            if (employee != null) {
                tvName.setText(employee.getName());
                tvEmail.setText(employee.getEmail());
                tvPhone.setText(employee.getPhone());
                tvAge.setText(String.valueOf(employee.getAge()));
                tvSalary.setText("$" + employee.getSalary());
                tvDept.setText(employee.getDepartment());
                tvSkills.setText(employee.getSkills());
                tvActive.setText(employee.getActive() == 1 ? "Active" : "Inactive");

                SimpleDateFormat sdf = new SimpleDateFormat("dd MMM yyyy", Locale.getDefault());
                tvJoiningDate.setText(sdf.format(new Date(employee.getJoiningDate())));

                if (employee.getProfileImagePath() != null && !employee.getProfileImagePath().isEmpty()) {
                    ivProfile.setImageURI(Uri.parse(employee.getProfileImagePath()));
                }
            }
        }

        btnBack.setOnClickListener(v -> finish());
    }
}
