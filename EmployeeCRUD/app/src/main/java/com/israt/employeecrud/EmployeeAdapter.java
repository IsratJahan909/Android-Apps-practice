package com.israt.employeecrud;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class EmployeeAdapter extends RecyclerView.Adapter<EmployeeAdapter.EmployeeViewHolder> {

    private List<Employee> employeeList;
    private Context context;
    private DatabaseHelper dbHelper;

    public EmployeeAdapter(List<Employee> employeeList, Context context) {
        this.employeeList = employeeList;
        this.context = context;
        this.dbHelper = new DatabaseHelper(context);
    }

    @NonNull
    @Override
    public EmployeeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_employee, parent, false);
        return new EmployeeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull EmployeeViewHolder holder, int position) {
        final int currentPosition = holder.getAdapterPosition();
        Employee employee = employeeList.get(currentPosition);
        
        holder.tvName.setText(employee.getName() != null ? employee.getName() : "No Name");
        holder.tvEmail.setText(employee.getEmail() != null ? employee.getEmail() : "No Email");
        holder.tvDept.setText("Dept: " + (employee.getDepartment() != null ? employee.getDepartment() : "N/A"));

        if (employee.getProfileImagePath() != null && !employee.getProfileImagePath().isEmpty()) {
            try {
                holder.ivProfileItem.setImageURI(Uri.parse(employee.getProfileImagePath()));
            } catch (Exception e) {
                holder.ivProfileItem.setImageResource(android.R.drawable.ic_menu_gallery);
            }
        } else {
            holder.ivProfileItem.setImageResource(android.R.drawable.ic_menu_gallery);
        }

        holder.btnView.setOnClickListener(v -> {
            Intent intent = new Intent(context, ViewEmployeeActivity.class);
            intent.putExtra("EMPLOYEE_ID", employee.getId());
            context.startActivity(intent);
        });

        holder.btnEdit.setOnClickListener(v -> {
            Intent intent = new Intent(context, CreateEmployeeActivity.class);
            intent.putExtra("EMPLOYEE_ID", employee.getId());
            context.startActivity(intent);
        });

        holder.btnDelete.setOnClickListener(v -> {
            new AlertDialog.Builder(context)
                    .setTitle("Delete Employee")
                    .setMessage("Are you sure?")
                    .setPositiveButton("Yes", (dialog, which) -> {
                        dbHelper.deleteEmployee(employee.getId());
                        employeeList.remove(currentPosition);
                        notifyItemRemoved(currentPosition);
                        notifyItemRangeChanged(currentPosition, employeeList.size());
                        Toast.makeText(context, "Deleted", Toast.LENGTH_SHORT).show();
                    })
                    .setNegativeButton("No", null)
                    .show();
        });
    }

    @Override
    public int getItemCount() {
        return employeeList != null ? employeeList.size() : 0;
    }

    public static class EmployeeViewHolder extends RecyclerView.ViewHolder {
        TextView tvName, tvEmail, tvDept;
        ImageView ivProfileItem;
        Button btnView, btnEdit, btnDelete;

        public EmployeeViewHolder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tvName);
            tvEmail = itemView.findViewById(R.id.tvEmail);
            tvDept = itemView.findViewById(R.id.tvDept);
            ivProfileItem = itemView.findViewById(R.id.ivProfileItem);
            btnView = itemView.findViewById(R.id.btnView);
            btnEdit = itemView.findViewById(R.id.btnEdit);
            btnDelete = itemView.findViewById(R.id.btnDelete);
        }
    }
}
