package com.israt.employeeproject.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.israt.employeeproject.entity.Employee;

public class EmployeeDao {
    private DatabaseHelper dbHelper;

    public EmployeeDao(DatabaseHelper dbHelper ) {
       this.dbHelper = dbHelper;
    }
    public long insert(Employee employee) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put("name", employee.getName());
        values.put("email", employee.getEmail());
        values.put("phone", employee.getPhone());
        values.put("age", employee.getAge());
        values.put("salary", employee.getSalary());
        values.put("active", employee.getActive());
        values.put("joiningDate", employee.getJoiningDate());
        values.put("department", employee.getDepartment());
        values.put("skills", employee.getSkills());
        values.put("profileImagePath", employee.getProfileImagePath());

        long id = db.insert("employees", null, values);
        db.close();
        return id;
    }
    public int update(Employee employee) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        int result = db.update("employees", values, "id = ?", new String[]{String.valueOf(employee.getId())});
        db.close();
        return result;
    }
    public void delete(int id) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        db.delete("employees", "id = ?", new String[]{String.valueOf(id)});
        db.close();
    }
}
