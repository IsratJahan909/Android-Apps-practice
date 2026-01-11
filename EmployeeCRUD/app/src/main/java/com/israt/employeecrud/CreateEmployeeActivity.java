package com.israt.employeecrud;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Date;

public class CreateEmployeeActivity extends AppCompatActivity {

    private EditText etName, etEmail, etPhone, etAge, etSalary, etDepartment, etSkills;
    private CheckBox cbActive;
    private ImageView ivProfile;
    private Button btnSave, btnSelectImage;
    private DatabaseHelper dbHelper;
    private String imagePath = "";
    private int employeeId = -1;

    private final ActivityResultLauncher<Intent> imagePickerLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            result -> {
                if (result.getResultCode() == RESULT_OK && result.getData() != null) {
                    Uri selectedImageUri = result.getData().getData();
                    if (selectedImageUri != null) {
                        imagePath = selectedImageUri.toString();
                        ivProfile.setImageURI(selectedImageUri);
                    }
                }
            }
    );

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_employee);

        dbHelper = new DatabaseHelper(this);

        ivProfile = findViewById(R.id.ivProfile);
        btnSelectImage = findViewById(R.id.btnSelectImage);
        etName = findViewById(R.id.etName);
        etEmail = findViewById(R.id.etEmail);
        etPhone = findViewById(R.id.etPhone);
        etAge = findViewById(R.id.etAge);
        etSalary = findViewById(R.id.etSalary);
        etDepartment = findViewById(R.id.etDepartment);
        etSkills = findViewById(R.id.etSkills);
        cbActive = findViewById(R.id.cbActive);
        btnSave = findViewById(R.id.btnSave);

        employeeId = getIntent().getIntExtra("EMPLOYEE_ID", -1);
        if (employeeId != -1) {
            loadEmployeeData();
            btnSave.setText("Update Employee");
        }

        btnSelectImage.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            imagePickerLauncher.launch(intent);
        });

        btnSave.setOnClickListener(v -> saveEmployee());
    }

    private void loadEmployeeData() {
        Employee employee = dbHelper.getEmployee(employeeId);
        if (employee != null) {
            etName.setText(employee.getName());
            etEmail.setText(employee.getEmail());
            etPhone.setText(employee.getPhone());
            etAge.setText(String.valueOf(employee.getAge()));
            etSalary.setText(String.valueOf(employee.getSalary()));
            etDepartment.setText(employee.getDepartment());
            etSkills.setText(employee.getSkills());
            cbActive.setChecked(employee.getActive() == 1);
            imagePath = employee.getProfileImagePath();
            if (imagePath != null && !imagePath.isEmpty()) {
                ivProfile.setImageURI(Uri.parse(imagePath));
            }
        }
    }

    private void saveEmployee() {
        String name = etName.getText().toString();
        String email = etEmail.getText().toString();
        String phone = etPhone.getText().toString();
        String ageStr = etAge.getText().toString();
        String salaryStr = etSalary.getText().toString();
        String department = etDepartment.getText().toString();
        String skills = etSkills.getText().toString();
        int active = cbActive.isChecked() ? 1 : 0;

        if (name.isEmpty() || email.isEmpty()) {
            Toast.makeText(this, "Please fill required fields", Toast.LENGTH_SHORT).show();
            return;
        }

        Employee employee = new Employee();
        if (employeeId != -1) employee.setId(employeeId);
        employee.setName(name);
        employee.setEmail(email);
        employee.setPhone(phone);
        employee.setAge(ageStr.isEmpty() ? 0 : Integer.parseInt(ageStr));
        employee.setSalary(salaryStr.isEmpty() ? 0.0 : Double.parseDouble(salaryStr));
        employee.setActive(active);
        employee.setJoiningDate(new Date().getTime());
        employee.setDepartment(department);
        employee.setSkills(skills);
        employee.setProfileImagePath(imagePath);

        long result;
        if (employeeId == -1) {
            result = dbHelper.insertEmployee(employee);
        } else {
            result = dbHelper.updateEmployee(employee);
        }

        if (result > 0) {
            Toast.makeText(this, "Success", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(CreateEmployeeActivity.this, EmployeeListActivity.class);
            startActivity(intent);
            finish();
        } else {
            Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show();
        }
    }
}
