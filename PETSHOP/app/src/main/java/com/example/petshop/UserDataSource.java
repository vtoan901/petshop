package com.example.petshop;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;

import java.util.ArrayList;

public class UserDataSource {
    private SQLiteDatabase database;
    private UserDbHelper dbHelper;

    public UserDataSource(Context context) {
        dbHelper = new  UserDbHelper(context);
    }

    public void open() {

        database = dbHelper.getWritableDatabase();
        database=dbHelper.getReadableDatabase();
    }


    public void close() {
        dbHelper.close();
    }

    // Lưu thông tin đăng kí
    public long insertUser(String name, String email, String password) {
        ContentValues values = new ContentValues();
        values.put("name", name);
        values.put("email", email);
        values.put("password", password);

        return database.insert("user", null, values);
    }

    public boolean loginUser(String email, String password) {
        String selection = "email = ? AND password = ?";
        String[] selectionArgs = {email, password};

        Cursor cursor = database.query(
                "user",
                null,
                selection,
                selectionArgs,
                null,
                null,
                null
        );

        int count = cursor.getCount();
        cursor.close();

        return count > 0;
    }
    public long GridViewData(ArrayList arr) {
        String[] projection = {
                "Id",
                "Ten",
                "Gia",
                "HinhAnh"
        };

        Cursor cursor = database.query(
                "dogs",
                projection,
                null,
                null,
                null,
                null,
                null
        );
        while (cursor.moveToNext()) {
            int id = cursor.getInt(cursor.getColumnIndexOrThrow("Id"));
            String ten = cursor.getString(cursor.getColumnIndexOrThrow("Ten"));
            String gia = cursor.getString(cursor.getColumnIndexOrThrow("Gia"));
            byte[] hinh = cursor.getBlob(cursor.getColumnIndexOrThrow("HinhAnh"));

            // Create a Dog object and add it to the array
            arr.add(new Dog(id, ten, gia, hinh));

        }

        cursor.close();
        return cursor.getCount();
    }




}
