package com.israt.employeeproject.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "EmployeeDB";
    private static final int DATABASE_VERSION = 1;

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String TABLE_CREATE = "CREATE TABLE employees (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "name TEXT, " +
                "email TEXT, " +
                "phone TEXT, " +
                "age INTEGER, " +
                "salary REAL, " +
                "active INTEGER, " +
                "joiningDate INTEGER, " +
                "department TEXT, " +
                "skills TEXT, " +
                "profileImagePath TEXT" +
                ");";
        db.execSQL(TABLE_CREATE);
    }
    @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("DROP TABLE IF EXISTS employees");
            onCreate(db);
        }
}
