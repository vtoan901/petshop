package com.example.petshop;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;
import android.widget.Toast;


import androidx.appcompat.app.AppCompatActivity;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;

public class dog_activity extends AppCompatActivity {
    Button btnThem;
    ArrayList<Dog> arrayDog;
    DogAdapter adapter;
    GridView gridView;
    //private UserDataSource dataSource;
    public static UserDbHelper dbHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dex__1);
        btnThem=findViewById(R.id.btnThem);
        gridView= (GridView) findViewById(R.id.gridView);
        arrayDog = new ArrayList<>();

        arrayDog.add(new Dog(1, "Alatka", "100$", convertDrawableToByteArray(R.drawable.ellipse)));
        arrayDog.add(new Dog(2, "Alatka", "100000$", convertDrawableToByteArray(R.drawable.ellipse)));
        arrayDog.add(new Dog(3, "Alatka", "20000$", convertDrawableToByteArray(R.drawable.ellipse)));
        arrayDog.add(new Dog(4, "Alatka", "33300$", convertDrawableToByteArray(R.drawable.ellipse)));

        adapter=new DogAdapter(this,R.layout.dex__2,arrayDog);
        gridView.setAdapter(adapter);
        //DataBase
//        Toast.makeText(dog_activity.this,"Check Thành Công",Toast.LENGTH_SHORT).show();
//
//        dataSource = new UserDataSource(this);
//        Toast.makeText(dog_activity.this,"Check Thành Công Lần 2",Toast.LENGTH_SHORT).show();
//
//        dataSource.open();
//        Toast.makeText(dog_activity.this,"Check Thành Công Lần 3",Toast.LENGTH_SHORT).show();

//        long id = dataSource.GridViewData(arrayDog);
//        if(id!=-1)
//        {
//            Toast.makeText(dog_activity.this,"Check Thành Công Lần 4",Toast.LENGTH_SHORT).show();
//        }
        dbHelper = new UserDbHelper(dog_activity.this);
        dbHelper.QueryData("CREATE TABLE IF NOT EXISTS dogs (Id INTEGER PRIMARY KEY, Ten TEXT, Gia TEXT, HinhAnh BLOB)");
        Cursor cursor=dbHelper.GetData("SELECT * FROM dogs");
        while(cursor.moveToNext()){
            arrayDog.add(new Dog(
                    cursor.getInt(0),
                    cursor.getString(1),
                    cursor.getString(2),
                    cursor.getBlob(3)
            ));
        }
        adapter.notifyDataSetChanged();

        btnThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(dog_activity.this, Add_dog_activity.class));
            }
        });



    }
    private byte[] convertDrawableToByteArray(int drawableResId) {
        Bitmap bitmap = BitmapFactory.decodeResource(this.getResources(), drawableResId);
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
        return stream.toByteArray();
    }
}
