package com.israt.employeeproject.layout;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import com.israt.employeeproject.R;
import com.israt.employeeproject.db.DatabaseHelper;
import com.israt.employeeproject.model.Employee;

import java.util.Calendar;

public class CreateEmployeeActivity extends AppCompatActivity {

    private EditText etName, etEmail, etPhone, etAge, etSalary, etDepartment, etSkills;
    private ImageView ivProfile;
    private Button btnSelectImage, btnSubmit;

    private Uri imageUri = null; // সিলেক্ট করা ইমেজের URI

    private final ActivityResultLauncher<Intent> imagePickerLauncher =
            registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), result -> {
                if (result.getResultCode() == RESULT_OK && result.getData() != null) {
                    imageUri = result.getData().getData();
                    ivProfile.setImageURI(imageUri);
                }
            });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_create_employee);

        // UI বাইন্ডিং
        initViews();

        // গ্যালারি থেকে ইমেজ সিলেক্ট করা
        btnSelectImage.setOnClickListener(v -> openGallery());

        // সাবমিট বাটন ক্লিক
        btnSubmit.setOnClickListener(v -> saveEmployee());
    }

    private void initViews() {
        etName = findViewById(R.id.etName);
        etEmail = findViewById(R.id.etEmail);
        etPhone = findViewById(R.id.etPhone);
        etAge = findViewById(R.id.etAge);
        etSalary = findViewById(R.id.etSalary);
        etDepartment = findViewById(R.id.etDepartment);
        etSkills = findViewById(R.id.etSkills);

        ivProfile = findViewById(R.id.ivProfile);
        btnSelectImage = findViewById(R.id.btnSelectImage);
        btnSubmit = findViewById(R.id.btnSubmit);
    }

    private void openGallery() {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        imagePickerLauncher.launch(intent);
    }

    private void saveEmployee() {
        // ইনপুট নেওয়া
        String name = etName.getText().toString().trim();
        String email = etEmail.getText().toString().trim();
        String phone = etPhone.getText().toString().trim();
        String ageStr = etAge.getText().toString().trim();
        String salaryStr = etSalary.getText().toString().trim();
        String department = etDepartment.getText().toString().trim();
        String skills = etSkills.getText().toString().trim();

        // বেসিক ভ্যালিডেশন
        if (name.isEmpty()) {
            etName.setError("নাম দিন");
            etName.requestFocus();
            return;
        }
        if (email.isEmpty()) {
            etEmail.setError("ইমেইল দিন");
            etEmail.requestFocus();
            return;
        }

        int age = 0;
        try {
            if (!ageStr.isEmpty()) age = Integer.parseInt(ageStr);
        } catch (NumberFormatException e) {
            etAge.setError("সঠিক বয়স দিন");
            return;
        }

        double salary = 0;
        try {
            if (!salaryStr.isEmpty()) salary = Double.parseDouble(salaryStr);
        } catch (NumberFormatException e) {
            etSalary.setError("সঠিক বেতন দিন");
            return;
        }

        // ইমেজ পাথ (এখানে শুধু URI সেভ করা হলো, প্রোডাকশনে ফাইল কপি করে পাথ সেভ করতে হবে)
        String profileImagePath = imageUri != null ? imageUri.toString() : null;

        // Employee অবজেক্ট তৈরি
        Employee employee = new Employee();
        employee.setName(name);
        employee.setEmail(email);
        employee.setPhone(phone);
        employee.setAge(age);
        employee.setSalary(salary);
        employee.setActive(true);
        employee.setJoiningDate(Calendar.getInstance().getTimeInMillis());
        employee.setDepartment(department);
        employee.setSkills(skills);
        employee.setProfileImagePath(profileImagePath);

        // ডাটাবেসে সেভ
        DatabaseHelper dbHelper = new DatabaseHelper(this);
        long id = dbHelper.addEmployee(employee);

        if (id > 0) {
            Toast.makeText(this, "কর্মচারী সফলভাবে যোগ হয়েছে!", Toast.LENGTH_SHORT).show();
            finish(); // অ্যাক্টিভিটি বন্ধ করে আগের স্ক্রিনে ফিরে যাবে
        } else {
            Toast.makeText(this, "কোনো সমস্যা হয়েছে!", Toast.LENGTH_SHORT).show();
        }
    }
}