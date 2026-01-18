package com.israt.apiexample.adapters;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.israt.apiexample.R;
import com.israt.apiexample.entity.Category;
import com.israt.apiexample.service.CategoryService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CategoryAdapter
        extends RecyclerView.Adapter<CategoryAdapter.VH> {

    List<Category> list;
    CategoryService repository;

    public CategoryAdapter(List<Category> list,
                           CategoryService repository) {
        this.list = list;
        this.repository = repository;
    }

    @NonNull
    @Override
    public VH onCreateViewHolder(@NonNull ViewGroup p, int v) {
        return new VH(LayoutInflater.from(p.getContext())
                .inflate(R.layout.row_category, p, false));
    }

    @Override
    public void onBindViewHolder(@NonNull VH h, @SuppressLint("RecyclerView") int i) {
        Category c = list.get(i);

        h.txtName.setText(c.getName());

        Glide.with(h.itemView.getContext())
                .load(c.getFullImageUrl())
                .into(h.image);

        h.btnDelete.setOnClickListener(v ->
                repository.delete(c.getId())
                        .enqueue(new Callback<Void>() {
                            @Override
                            public void onResponse(Call<Void> call,
                                                   Response<Void> response) {
                                list.remove(i);
                                notifyItemRemoved(i);
                            }

                            @Override
                            public void onFailure(Call<Void> call, Throwable t) {}
                        }));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    static class VH extends RecyclerView.ViewHolder {
        TextView txtName;
        ImageView image;
        Button btnDelete;

        VH(View v) {
            super(v);
            txtName = v.findViewById(R.id.txtName);
            image = v.findViewById(R.id.img);
            btnDelete = v.findViewById(R.id.btnDelete);
        }
    }
}
