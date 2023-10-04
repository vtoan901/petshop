package com.example.petshop;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;

public class UserDbHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "petshop.db";

    private static final String SQL_CREATE_TABLE =
            "CREATE TABLE IF NOT EXISTS user (" +
                    "Id INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "name TEXT," +
                    "email TEXT," +
                    "password TEXT)";
    private static final String SQL_CREATE_TABLE_DOG =
            "CREATE TABLE IF NOT EXISTS dogs (Id INTEGER PRIMARY KEY, Ten TEXT, Gia TEXT, HinhAnh BLOB)";

    public UserDbHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_TABLE);
        //db.execSQL(SQL_CREATE_TABLE_DOG);
    }
    public void QueryData(String sql){
        SQLiteDatabase database=getWritableDatabase();
        database.execSQL(sql);
    }
    public Cursor GetData(String sql){
        SQLiteDatabase database=getReadableDatabase();
        return database.rawQuery(sql,null);
    }
    public void Insert_Dog(String ten, String gia,byte[] hinh){
        SQLiteDatabase database=getWritableDatabase();
        String sql= "INSERT INTO dogs VALUES(null, ?, ?, ?)";
        SQLiteStatement statement=database.compileStatement(sql);
        statement.clearBindings();
        statement.bindString(1,ten);
        statement.bindString(2,gia);
        statement.bindBlob(3,hinh);
        statement.executeInsert();

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS user");
        onCreate(db);
    }

}
