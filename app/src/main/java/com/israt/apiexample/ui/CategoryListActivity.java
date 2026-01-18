package com.israt.apiexample.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.israt.apiexample.R;
import com.israt.apiexample.adapters.CategoryAdapter;
import com.israt.apiexample.entity.Category;
import com.israt.apiexample.service.CategoryService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CategoryListActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    CategoryService service;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_category_list);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        service = new CategoryService(this);

        FloatingActionButton fab = findViewById(R.id.fabAdd);
        fab.setOnClickListener(v ->
                startActivity(new Intent(this, CreateCategoryActivity.class)));

    }
   @Override
    protected void onResume() {
     super.onResume();
     loadCategories();
    }

    private void loadCategories() {
   service.getAll().enqueue(new Callback<List<Category>>() {
       public void onResponse(Call<List<Category>> call,
                              Response<List<Category>> response) {

           recyclerView.setAdapter(
                   new CategoryAdapter(response.body(), service)
           );
       }


      @Override
       public void onFailure(Call<List<Category>> call, Throwable t) {
           Toast.makeText(CategoryListActivity.this,
                   t.getMessage(), Toast.LENGTH_SHORT).show();
       }

   })

    ;}
}



