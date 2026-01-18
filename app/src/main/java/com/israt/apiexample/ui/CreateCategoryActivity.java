package com.israt.apiexample.ui;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.israt.apiexample.R;
import com.israt.apiexample.entity.Category;
import com.israt.apiexample.service.CategoryService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CreateCategoryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_create_category);

        EditText etedit;

        ImageView etpic;

        Uri imageUri;

        CategoryService repository;
        List<Category> allCategories = new ArrayList<>();


        repository = new CategoryService(this);

         etedit = findViewById(R.id.etedit);

        imgPreview = findViewById(R.id.imgPreview);

        Button btnPick = findViewById(R.id.btnPickImage);
        Button btnSave = findViewById(R.id.btnSave);

        btnPick.setOnClickListener(v -> pickImage());
        btnSave.setOnClickListener(v -> save());
    }

    private void pickImage() {
        Intent i = new Intent(Intent.ACTION_GET_CONTENT);
        i.setType("image/*");
        startActivityForResult(i, PICK_IMAGE);
    }

    @Override
    protected void onActivityResult(int r, int c, Intent data) {
        super.onActivityResult(r, c, data);
        if (r == PICK_IMAGE && c == RESULT_OK && data != null) {
            imageUri = data.getData();
            imgPreview.setImageURI(imageUri);
        }
    }

    private void save() {
        try {
            String name = etedit.getText().toString().trim();
            if (name.isEmpty()) {
                Toast.makeText(this, "Please enter category name", Toast.LENGTH_SHORT).show();
                return;
            }

            Category c = new Category();
            c.setName(name);

            // Get selected parentId




            repository.create(c, imageUri).enqueue(new Callback<Category>() {
                @Override
                public void onResponse(Call<Category> call, Response<Category> response) {
                    Toast.makeText(CreateCategoryActivity.this,
                            "Category Created", Toast.LENGTH_SHORT).show();
                    finish();
                }

                @Override
                public void onFailure(Call<Category> call, Throwable t) {
                    Toast.makeText(CreateCategoryActivity.this,
                            t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}