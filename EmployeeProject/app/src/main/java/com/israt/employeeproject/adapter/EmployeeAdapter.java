package com.israt.employeeproject.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.israt.employeeproject.entity.Employee;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class EmployeeAdapter extends RecyclerView.Adapter<EmployeeAdapter.ViewHolder> {

    private List<Employee> employees = new ArrayList<>();

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemEmployeeBinding binding = ItemEmployeeBinding.inflate(
                LayoutInflater.from(parent.getContext()), parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Employee employee = employees.get(position);

        holder.binding.tvName.setText(employee.getName());
        holder.binding.tvDepartment.setText(employee.getDepartment());
        holder.binding.tvSalary.setText(String.format(Locale.US, "৳ %,d", (long) employee.getSalary()));

        SimpleDateFormat sdf = new SimpleDateFormat("dd MMM yyyy", Locale.getDefault());
        holder.binding.tvJoining.setText(sdf.format(new Date(employee.getJoiningDate())));
    }

    @Override
    public int getItemCount() {
        return employees.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        ItemEmployeeBinding binding;

        ViewHolder(ItemEmployeeBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
