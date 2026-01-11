package com.israt.employeecrud;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "EmployeeDB";
    private static final int DATABASE_VERSION = 1;

    public static final String TABLE_EMPLOYEES = "employees";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_EMAIL = "email";
    public static final String COLUMN_PHONE = "phone";
    public static final String COLUMN_AGE = "age";
    public static final String COLUMN_SALARY = "salary";
    public static final String COLUMN_ACTIVE = "active";
    public static final String COLUMN_JOINING_DATE = "joiningDate";
    public static final String COLUMN_DEPARTMENT = "department";
    public static final String COLUMN_SKILLS = "skills";
    public static final String COLUMN_PROFILE_IMAGE = "profileImagePath";

    private static final String TABLE_CREATE =
            "CREATE TABLE " + TABLE_EMPLOYEES + " (" +
                    COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    COLUMN_NAME + " TEXT, " +
                    COLUMN_EMAIL + " TEXT, " +
                    COLUMN_PHONE + " TEXT, " +
                    COLUMN_AGE + " INTEGER, " +
                    COLUMN_SALARY + " REAL, " +
                    COLUMN_ACTIVE + " INTEGER, " +
                    COLUMN_JOINING_DATE + " INTEGER, " +
                    COLUMN_DEPARTMENT + " TEXT, " +
                    COLUMN_SKILLS + " TEXT, " +
                    COLUMN_PROFILE_IMAGE + " TEXT" +
                    ");";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TABLE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_EMPLOYEES);
        onCreate(db);
    }

    public long insertEmployee(Employee employee) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME, employee.getName());
        values.put(COLUMN_EMAIL, employee.getEmail());
        values.put(COLUMN_PHONE, employee.getPhone());
        values.put(COLUMN_AGE, employee.getAge());
        values.put(COLUMN_SALARY, employee.getSalary());
        values.put(COLUMN_ACTIVE, employee.getActive());
        values.put(COLUMN_JOINING_DATE, employee.getJoiningDate());
        values.put(COLUMN_DEPARTMENT, employee.getDepartment());
        values.put(COLUMN_SKILLS, employee.getSkills());
        values.put(COLUMN_PROFILE_IMAGE, employee.getProfileImagePath());

        long id = db.insert(TABLE_EMPLOYEES, null, values);
        db.close();
        return id;
    }

    public int updateEmployee(Employee employee) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME, employee.getName());
        values.put(COLUMN_EMAIL, employee.getEmail());
        values.put(COLUMN_PHONE, employee.getPhone());
        values.put(COLUMN_AGE, employee.getAge());
        values.put(COLUMN_SALARY, employee.getSalary());
        values.put(COLUMN_ACTIVE, employee.getActive());
        values.put(COLUMN_JOINING_DATE, employee.getJoiningDate());
        values.put(COLUMN_DEPARTMENT, employee.getDepartment());
        values.put(COLUMN_SKILLS, employee.getSkills());
        values.put(COLUMN_PROFILE_IMAGE, employee.getProfileImagePath());

        return db.update(TABLE_EMPLOYEES, values, COLUMN_ID + " = ?", new String[]{String.valueOf(employee.getId())});
    }

    public void deleteEmployee(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_EMPLOYEES, COLUMN_ID + " = ?", new String[]{String.valueOf(id)});
        db.close();
    }

    public Employee getEmployee(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_EMPLOYEES, null, COLUMN_ID + "=?", new String[]{String.valueOf(id)}, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
            Employee employee = new Employee();
            employee.setId(cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_ID)));
            employee.setName(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_NAME)));
            employee.setEmail(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_EMAIL)));
            employee.setPhone(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_PHONE)));
            employee.setAge(cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_AGE)));
            employee.setSalary(cursor.getDouble(cursor.getColumnIndexOrThrow(COLUMN_SALARY)));
            employee.setActive(cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_ACTIVE)));
            employee.setJoiningDate(cursor.getLong(cursor.getColumnIndexOrThrow(COLUMN_JOINING_DATE)));
            employee.setDepartment(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_DEPARTMENT)));
            employee.setSkills(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_SKILLS)));
            employee.setProfileImagePath(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_PROFILE_IMAGE)));
            cursor.close();
            return employee;
        }
        return null;
    }

    public List<Employee> getAllEmployees() {
        List<Employee> employeeList = new ArrayList<>();
        String selectQuery = "SELECT * FROM " + TABLE_EMPLOYEES + " ORDER BY " + COLUMN_ID + " DESC";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                Employee employee = new Employee();
                employee.setId(cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_ID)));
                employee.setName(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_NAME)));
                employee.setEmail(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_EMAIL)));
                employee.setPhone(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_PHONE)));
                employee.setAge(cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_AGE)));
                employee.setSalary(cursor.getDouble(cursor.getColumnIndexOrThrow(COLUMN_SALARY)));
                employee.setActive(cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_ACTIVE)));
                employee.setJoiningDate(cursor.getLong(cursor.getColumnIndexOrThrow(COLUMN_JOINING_DATE)));
                employee.setDepartment(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_DEPARTMENT)));
                employee.setSkills(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_SKILLS)));
                employee.setProfileImagePath(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_PROFILE_IMAGE)));
                employeeList.add(employee);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return employeeList;
    }
}
