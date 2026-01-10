package com.example.myapplicationtt.testActivity;

import androidx.appcompat.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;

import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplicationtt.R;
import com.example.myapplicationtt.db.UserDao;
import com.example.myapplicationtt.entity.User;
import com.example.myapplicationtt.adapters.UserAdapter;

import java.util.List;

public class UserListActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    UserAdapter adapter;
    UserDao userDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_list);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        userDao = new UserDao(this);

//        TextView tvUserCount = findViewById(R.id.goLogin);
//        tvUserCount.setText(userDao.getAllUsers().size());

        loadUsers();
    }

    private void loadUsers() {
        List<User> userList = userDao.getAllUsers();

        adapter = new UserAdapter(this, userList, user -> {
            // Show options dialog: Update or Delete
            String[] options = {"Update", "Delete"};
            new AlertDialog.Builder(this)
                    .setTitle("Select Action")
                    .setItems(options, (dialog, which) -> {
                        if (which == 0) {
//                            showUpdateDialog(user); // Update
                            Intent intent = new Intent(UserListActivity.this, TestFormActivity.class);
                            intent.putExtra("userId", user.getId()); // Pass user ID
                            startActivity(intent);

                        } else if (which == 1) {
                            // Delete
                            new AlertDialog.Builder(this)
                                    .setTitle("Delete User")
                                    .setMessage("Are you sure?")
                                    .setPositiveButton("Yes", (d, w) -> {
                                        userDao.deleteUser(user.getId());
                                        Toast.makeText(this, "Deleted", Toast.LENGTH_SHORT).show();
                                        loadUsers();
                                    })
                                    .setNegativeButton("No", null)
                                    .show();
                        }
                    }).show();
        });

        recyclerView.setAdapter(adapter);
    }

    // ------------------ UPDATE DIALOG ------------------
//    private void showUpdateDialog(User user) {
//        AlertDialog.Builder builder = new AlertDialog.Builder(this);
//        builder.setTitle("Update User");
//
//        // Create LinearLayout for EditTexts
//        LinearLayout layout = new LinearLayout(this);
//        layout.setOrientation(LinearLayout.VERTICAL);
//        layout.setPadding(50, 40, 50, 10);
//
//        // Name
//        EditText etName = new EditText(this);
//        etName.setText(user.getName());
//        etName.setHint("Name");
//        layout.addView(etName);
//
//        // Email
//        EditText etEmail = new EditText(this);
//        etEmail.setText(user.getEmail());
//        etEmail.setHint("Email");
//        layout.addView(etEmail);
//
//        // Phone
//        EditText etPhone = new EditText(this);
//        etPhone.setText(user.getPhone());
//        etPhone.setHint("Phone");
//        layout.addView(etPhone);
//
//        builder.setView(layout);
//
//        builder.setPositiveButton("Update", (dialog, which) -> {
//            // Save updated values
//            user.setName(etName.getText().toString());
//            user.setEmail(etEmail.getText().toString());
//            user.setPhone(etPhone.getText().toString());
//
//            userDao.updateUser(user); // call update in DB
//            Toast.makeText(this, "User Updated", Toast.LENGTH_SHORT).show();
//            loadUsers();
//        });
//
//        builder.setNegativeButton("Cancel", null);
//
//        builder.show();
//    }
}
