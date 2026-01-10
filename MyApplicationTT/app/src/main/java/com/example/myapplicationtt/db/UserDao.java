package com.example.myapplicationtt.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.myapplicationtt.entity.User;

import java.util.ArrayList;
import java.util.List;

public class UserDao {

    private final DatabaseHelper dbHelper;

    public UserDao(Context context) {
        dbHelper = new DatabaseHelper(context);
    }

    // 🔹 CREATE
    public long insertUser(User user) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ContentValues cv = new ContentValues();
        cv.put("name", user.getName());
        cv.put("email", user.getEmail());
        cv.put("password", user.getPassword());
        cv.put("phone", user.getPhone());
        cv.put("gender", user.getGender());
        cv.put("dob", user.getDob());
        cv.put("country", user.getCountry());
        cv.put("skills", user.getSkills());
        cv.put("terms", user.isTermsAccepted() ? 1 : 0);

        return db.insert(DatabaseHelper.TABLE_USER, null, cv);
    }

    // 🔹 READ ALL
    public List<User> getAllUsers() {
        List<User> list = new ArrayList<>();
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        Cursor c = db.rawQuery("SELECT * FROM users", null);

        if (c.moveToFirst()) {
            do {
                User u = new User( c.getString(1),
                        c.getString(2),
                        c.getString(3),
                        c.getString(4),
                        c.getString(5),
                        c.getString(6),
                        c.getString(7),
                        c.getString(8),
                        c.getInt(9) == 1);
                list.add(u);
            } while (c.moveToNext());
        }
        c.close();
        return list;
    }


    public User getUserByID(long id) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM users where id = ?", new String[]{String.valueOf(id)});
        User u = null;
        if (c.moveToFirst()) {
                u = new User();
                u.setId(c.getLong(0));
                u = new User(
                        c.getString(1),
                        c.getString(2),
                        c.getString(3),
                        c.getString(4),
                        c.getString(5),
                        c.getString(6),
                        c.getString(7),
                        c.getString(8),
                        c.getInt(9) == 1
                );

        }
        c.close();
        return u;
    }

    // 🔹 UPDATE
    public int updateUser(long id, User user) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("name", user.getName());
        cv.put("email", user.getEmail());
        cv.put("password", user.getPassword());
        cv.put("phone", user.getPhone());
        cv.put("gender", user.getGender());
        cv.put("dob", user.getDob());
        cv.put("country", user.getCountry());
        cv.put("skills", user.getSkills());
        cv.put("terms", user.isTermsAccepted() ? 1 : 0);
        String[] arg = new String[] { String.valueOf(id)};

        return db.update("users", cv, "id=?", arg);
    }

    // 🔹 DELETE
    public int deleteUser(long id) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        return db.delete("users", "id=?", new String[]{String.valueOf(id)});
    }
}
