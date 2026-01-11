package com.israt.employeeproject.layout;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.israt.employeeproject.R;
import com.israt.employeeproject.db.DatabaseHelper;

public class CreateEmployeeActivity extends AppCompatActivity {

    private EditText etName, etEmail, etPhone, etAge, etSalary, stDepartment, stSkills;
    private ImageView ivProfile;
    private Button btnSelectImage, btnSubmit;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_create_employee);




    }
}