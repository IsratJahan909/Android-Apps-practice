package com.example.myapplicationtt.testActivity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.myapplicationtt.R;
import com.example.myapplicationtt.db.UserDao;
import com.example.myapplicationtt.entity.User;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TestFormActivity extends AppCompatActivity {

    ProgressBar progressBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_form3);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        EditText etName = findViewById(R.id.etName);
        EditText etEmail = findViewById(R.id.etEmail);
        EditText etPassword = findViewById(R.id.etPassword);
        EditText etPhone = findViewById(R.id.etPhone);
        RadioGroup rgGender = findViewById(R.id.rgGender);
        DatePicker datePicker = findViewById(R.id.datePicker);
        Spinner spCountry = findViewById(R.id.spCountry);
        CheckBox cbJava = findViewById(R.id.cbJava);
        CheckBox cbAndroid = findViewById(R.id.cbAndroid);
        CheckBox cbFlutter = findViewById(R.id.cbFlutter);
        CheckBox cbTerms = findViewById(R.id.cbTerms);
        Button btnRegister = findViewById(R.id.btnRegister);
        TextView goLogin = findViewById(R.id.goLogin);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, Arrays.asList("Bangladesh", "India", "USA", "UK"));
        spCountry.setAdapter(adapter);


        //NEW-------------
        UserDao userDao = new UserDao(this);
        long userId = getIntent().getLongExtra("userId", -1);
        User editingUser = null;

        // ---------------- PREFILL IF UPDATE ----------------
        if (userId != -1) {
            editingUser = userDao.getUserByID(userId);

            etName.setText(editingUser.getName());
            etEmail.setText(editingUser.getEmail());
            etPassword.setText(editingUser.getPassword());
            etPhone.setText(editingUser.getPhone());

            // Gender
            for (int i = 0; i < rgGender.getChildCount(); i++) {
                RadioButton rb = (RadioButton) rgGender.getChildAt(i);
                if (rb.getText().toString().equals(editingUser.getGender())) {
                    rb.setChecked(true);
                    break;
                }
            }

            // DOB
            String[] dobParts = editingUser.getDob().split("/");
            datePicker.updateDate(
                    Integer.parseInt(dobParts[2]),
                    Integer.parseInt(dobParts[1]) - 1,
                    Integer.parseInt(dobParts[0])
            );

            // Country
            int pos = adapter.getPosition(editingUser.getCountry());
            spCountry.setSelection(pos);

            // Skills
            String[] skills = editingUser.getSkills().split(",");
            cbJava.setChecked(Arrays.asList(skills).contains("Java"));
            cbAndroid.setChecked(Arrays.asList(skills).contains("Android"));
            cbFlutter.setChecked(Arrays.asList(skills).contains("Flutter"));

            // Terms
            cbTerms.setChecked(editingUser.isTermsAccepted());

            btnRegister.setText("Update");
        }


        //NEW-------------



        btnRegister.setOnClickListener(view -> {

            String name = etName.getText().toString();
            String email = etEmail.getText().toString();
            String password = etPassword.getText().toString();
            String phone = etPhone.getText().toString();

            int selectedGenderId = rgGender.getCheckedRadioButtonId();
            String gender = selectedGenderId == -1 ? "Not Selected" : ((RadioButton) findViewById(selectedGenderId)).getText().toString();

            String dob = datePicker.getDayOfMonth() + "/" + (datePicker.getMonth() + 1) + "/" + datePicker.getYear();

            String country = spCountry.getSelectedItem().toString();

            List<String> skills = new ArrayList<>();
            if (cbJava.isChecked()) {
                skills.add("Java");
            }
            ;
            if (cbAndroid.isChecked()) skills.add("Android");
            if (cbFlutter.isChecked()) skills.add("Flutter");

            boolean acceptedTerms = cbTerms.isChecked();
            String skillString = skills.isEmpty() ? "" : String.join(",", skills);
            User user = new User(
                    name,
                    email,
                    password,
                    phone,
                    gender,
                    dob,
                    country,
                    skillString,
                    acceptedTerms
            );

            if (userId != -1) {
                user.setId(userId);
                userDao.updateUser(userId,user);
                Toast.makeText(this, "User Updated!", Toast.LENGTH_SHORT).show();
            } else {
                long id = userDao.insertUser(user);
                Toast.makeText(this, "Saved! ID: " + id, Toast.LENGTH_SHORT).show();
            }
        });

    }

    // 1️⃣ Toast
    public void showToast(View view) {
        Toast.makeText(this, "This is Toast", Toast.LENGTH_SHORT).show();
    }

    // 2️⃣ Snackbar
    public void showSnackbar(View view) {
        Snackbar snackbar = Snackbar.make(view, "This is Snackbar", Snackbar.LENGTH_LONG);
        snackbar.setAction("UNDO", v -> Toast.makeText(this, "Undo Clicked", Toast.LENGTH_SHORT).show());
        snackbar.setBackgroundTint(Color.BLACK);
        snackbar.setTextColor(Color.WHITE);
        snackbar.show();
    }

    // 3️⃣ AlertDialog (Modal)
    public void showAlert(View view) {
        new AlertDialog.Builder(this).setTitle("Delete Item").setMessage("Are you sure?").setPositiveButton("Yes", (d, w) -> Toast.makeText(this, "Deleted", Toast.LENGTH_SHORT).show()).setNegativeButton("No", null).show();
    }

    // 4️⃣ Custom Dialog
    public void showCustomDialog(View view) {
        Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.dialog_custom);
        dialog.setCancelable(false);

        Button close = dialog.findViewById(R.id.btnClose);
        close.setOnClickListener(v -> dialog.dismiss());

        dialog.show();
    }

    // 5️⃣ Bottom Sheet
    public void showBottomSheet(View view) {
        BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(this);
        View sheetView = LayoutInflater.from(this).inflate(R.layout.bottom_sheet_layout, null);
        bottomSheetDialog.setContentView(sheetView);
        bottomSheetDialog.show();
    }

    // 6️⃣ ProgressBar
    public void showProgress(View view) {
        progressBar.setVisibility(View.VISIBLE);

        new Handler().postDelayed(() -> progressBar.setVisibility(View.GONE), 2000);
    }


//            Toast.makeText(this, value, Toast.LENGTH_LONG).show();


//            new AlertDialog.Builder(this)
//                    .setTitle("Form Data")
//                    .setMessage(value)
//                    .setPositiveButton("OK", null)
//                    .show();

//            new AlertDialog.Builder(this)
//                    .setTitle("Delete Item")
//                    .setMessage("Are you sure?")
//                    .setPositiveButton("Yes", (d, w) -> {
//                        Toast.makeText(this, "Deleted", Toast.LENGTH_SHORT).show();
//                    }).setNegativeButton("No", (d, w) -> {
//                        Toast.makeText(this, "Not Deleted", Toast.LENGTH_SHORT).show();
//                    }).setNeutralButton("X",(d, w) -> {
//                        Toast.makeText(this, "Neutral", Toast.LENGTH_SHORT).show();
//                    }).show();


//            Snackbar snackbar = Snackbar.make(view, value, Snackbar.LENGTH_LONG);
//            snackbar.setAction("UNDO", v -> Toast.makeText(this, "Undo Clicked", Toast.LENGTH_SHORT).show());
//            snackbar.setBackgroundTint(Color.BLACK);
//            snackbar.setTextColor(Color.WHITE);
//            snackbar.show();


}