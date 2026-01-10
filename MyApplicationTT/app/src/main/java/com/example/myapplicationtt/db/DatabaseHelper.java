package com.example.myapplicationtt.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String DB_NAME = "app_db";
    public static final int DB_VERSION = 1;

    public static final String TABLE_USER = "users";

    public static final String TABLE_ADDRESS = "address";

    public DatabaseHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE_USER =
                "CREATE TABLE " + TABLE_USER + "(" +
                        "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                        "name TEXT," +
                        "email TEXT," +
                        "password TEXT," +
                        "phone TEXT," +
                        "gender TEXT," +
                        "dob TEXT," +
                        "country TEXT," +
                        "skills TEXT," +
                        "terms INTEGER" +
                        ")";
        db.execSQL(CREATE_TABLE_USER);

        String CREATE_TABLE_ADDRESS =
                "CREATE TABLE " + TABLE_ADDRESS + "(" +
                        "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                        "name TEXT," +
                        "email TEXT," +
                        "password TEXT," +
                        "phone TEXT," +
                        "gender TEXT," +
                        "dob TEXT," +
                        "country TEXT," +
                        "skills TEXT," +
                        "terms INTEGER" +
                        ")";
        db.execSQL(CREATE_TABLE_ADDRESS);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USER);
        onCreate(db);
    }
}
